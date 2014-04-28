package net.saga.aeroconf.app.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AeroConfContentProvider extends ContentProvider {

    public static final String AUTHORITY = "content://org.jboss.aeroconf";

    public static final DataManager MANAGER = new DataManager();

    public final CountDownLatch storeLatch = new CountDownLatch(3);

    public AeroConfContentProvider() {

    }


    @Override
    public boolean onCreate() {

        SQLStore<Room> roomStore = registerAndOpenStore(Room.class);
        SQLStore<Speaker> speakerStore = registerAndOpenStore(Speaker.class);
        SQLStore<Presentation> presentationStore = registerAndOpenStore(Presentation.class);

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

    private void loadRoomsFromFile() {
        InputStream rooms = getContext().getResources().openRawResource(R.raw.rooms);

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
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
