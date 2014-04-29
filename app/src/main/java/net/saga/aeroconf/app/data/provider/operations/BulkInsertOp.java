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
public class BulkInsertOp<T> implements Operation<Integer> {

    private final ContentResolver resolver;
    private final Uri notifyUri;
    private final Class<T> klass;

    public BulkInsertOp(ContentResolver resolver, Uri notifyUri, Class<T> klass) {
        this.resolver = resolver;
        this.notifyUri = notifyUri;
        this.klass = klass;
    }

    @Override
    public Integer exec(Gson gson, SQLStore store, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        for (ContentValues value : values) {
            T calendar = gson.fromJson(value.getAsString(ConfContract.DATA), klass);
            store.save(calendar);
        }

        resolver.notifyChange(notifyUri, null, false);
        return values.length;
    }
}