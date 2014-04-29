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

public class AeroConfContentProvider extends AbstractAeroConfProvider implements ConfContract {


    public AeroConfContentProvider() {

    }


    @Override
    public boolean onCreate() {

        roomStore = registerAndOpenStore(Room.class);
        speakerStore = registerAndOpenStore(Speaker.class);
        presentationStore = registerAndOpenStore(Presentation.class);
        scheduleStore = registerAndOpenStore(Schedule.class);

        return true;
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int match = MATCHER.match(uri);

        switch (match) {
            case RoomContract.ROOM:
                break;
            case SpeakerContract.SPEAKER:
                break;
            case PresentationContract.PRESENTATION:
                break;
            case ScheduleContract.SCHEDULE:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {

        int match = MATCHER.match(uri);

        switch (match) {
            case RoomContract.ROOM:
                break;
            case SpeakerContract.SPEAKER:
                break;
            case PresentationContract.PRESENTATION:
                break;
            case ScheduleContract.SCHEDULE:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int match = MATCHER.match(uri);

        switch (match) {
            case RoomContract.ROOM:
                break;
            case SpeakerContract.SPEAKER:
                break;
            case PresentationContract.PRESENTATION:
                break;
            case ScheduleContract.SCHEDULE:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        int match = MATCHER.match(uri);

        switch (match) {
            case RoomContract.ROOM:
                break;
            case SpeakerContract.SPEAKER:
                break;
            case PresentationContract.PRESENTATION:
                break;
            case ScheduleContract.SCHEDULE:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int match = MATCHER.match(uri);

        switch (match) {
            case RoomContract.ROOM:
                break;
            case SpeakerContract.SPEAKER:
                break;
            case PresentationContract.PRESENTATION:
                break;
            case ScheduleContract.SCHEDULE:
                break;

        }

        throw new UnsupportedOperationException("Not yet implemented");
    }


}
