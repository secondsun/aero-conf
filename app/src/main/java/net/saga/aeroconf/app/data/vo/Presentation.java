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

public class Presentation implements Serializable {
    @RecordId
    private Integer id;

    private Date createdDate;
    private Date updatedDate;
    private int version;
    private String audioLink;
    private String description;
    private String presentationLink;
    private Speaker speaker;
    public String title;
    private String presentationType;
    private String skillLevel;

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
        return !(updatedDate != null ? !updatedDate.equals(that.updatedDate) : that.updatedDate != null);

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