package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
public class DomainOrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    //todo this could be calculate and potentially gotten from the list
    @Column
    private int amount;
    @OneToOne
    @JoinColumn(name = "itemId")
    private DomainItem item;

    public DomainOrderItem(int id, int amount, DomainItem item) {
        this.id = id;
        this.amount = amount;
        this.item = item;
    }

    public DomainOrderItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DomainItem getItem() {
        return item;
    }

    public void setItem(DomainItem item) {
        this.item = item;
    }
}
