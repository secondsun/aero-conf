package net.saga.aeroconf.app.data.provider.operations;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.google.gson.Gson;

import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.vo.Schedule;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

/**
 * Created by summers on 4/29/14.
 */
public class ScheduleBulkInsert implements Operation<Integer> {

    private final ContentResolver resolver;

    public ScheduleBulkInsert(ContentResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Integer exec(Gson gson, SQLStore scheduleStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        for (ContentValues value : values) {
            Schedule calendar = gson.fromJson(value.getAsString(ConfContract.ScheduleContract.DATA), Schedule.class);
            scheduleStore.save(calendar);
        }

        resolver.notifyChange(ConfContract.ScheduleContract.URI, null, false);
        return values.length;
    }
}