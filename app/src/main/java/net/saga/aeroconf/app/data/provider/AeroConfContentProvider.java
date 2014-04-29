package net.saga.aeroconf.app.data.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.provider.operations.Operation;
import net.saga.aeroconf.app.data.provider.operations.ScheduleDelete;
import net.saga.aeroconf.app.data.vo.Presentation;
import net.saga.aeroconf.app.data.vo.Room;
import net.saga.aeroconf.app.data.vo.Schedule;
import net.saga.aeroconf.app.data.vo.Speaker;

public class AeroConfContentProvider extends AbstractAeroConfProvider implements ConfContract {

    private ContentResolver resolver;

    public AeroConfContentProvider() {

    }


    @Override
    public boolean onCreate() {

        roomStore = registerAndOpenStore(Room.class);
        speakerStore = registerAndOpenStore(Speaker.class);
        presentationStore = registerAndOpenStore(Presentation.class);
        scheduleStore = registerAndOpenStore(Schedule.class);
        resolver = getContext().getContentResolver();
        return true;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int match = MATCHER.match(uri);
        Operation<Integer> op = null;

        switch (match) {
            case RoomContract.ROOM:
                throw new UnsupportedOperationException("Not yet implemented");

            case SpeakerContract.SPEAKER:
                throw new UnsupportedOperationException("Not yet implemented");

            case PresentationContract.PRESENTATION:
                throw new UnsupportedOperationException("Not yet implemented");

            case ScheduleContract.SCHEDULE:
                op = new ScheduleDelete(resolver);
                break;
        }

        Integer res = execute(uri, null, selection, selectionArgs, op);
        if (res == null) {
            res = 0;
        }

        return res;

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
            default: {
                throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
            }
        }

        return uri.toString();
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
