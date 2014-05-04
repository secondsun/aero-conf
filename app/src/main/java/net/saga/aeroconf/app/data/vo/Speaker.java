package net.saga.aeroconf.app.data.vo;

import org.jboss.aerogear.android.RecordId;

import java.io.Serializable;
import java.util.Date;

public class Speaker implements Serializable {

    @RecordId
    private Integer id;

    private Date createdDate;
    private Date updatedDate;
    private int version;
    private String bio;
    private String firstName;
    private String lastName;
    private String twitterId;
    private String googlePlusId;
    private String linkedInId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        Speaker speaker = (Speaker) o;

        if (id != speaker.id) return false;
        if (version != speaker.version) return false;
        if (bio != null ? !bio.equals(speaker.bio) : speaker.bio != null) return false;
        if (createdDate != null ? !createdDate.equals(speaker.createdDate) : speaker.createdDate != null)
            return false;
        if (firstName != null ? !firstName.equals(speaker.firstName) : speaker.firstName != null)
            return false;
        if (googlePlusId != null ? !googlePlusId.equals(speaker.googlePlusId) : speaker.googlePlusId != null)
            return false;
        if (lastName != null ? !lastName.equals(speaker.lastName) : speaker.lastName != null)
            return false;
        if (linkedInId != null ? !linkedInId.equals(speaker.linkedInId) : speaker.linkedInId != null)
            return false;
        if (twitterId != null ? !twitterId.equals(speaker.twitterId) : speaker.twitterId != null)
            return false;
        return !(updatedDate != null ? !updatedDate.equals(speaker.updatedDate) : speaker.updatedDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + version;
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (twitterId != null ? twitterId.hashCode() : 0);
        result = 31 * result + (googlePlusId != null ? googlePlusId.hashCode() : 0);
        result = 31 * result + (linkedInId != null ? linkedInId.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
