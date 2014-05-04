package net.saga.aeroconf.app.data.vo;

import org.jboss.aerogear.android.RecordId;

import java.util.Date;

public class Schedule implements Comparable<Schedule> {

    @RecordId
    private Integer id;
    private Date createdDate;
    private Date updatedDate;
    private int version;
    private String scheduleItemType;
    public String title;
    public Date fromTime;
    private Date toTime;
    public Integer room_id;
    public Integer presentation_id;
    private int rowspan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;

        Schedule schedule = (Schedule) o;

        if (rowspan != schedule.rowspan) return false;
        if (version != schedule.version) return false;
        if (createdDate != null ? !createdDate.equals(schedule.createdDate) : schedule.createdDate != null)
            return false;
        if (fromTime != null ? !fromTime.equals(schedule.fromTime) : schedule.fromTime != null)
            return false;
        if (id != null ? !id.equals(schedule.id) : schedule.id != null) return false;
        if (presentation_id != null ? !presentation_id.equals(schedule.presentation_id) : schedule.presentation_id != null)
            return false;
        if (room_id != null ? !room_id.equals(schedule.room_id) : schedule.room_id != null)
            return false;
        if (scheduleItemType != null ? !scheduleItemType.equals(schedule.scheduleItemType) : schedule.scheduleItemType != null)
            return false;
        if (title != null ? !title.equals(schedule.title) : schedule.title != null) return false;
        if (toTime != null ? !toTime.equals(schedule.toTime) : schedule.toTime != null)
            return false;
        return !(updatedDate != null ? !updatedDate.equals(schedule.updatedDate) : schedule.updatedDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + version;
        result = 31 * result + (scheduleItemType != null ? scheduleItemType.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (fromTime != null ? fromTime.hashCode() : 0);
        result = 31 * result + (toTime != null ? toTime.hashCode() : 0);
        result = 31 * result + (room_id != null ? room_id.hashCode() : 0);
        result = 31 * result + (presentation_id != null ? presentation_id.hashCode() : 0);
        result = 31 * result + rowspan;
        return result;
    }

    @Override
    public int compareTo(Schedule another) {
        if (equals(another)) {
            return 0;
        }

        if (another == null) {
            return 1;
        }

        int timeCompare = fromTime.compareTo(another.fromTime);

        if (timeCompare == 0) {
            if (presentation_id != null) {
                if (another.presentation_id == null) {
                    return 1;
                } else {
                    return presentation_id.compareTo(another.presentation_id);
                }
            } else if (another.presentation_id != null) {
                return -1;
            } else {
                if ("KEYNOTE".equals(scheduleItemType)) {
                    return 1;
                } else if ("KEYNOTE".equals(another.scheduleItemType)) {
                    return -1;
                } else {
                    return 0; //Can't think of other valid comparisons.
                }
            }

        } else {
            return timeCompare;
        }

    }
}
