package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
@IdClass(DomainReview.class)
public class DomainReview implements Serializable {
    @Id
    @Column
    private int id;
    @Column
    private String title;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "userId")
    private DomainUser user;

    public DomainReview(int id, String title, String content, DomainUser user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public DomainReview() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DomainUser getUser() {
        return user;
    }

    public void setUser(DomainUser user) {
        this.user = user;
    }
}
