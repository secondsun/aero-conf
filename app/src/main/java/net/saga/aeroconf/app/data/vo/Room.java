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
package net.saga.aeroconf.app.data.vo;


import org.jboss.aerogear.android.RecordId;

import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {

    @RecordId
    private Integer id;

    private Date createdDate;
    private Date updatedDate;
    private int version;

    public String name;
    private String track;
    public String cssStyleName;
    private int capacity;
    private String description;
    private int roomOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (capacity != room.capacity) return false;
        if (id != room.id) return false;
        if (roomOrder != room.roomOrder) return false;
        if (version != room.version) return false;
        if (createdDate != null ? !createdDate.equals(room.createdDate) : room.createdDate != null)
            return false;
        if (cssStyleName != null ? !cssStyleName.equals(room.cssStyleName) : room.cssStyleName != null)
            return false;
        if (description != null ? !description.equals(room.description) : room.description != null)
            return false;
        if (name != null ? !name.equals(room.name) : room.name != null) return false;
        if (track != null ? !track.equals(room.track) : room.track != null) return false;
        return !(updatedDate != null ? !updatedDate.equals(room.updatedDate) : room.updatedDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + version;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (track != null ? track.hashCode() : 0);
        result = 31 * result + (cssStyleName != null ? cssStyleName.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + roomOrder;
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}