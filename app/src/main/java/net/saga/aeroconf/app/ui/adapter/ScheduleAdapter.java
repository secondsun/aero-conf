/* Copyright 2014 Hoyt Summers Pittman III
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package net.saga.aeroconf.app.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.saga.aeroconf.app.R;
import net.saga.aeroconf.app.data.provider.contract.ConfContract;
import net.saga.aeroconf.app.data.vo.Presentation;
import net.saga.aeroconf.app.data.vo.Room;
import net.saga.aeroconf.app.data.vo.Schedule;
import net.saga.aeroconf.app.util.GsonUtils;
import net.saga.aeroconf.app.util.ResourceUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleAdapter extends BaseAdapter {


    private static final int DATE_TYPE = 0;
    private static final int ITEM_TYPE = 1;
    private static final String TAG = ScheduleAdapter.class.getSimpleName();


    private final DateFormat format = new SimpleDateFormat("h:mm a");


    private List<Schedule> calendar;

    private Date dayOne;
    private Date dayTwo;

    private int dayOneSize = 0;
    private final Context appContext;


    public ScheduleAdapter(List<Schedule> calendar, Context appContext) {
        Log.d(TAG, "Constructor with data:" + calendar.size());

        this.calendar = calendar;
        this.calendar = new ArrayList<Schedule>(calendar);

        dayOne = Calendar.getInstance().getTime();
        dayTwo = Calendar.getInstance().getTime();

        dayOneSize = 0;
        this.appContext = appContext;
        for (Schedule item : calendar) {
            if (item.fromTime.after(dayTwo)) {

            } else {
                dayOneSize++;
            }
        }


    }

    public void update(List<Schedule> calendar) {
        Log.d(TAG, "Updating new data:" + calendar.size());
        this.calendar = new ArrayList<Schedule>(calendar);
        dayOne = Calendar.getInstance().getTime();
        dayTwo = Calendar.getInstance().getTime();

        dayOneSize = 0;
        for (Schedule item : calendar) {
            if (item.fromTime.after(dayTwo)) {

            } else {
                dayOneSize++;
            }
        }

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                ScheduleAdapter.super.notifyDataSetChanged();
            }
        });
    }

    private void zero(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    @Override
    public int getCount() {
        return calendar.size() + 2;
    }

    @Override
    public Object getItem(int position) {
        if (position == 0) {
            return dayOne;
        } else if (position == dayOneSize + 1) {
            return dayTwo;
        } else if (position > dayOneSize) {
            return calendar.get(position - 2); //Offset for two date header items
        } else {
            return calendar.get(position - 1); //Offset for one date header item
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return DATE_TYPE;
        } else if (position == dayOneSize + 1) {
            return DATE_TYPE;
        } else if (position > dayOneSize) {
            return ITEM_TYPE;
        } else {
            return ITEM_TYPE;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        switch (getItemViewType(position)) {
            case DATE_TYPE:
                convertView = getDateView(convertView);
                ((ViewHolder) convertView.getTag()).date.setText(getDateHeader(position));
                return convertView;
            case ITEM_TYPE:
                convertView = getItemView(convertView);

                ViewHolder holder = (ViewHolder) convertView.getTag();
                Schedule item = ((Schedule) getItem(position));

                holder.date.setText(format.format(item.fromTime));

                if (item != null) {

                    if (item.room_id != null) {
                        Cursor roomCursor = appContext.getContentResolver().query(ConfContract.RoomContract.idUri(item.room_id), null, null, null, null);
                        if (roomCursor.moveToNext()) {
                            Room room = GsonUtils.GSON.fromJson(roomCursor.getString(0), Room.class);
                            holder.date.setBackgroundResource(ResourceUtils.trackCSSToColor(room.cssStyleName));
                            holder.roomName.setText(room.name);
                        } else {
                            holder.roomName.setText("Empty Room");
                            holder.date.setBackgroundResource(R.color.dn_blue);
                        }
                        roomCursor.close();
                    }

                    if (item.presentation_id != null) {
                        Cursor presentation = appContext.getContentResolver().query(ConfContract.PresentationContract.idUri(item.presentation_id), null, null, null, null);
                        if (presentation.moveToNext()) {
                            holder.title.setText(GsonUtils.GSON.fromJson(presentation.getString(0), Presentation.class).title);
                        } else {
                            holder.title.setText("Empty Title");
                        }

                        presentation.close();

                    } else {
                        holder.title.setText(item.title);
                    }
                }

                holder.title.setSelected(true);
                convertView.invalidate();
                convertView.requestLayout();
                convertView.refreshDrawableState();

                return convertView;
        }
        return null;
    }

    private String getDateHeader(int position) {
        return position == 0 ? "Day 0" : "Day 1";
    }

    private View getDateView(View convertView) {
        if (convertView == null || convertView.findViewById(R.id.date) == null) {
            Log.d(TAG, "inflating date");
            LayoutInflater inflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.date_breaker_list_item, null);
            ViewHolder holder = new ViewHolder();
            holder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        }
        return convertView;
    }

    private View getItemView(View convertView) {
        if (convertView == null || convertView.findViewById(R.id.session_room) == null) {
            Log.d(TAG, "inflating item.  Convert was " + convertView);
            LayoutInflater inflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.schedule_list_item, null);
            ViewHolder holder = new ViewHolder();
            holder.date = ((TextView) convertView.findViewById(R.id.start_time));
            holder.roomName = ((TextView) convertView.findViewById(R.id.session_room));
            holder.title = ((TextView) convertView.findViewById(R.id.session_title));
            convertView.setTag(holder);
        }
        return convertView;
    }

    public List<Schedule> getCalendar() {
        return calendar;
    }

    private static class ViewHolder {
        private TextView date;
        private TextView title;
        private TextView roomName;
    }

}