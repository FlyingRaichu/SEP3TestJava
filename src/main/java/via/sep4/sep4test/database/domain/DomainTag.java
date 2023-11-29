package via.sep4.sep4test.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class DomainTag {
    @Id
    @Column private int id;
    @Column private String tagName;

    public DomainTag(){}
    public DomainTag(int id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
