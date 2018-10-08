package org.nirmal.buddy.collection;

import java.io.Serializable;

import org.nirmal.buddy.collection.Identifiable;


public class SampleDTO implements Serializable, Identifiable {

    private static final long serialVersionUID = 8848065919381525062L;

    private Long id;
    private String title;

    public SampleDTO(String title) {
        super();
        this.title = title;
    }

    public SampleDTO(Long id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Long getID() {
        return id;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SampleDTO other = (SampleDTO) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (title == null) {
            if (other.title != null) return false;
        } else if (!title.equals(other.title)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "SampleDTO [" + (id != null ? "id=" + id + ", " : "") + (title != null ? "title=" + title : "") + "]";
    }

}
