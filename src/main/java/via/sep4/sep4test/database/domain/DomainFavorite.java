package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
@IdClass(DomainFavorite.class)
public class DomainFavorite implements Serializable {
    @Id
    @Column
    private int userId;

    @Id
    @Column
    private int itemId;

    public DomainFavorite(int userId, int itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public DomainFavorite() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
