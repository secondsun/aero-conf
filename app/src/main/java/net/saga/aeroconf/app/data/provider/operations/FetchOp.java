package net.saga.aeroconf.app.data.provider.operations;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import net.saga.aeroconf.app.data.provider.contract.SingleColumnJsonArrayList;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

public class FetchOp implements Operation<Cursor> {

    @Override
    public SingleColumnJsonArrayList exec(SQLStore store, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        return new SingleColumnJsonArrayList((store.read(uri.getLastPathSegment())));
    }
}