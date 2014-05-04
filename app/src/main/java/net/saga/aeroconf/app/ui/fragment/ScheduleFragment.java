package net.saga.aeroconf.app.ui.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.vo.Schedule;
import net.saga.aeroconf.app.ui.adapter.ScheduleAdapter;
import net.saga.aeroconf.app.util.GsonUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private ScheduleAdapter adapter;
    private ListView view;
    private ContentResolver resolver;

    private final Observer observer = new Observer(new Handler());
    private final List<Schedule> calendar = new ArrayList<Schedule>();
    private AsyncTask<Void, Void, List<Schedule>> calendarLoaderTask;

    public ScheduleFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (adapter == null) {
            adapter = new ScheduleAdapter(calendar, activity.getApplicationContext());
        }
        resolver = getActivity().getContentResolver();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new ListView(inflater.getContext());
        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.getItem(position) instanceof Schedule) {
                    Schedule item = (Schedule) adapter.getItem(position);
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        resolver.registerContentObserver(ConfContract.ScheduleContract.URI, false, observer);

        calendarLoaderTask = new AsyncTask<Void, Void, List<Schedule>>() {
            @Override
            protected synchronized List<Schedule> doInBackground(Void... params) {

                List<Schedule> toReturn = new ArrayList<Schedule>();
                Cursor cursor = null;
                try {
                    cursor = getActivity().getContentResolver().query(ConfContract.ScheduleContract.URI, null, null, null, null);

                    while (cursor != null && cursor.moveToNext()) {
                        toReturn.add(GsonUtils.GSON.fromJson(cursor.getString(0), Schedule.class));
                    }

                    return toReturn;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }

            @Override
            protected synchronized void onPostExecute(List<Schedule> userCalendars) {
                if (!super.isCancelled()) {
                    dataUpdated(userCalendars);
                }
            }
        };

        if (calendar == null || calendar.size() < 3) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                calendarLoaderTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            else
                calendarLoaderTask.execute();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        calendarLoaderTask.cancel(false);
    }

    synchronized void dataUpdated(Collection<Schedule> newData) {
        calendar.clear();
        calendar.addAll(newData);
        adapter.update(new ArrayList<Schedule>(newData));
        adapter.notifyDataSetChanged();
    }

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    private final class Observer extends ContentObserver {
        public Observer(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            AsyncTask<Void, Void, ArrayList<Schedule>> task = new AsyncTask<Void, Void, ArrayList<Schedule>>() {
                @Override
                protected ArrayList<Schedule> doInBackground(Void... params) {
                    Cursor cursor = null;
                    try {
                        cursor = resolver.query(ConfContract.ScheduleContract.URI, null, null, null, null);
                        ArrayList<Schedule> calendar = new ArrayList<>(cursor.getCount());
                        while (cursor != null && cursor.moveToNext()) {
                            calendar.add(GsonUtils.GSON.fromJson(cursor.getString(0), Schedule.class));
                        }
                        return calendar;
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                }

                @Override
                protected void onPostExecute(ArrayList<Schedule> userCalendars) {
                    dataUpdated(userCalendars);
                }
            };
        }
    }

}
