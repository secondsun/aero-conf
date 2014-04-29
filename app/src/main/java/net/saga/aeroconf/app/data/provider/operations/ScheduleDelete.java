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
public class ScheduleDelete implements Operation<Integer> {

    private final ContentResolver resolver;

    public ScheduleDelete(ContentResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Integer exec(Gson gson, SQLStore scheduleStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {

        if (selectionArgs == null || selectionArgs[0] == null) {
            scheduleStore.reset();
        } else {
            Long id = Long.getLong(selectionArgs[0]);
            scheduleStore.remove(id);
        }

        resolver.notifyChange(ConfContract.ScheduleContract.URI, null, false);
        return 1;
    }
}