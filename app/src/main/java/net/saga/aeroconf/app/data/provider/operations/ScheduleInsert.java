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
public class ScheduleInsert implements Operation<Uri> {

    private final ContentResolver resolver;

    public ScheduleInsert(ContentResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Uri exec(Gson gson, SQLStore scheduleStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        Schedule calendar = gson.fromJson(values[0].getAsString(ConfContract.ScheduleContract.DATA), Schedule.class);
        scheduleStore.save(calendar);

        if (values[0].getAsBoolean(ConfContract.ScheduleContract.NOTIFY) != null && values[0].getAsBoolean(ConfContract.ScheduleContract.NOTIFY)) {
            resolver.notifyChange(ConfContract.ScheduleContract.URI, null, false);
        }
        return ConfContract.ScheduleContract.URI;
    }
}