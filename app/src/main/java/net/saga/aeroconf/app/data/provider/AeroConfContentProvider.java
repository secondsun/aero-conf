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

}
