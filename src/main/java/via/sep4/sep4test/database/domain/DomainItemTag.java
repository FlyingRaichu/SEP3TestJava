package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table
@IdClass(DomainItemTag.class)
public class DomainItemTag implements Serializable {

    @Id
    @Column private int itemId;

    @Id
    @Column private int tagId;

    public DomainItemTag() {}

    public DomainItemTag(int itemId, int tagId) {
        this.itemId = itemId;
        this.tagId = tagId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
