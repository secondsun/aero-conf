package net.saga.aeroconf.app.data.vo;


import org.jboss.aerogear.android.RecordId;

import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {

    @RecordId
    public Integer id;

    public Date createdDate;
    public Date updatedDate;
    public int version;

    public String name;
    public String track;
    public String cssStyleName;
    public int capacity;
    public String description;
    public int roomOrder;

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
        if (updatedDate != null ? !updatedDate.equals(room.updatedDate) : room.updatedDate != null)
            return false;

        return true;
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