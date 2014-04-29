package net.saga.aeroconf.app.data.provider;

import android.content.ContentValues;
import android.net.Uri;

import com.google.gson.Gson;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

/**
 * Created by summers on 4/29/14.
 */
public interface Operation<T> {
    T exec(Gson gson, SQLStore calendarStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs);
}
