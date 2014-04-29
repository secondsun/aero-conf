package net.saga.aeroconf.app.data.provider.operations;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.google.gson.Gson;

import net.saga.aeroconf.app.data.provider.contract.ConfContract;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

/**
 * Created by summers on 4/29/14.
 */
public class InsertOp<T> implements Operation<Uri> {

    private final ContentResolver resolver;
    private final Uri notifyUri;
    private final Class<T> klass;

    public InsertOp(ContentResolver resolver, Uri notifyUri, Class<T> klass) {
        this.resolver = resolver;
        this.notifyUri = notifyUri;
        this.klass = klass;
    }

    @Override
    public Uri exec(Gson gson, SQLStore store, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        T calendar = gson.fromJson(values[0].getAsString(ConfContract.DATA), klass);
        store.save(calendar);

        if (values[0].getAsBoolean(ConfContract.NOTIFY) != null && values[0].getAsBoolean(ConfContract.NOTIFY)) {
            resolver.notifyChange(notifyUri, null, false);
        }
        return uri;
    }
}