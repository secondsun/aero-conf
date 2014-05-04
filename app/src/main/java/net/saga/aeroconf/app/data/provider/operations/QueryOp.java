package net.saga.aeroconf.app.data.provider.operations;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.google.gson.Gson;

import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.provider.contract.SingleColumnJsonArrayList;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

import java.util.ArrayList;

public class QueryOp implements Operation<Cursor> {

    @Override
    public SingleColumnJsonArrayList exec(Gson gson, SQLStore store, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        if (selection != null && selection.equals(ConfContract.ID)) {
            return new SingleColumnJsonArrayList((store.read(selectionArgs[0])));
        }
        return new SingleColumnJsonArrayList(new ArrayList(store.readAll()));
    }
}