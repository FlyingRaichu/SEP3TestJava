package via.sep4.sep4test.database.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table
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
