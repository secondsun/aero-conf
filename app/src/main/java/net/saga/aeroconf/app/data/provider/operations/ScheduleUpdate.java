package net.saga.aeroconf.app.data.provider.operations;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.google.gson.Gson;

import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.vo.Schedule;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

public class ScheduleUpdate implements Operation<Integer> {

    private final ContentResolver resolver;

    public ScheduleUpdate(ContentResolver resolver) {
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
        Schedule schedule = gson.fromJson(values[0].getAsString(ConfContract.ScheduleContract.DATA), Schedule.class);
        scheduleStore.save(schedule);
        if (values[0].getAsBoolean(ConfContract.ScheduleContract.NOTIFY) != null && values[0].getAsBoolean(ConfContract.ScheduleContract.NOTIFY)) {
            resolver.notifyChange(ConfContract.ScheduleContract.URI, null, false);
        }
        return 1;
    }
}