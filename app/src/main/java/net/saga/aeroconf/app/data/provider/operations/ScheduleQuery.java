package net.saga.aeroconf.app.data.provider.operations;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.google.gson.Gson;

import net.saga.aeroconf.app.data.provider.contract.SingleColumnJsonArrayList;
import net.saga.aeroconf.app.data.vo.Schedule;

import org.jboss.aerogear.android.impl.datamanager.SQLStore;

import java.util.ArrayList;

public class ScheduleQuery implements Operation<Cursor> {

    @Override
    public SingleColumnJsonArrayList exec(Gson gson, SQLStore scheduleStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {
        return new SingleColumnJsonArrayList(new ArrayList<Schedule>(scheduleStore.readAll()));
    }
}