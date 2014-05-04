/* Copyright 2014 Hoyt Summers Pittman III
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package net.saga.aeroconf.app.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import net.saga.aeroconf.app.R;
import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.provider.operations.Operation;
import net.saga.aeroconf.app.data.vo.Presentation;
import net.saga.aeroconf.app.data.vo.Room;
import net.saga.aeroconf.app.data.vo.Schedule;
import net.saga.aeroconf.app.data.vo.Speaker;
import net.saga.aeroconf.app.util.GsonUtils;

import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.DataManager;
import org.jboss.aerogear.android.impl.datamanager.SQLStore;
import org.jboss.aerogear.android.impl.datamanager.StoreConfig;
import org.jboss.aerogear.android.impl.datamanager.StoreTypes;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractAeroConfProvider extends ContentProvider implements ConfContract {

    public static final String AUTHORITY = "org.jboss.aeroconf";

    private static final DataManager MANAGER = new DataManager();
    private static final String TAG = "ContentProvider";

    static final UriMatcher MATCHER = new UriMatcher(0);

    static {
        MATCHER.addURI(AUTHORITY, "Room", RoomContract.ROOM);
        MATCHER.addURI(AUTHORITY, "Room/#", RoomContract.ROOM_ID);

        MATCHER.addURI(AUTHORITY, "Speaker", SpeakerContract.SPEAKER);
        MATCHER.addURI(AUTHORITY, "Speaker/#", SpeakerContract.SPEAKER_ID);

        MATCHER.addURI(AUTHORITY, "Presentation", PresentationContract.PRESENTATION);
        MATCHER.addURI(AUTHORITY, "Presentation/#", PresentationContract.PRESENTATION_ID);

        MATCHER.addURI(AUTHORITY, "Schedule", ScheduleContract.SCHEDULE);
        MATCHER.addURI(AUTHORITY, "Schedule/#", ScheduleContract.SCHEDULE_ID);
    }

    private final CountDownLatch storeLatch = new CountDownLatch(4);
    SQLStore<Room> roomStore;
    SQLStore<Speaker> speakerStore;
    SQLStore<Presentation> presentationStore;
    SQLStore<Schedule> scheduleStore;



    private void loadSchedulesFromFile() {
        InputStream schedule = getContext().getResources().openRawResource(R.raw.schedule);
        JsonReader reader = new JsonReader(new InputStreamReader(schedule));
        JsonElement root = new JsonParser().parse(reader);
        JsonArray array = root.getAsJsonArray();

        JsonElement element;
        Gson gson = GsonUtils.GSON;

        for (int i = 0; i < array.size(); i++) {
            element = array.get(i);
            Schedule scheduleItem = gson.fromJson(element, Schedule.class);
            scheduleStore.save(scheduleItem);
        }
    }


    private void loadPresentationsFromFile() {
        InputStream presentations = getContext().getResources().openRawResource(R.raw.presentations);
        JsonReader reader = new JsonReader(new InputStreamReader(presentations));
        JsonElement root = new JsonParser().parse(reader);
        JsonArray array = root.getAsJsonObject().get("presentations").getAsJsonArray();

        JsonElement element;
        Gson gson = GsonUtils.GSON;

        for (int i = 0; i < array.size(); i++) {
            element = array.get(i);
            Presentation presentation = gson.fromJson(element, Presentation.class);
            presentationStore.save(presentation);
        }

    }

    private void loadSpeakersFromFile() {
        InputStream rooms = getContext().getResources().openRawResource(R.raw.speakers);
        JsonReader reader = new JsonReader(new InputStreamReader(rooms));
        JsonElement root = new JsonParser().parse(reader);
        JsonArray array = root.getAsJsonObject().get("speakerList").getAsJsonObject().get("speaker").getAsJsonArray();

        JsonElement element;
        Gson gson = GsonUtils.GSON;

        for (int i = 0; i < array.size(); i++) {
            element = array.get(i);
            Speaker speaker = gson.fromJson(element, Speaker.class);
            speakerStore.save(speaker);
        }

    }


    private void loadRoomsFromFile() {
        InputStream rooms = getContext().getResources().openRawResource(R.raw.rooms);
        JsonReader reader = new JsonReader(new InputStreamReader(rooms));
        JsonElement root = new JsonParser().parse(reader);
        JsonArray array = root.getAsJsonObject().get("roomList").getAsJsonObject().get("room").getAsJsonArray();

        JsonElement element;
        Gson gson = GsonUtils.GSON;

        for (int i = 0; i < array.size(); i++) {
            element = array.get(i);
            Room room = gson.fromJson(element, Room.class);
            roomStore.save(room);
        }

    }

    <T> SQLStore<T> registerAndOpenStore(Class<T> storeClass) {

        StoreConfig config = new StoreConfig(storeClass);
        config.setContext(getContext().getApplicationContext());
        config.setType(StoreTypes.SQL);

        SQLStore<T> store = (SQLStore<T>) MANAGER.store(config.getName(), config);

        store.open(new Callback<SQLStore<T>>() {
            @Override
            public void onSuccess(SQLStore<T> tsqlStore) {
                storeLatch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("StoreOpen", e.getMessage(), e);
                storeLatch.countDown();
            }
        });

        return store;

    }

    /**
     * Called before all operations to ensure that the datastores are set up.
     */
    private void confirm() {
        try {
            storeLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (roomStore.isEmpty()) {
            loadRoomsFromFile();
        }

        if (speakerStore.isEmpty()) {
            loadSpeakersFromFile();
        }

        if (presentationStore.isEmpty()) {
            loadPresentationsFromFile();
        }

        if (scheduleStore.isEmpty()) {
            loadSchedulesFromFile();
        }
    }

    <T> T execute(final Uri uri, final ContentValues[] values, final String selection, final String[] selectionArgs, final Operation<T> op) {
        final AtomicReference<T> returnRef = new AtomicReference<T>();

        confirm();

        SQLStore tempStore;

        int match = MATCHER.match(uri);
        switch (match) {
            case PresentationContract.PRESENTATION:
            case PresentationContract.PRESENTATION_ID:
                tempStore = presentationStore;
                break;
            case SpeakerContract.SPEAKER:
            case SpeakerContract.SPEAKER_ID:
                tempStore = speakerStore;
                break;
            case RoomContract.ROOM:
            case RoomContract.ROOM_ID:
                tempStore = roomStore;
                break;
            case ScheduleContract.SCHEDULE:
            case ScheduleContract.SCHEDULE_ID:
                tempStore = scheduleStore;
                break;
            default:
                throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
        }

        final SQLStore store = tempStore;


        synchronized (TAG) {
            returnRef.set(op.exec(store, uri, values, selection, selectionArgs));
        }
        return returnRef.get();
    }



}
