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
package net.saga.aeroconf.app.util;

import net.saga.aeroconf.app.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ResourceUtils {

    private static final Map<String, Integer> cassValueMap = new HashMap<String, Integer>();

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

}