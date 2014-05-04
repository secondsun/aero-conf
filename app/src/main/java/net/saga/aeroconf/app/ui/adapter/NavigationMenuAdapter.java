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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.saga.aeroconf.app.R;

public class NavigationMenuAdapter extends ArrayAdapter<NavItem> {
    public NavigationMenuAdapter(Context context, NavItem[] objects) {
        super(context, R.layout.drawer_list_layout, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.drawer_list_layout, null);
            NavItemHolder holder = new NavItemHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.nav_item_icon);
            holder.text = (TextView) convertView.findViewById(R.id.nav_item_text);
            convertView.setTag(holder);
        }

        NavItemHolder holder= (NavItemHolder) convertView.getTag();

        NavItem navItem = getItem(position);

        holder.icon.setImageResource(navItem.imageResource);
        holder.text.setText(navItem.label);

        return convertView;

    }

    /**
     * Created by summers on 4/25/14.
     */
    public static class NavItemHolder {
        ImageView icon;
        TextView text;
    }
}
