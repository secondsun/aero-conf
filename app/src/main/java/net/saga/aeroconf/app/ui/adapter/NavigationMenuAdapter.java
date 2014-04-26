package net.saga.aeroconf.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.saga.aeroconf.app.R;

/**
 * Created by summers on 4/25/14.
 */
public class NavigationMenuAdapter extends ArrayAdapter<NavItem> {
    public NavigationMenuAdapter(Context context, int resource, NavItem[] objects) {
        super(context, resource, objects);
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
