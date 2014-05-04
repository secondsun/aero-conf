package net.saga.aeroconf.app.data.provider.operations;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;


public class DeleteOp implements Operation<Integer> {

    private final ContentResolver resolver;
    private final Uri notifyUri;

    public DeleteOp(ContentResolver resolver, Uri notifyUri) {
        this.resolver = resolver;
        this.notifyUri = notifyUri;
    }

    @Override
    public Integer exec(SQLStore store, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {

        if (selectionArgs == null || selectionArgs[0] == null) {
            store.reset();
        } else {
            Long id = Long.getLong(selectionArgs[0]);
            store.remove(id);
        }

        resolver.notifyChange(notifyUri, null, false);
        return 1;
    }
}