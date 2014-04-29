package net.saga.aeroconf.app.data.vo;

import org.jboss.aerogear.android.RecordId;

import java.util.Date;

public class Schedule {

    @RecordId
    public Integer id;
    public Date createdDate;
    public Date updatedDate;
    public int version;
    public String scheduleItemType;
    public String title;
    public Date fromTime;
    public Date toTime;
    public Integer room_id;
    public Integer presentation_id;
    public int rowspan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
