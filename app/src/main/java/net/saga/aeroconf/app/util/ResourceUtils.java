package net.saga.aeroconf.app.util;

import net.saga.aeroconf.app.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by summers on 11/13/13.
 */
public class ResourceUtils {

    private static Map<String, Integer> cassValueMap = new HashMap<String, Integer>();

    public static int trackCSSToColor(String trackCSS) {
        if (trackCSS == null) {
            return R.color.dn_orange_red;
        }
        String trackId = trackCSS.replace("-", "_");
        if (cassValueMap.get(trackId) == null) {
            for (Field field : R.color.class.getFields()) {
                if (field.getName().toLowerCase().equals(trackId)) {
                    try {
                        cassValueMap.put(trackId, field.getInt(null));
                    } catch (IllegalAccessException e) {
                        return 0;
                    }
                }
            }
        }

        return cassValueMap.get(trackId);

    }

    public static int roomCSSToColor(String roomName) {
        return trackCSSToColor(RoomName.room(roomName).trackName);
    }

}