package net.saga.aeroconf.app.data.provider.contract;

import android.net.Uri;

import net.saga.aeroconf.app.data.provider.AeroConfContentProvider;

public abstract class ConfContract {

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

    public static class Room extends ConfContract {
        public static final Uri URI = Uri.parse(AeroConfContentProvider.AUTHORITY + "/Room");
    }

    public static class Speaker extends ConfContract {
        public static final Uri URI = Uri.parse(AeroConfContentProvider.AUTHORITY + "/Speaker");
    }

    public static class Presentation extends ConfContract {
        public static final Uri URI = Uri.parse(AeroConfContentProvider.AUTHORITY + "/Presentation");
    }

}


