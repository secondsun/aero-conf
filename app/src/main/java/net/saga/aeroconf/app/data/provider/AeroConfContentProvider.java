package net.saga.aeroconf.app.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import net.saga.aeroconf.app.R;
import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.vo.Presentation;
import net.saga.aeroconf.app.data.vo.Room;
import net.saga.aeroconf.app.data.vo.Speaker;

import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.DataManager;
import org.jboss.aerogear.android.impl.datamanager.SQLStore;
import org.jboss.aerogear.android.impl.datamanager.StoreConfig;
import org.jboss.aerogear.android.impl.datamanager.StoreTypes;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AeroConfContentProvider extends ContentProvider implements ConfContract{

    public static final String AUTHORITY = "content://org.jboss.aeroconf";

    public static final DataManager MANAGER = new DataManager();

    private static UriMatcher MATCHER = new UriMatcher(0);

    static {
        MATCHER.addURI(AUTHORITY, "Room", Room.ROOM);
        MATCHER.addURI(AUTHORITY, "Room/#", Room.ROOM_ID);

        MATCHER.addURI(AUTHORITY, "Speaker", Speaker.SPEAKER);
        MATCHER.addURI(AUTHORITY, "Speaker/#", Speaker.SPEAKER_ID);

        MATCHER.addURI(AUTHORITY, "Presentation", Presentation.PRESENTATION);
        MATCHER.addURI(AUTHORITY, "Presentation/#", Presentation.PRESENTATION_ID);
    }

    public final CountDownLatch storeLatch = new CountDownLatch(3);
    private SQLStore<Room> roomStore;
    private SQLStore<Speaker> speakerStore;
    private SQLStore<Presentation> presentationStore;

    public AeroConfContentProvider() {

    }


    @Override
    public boolean onCreate() {

        roomStore = registerAndOpenStore(Room.class);
        speakerStore = registerAndOpenStore(Speaker.class);
        presentationStore = registerAndOpenStore(Presentation.class);

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

        return true;
    }


    private void loadPresentationsFromFile() {
        InputStream rooms = getContext().getResources().openRawResource(R.raw.rooms);
        JsonReader reader = new JsonReader(new InputStreamReader(rooms));
        JsonElement root = new JsonParser().parse(reader);
        JsonArray array = root.getAsJsonObject().get("presentations").getAsJsonArray();

        JsonElement element;
        Gson gson = new Gson();

        for (int i = 0; i < array.size(); i++) {
            element = array.get(i);
            Presentation room = gson.fromJson(element, Presentation.class);
            presentationStore.save(room);
        }

    }

    private void loadSpeakersFromFile() {
        InputStream rooms = getContext().getResources().openRawResource(R.raw.speakers);
        JsonReader reader = new JsonReader(new InputStreamReader(rooms));
        JsonElement root = new JsonParser().parse(reader);
        JsonArray array = root.getAsJsonObject().get("speakerList").getAsJsonObject().get("speaker").getAsJsonArray();

        JsonElement element;
        Gson gson = new Gson();

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
        Gson gson = new Gson();

        for (int i = 0; i < array.size(); i++) {
            element = array.get(i);
            Room room = gson.fromJson(element, Room.class);
            roomStore.save(room);
        }

    }

    private <T> SQLStore<T> registerAndOpenStore(Class<T> storeClass) {

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

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = MATCHER.match(uri);

        switch (match) {
            case Room.ROOM:
                break;
            case Speaker.SPEAKER:
                break;
            case Presentation.PRESENTATION:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {

        int match = MATCHER.match(uri);

        switch (match) {
            case Room.ROOM:
                break;
            case Speaker.SPEAKER:
                break;
            case Presentation.PRESENTATION:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = MATCHER.match(uri);

        switch (match) {
            case Room.ROOM:
                break;
            case Speaker.SPEAKER:
                break;
            case Presentation.PRESENTATION:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

         int match = MATCHER.match(uri);

        switch (match) {
            case Room.ROOM:
                break;
            case Speaker.SPEAKER:
                break;
            case Presentation.PRESENTATION:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int match = MATCHER.match(uri);

        switch (match) {
            case Room.ROOM:
                break;
            case Speaker.SPEAKER:
                break;
            case Presentation.PRESENTATION:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
