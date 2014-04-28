package net.saga.aeroconf.app.data.vo;

import org.jboss.aerogear.android.RecordId;

import java.io.Serializable;
import java.util.Date;

public class Presentation implements Serializable {
    @RecordId
    public Integer id;

    public Date createdDate;
    public Date updatedDate;
    public int version;
    public String audioLink;
    public String description;
    public String presentationLink;
    public Speaker speaker;
    public String title;
    public String presentationType;
    public String skillLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ((Object) this).getClass() != o.getClass()) return false;

        Presentation that = (Presentation) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (audioLink != null ? !audioLink.equals(that.audioLink) : that.audioLink != null)
            return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (presentationLink != null ? !presentationLink.equals(that.presentationLink) : that.presentationLink != null)
            return false;
        if (presentationType != null ? !presentationType.equals(that.presentationType) : that.presentationType != null)
            return false;
        if (skillLevel != null ? !skillLevel.equals(that.skillLevel) : that.skillLevel != null)
            return false;
        if (speaker != null ? !speaker.equals(that.speaker) : that.speaker != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (updatedDate != null ? !updatedDate.equals(that.updatedDate) : that.updatedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        result = 31 * result + version;
        result = 31 * result + (audioLink != null ? audioLink.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (presentationLink != null ? presentationLink.hashCode() : 0);
        result = 31 * result + (speaker != null ? speaker.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (presentationType != null ? presentationType.hashCode() : 0);
        result = 31 * result + (skillLevel != null ? skillLevel.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}