package via.sep4.sep4test.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table
public class DomainReview implements Serializable {
    @Id
    @Column
    private int id;

    @Column
    private String content;
    @Column
    private int rating;
    @Column
    private String username;

    public DomainReview(int id, String content, int rating, String username) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.username = username;
    }

    public DomainReview() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
