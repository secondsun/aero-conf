package net.saga.aeroconf.app.data.provider.operations;

import android.content.ContentValues;
import android.net.Uri;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

/**
 * Created by summers on 4/29/14.
 */
public interface Operation<T> {
    T exec(SQLStore calendarStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs);
}
