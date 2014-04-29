package net.saga.aeroconf.app.data.provider.contract;

import android.net.Uri;

import net.saga.aeroconf.app.data.provider.AeroConfContentProvider;

public interface ConfContract {

    public static final int NO_MATCH = 0;

    /**
     * All items will have a unique identifier
     */
    public static final String ID = "ID";

    /**
     * All items will have a JSON serialized form
     */
    public static final String DATA = "DATA";

    /**
     * If a notification is necessary after an operation has been performed.
     */
    public static final String NOTIFY = "NOTIFY";
    String[] COLUMNS = {DATA, NOTIFY};

    public static class RoomContract implements ConfContract {
        public static final Uri URI = Uri.parse(AeroConfContentProvider.AUTHORITY + "/Room");

        public static final int ROOM = 1000;
        public static final int ROOM_ID = 1001;
    }

    public static class SpeakerContract implements ConfContract {
        public static final Uri URI = Uri.parse(AeroConfContentProvider.AUTHORITY + "/Speaker");

        public static final int SPEAKER = 2000;
        public static final int SPEAKER_ID = 2001;
    }

    public static class PresentationContract implements ConfContract {
        public static final Uri URI = Uri.parse(AeroConfContentProvider.AUTHORITY + "/Presentation");

        public static final int PRESENTATION = 3000;
        public static final int PRESENTATION_ID = 3001;
    }

    public static class ScheduleContract implements ConfContract {
        public static final Uri URI = Uri.parse(AeroConfContentProvider.AUTHORITY + "/Schedule");

        public static final int SCHEDULE = 4000;
        public static final int SCHEDULE_ID = 4001;
    }

}


