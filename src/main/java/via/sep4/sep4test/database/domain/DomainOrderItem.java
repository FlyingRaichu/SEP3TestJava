package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
public class DomainOrderItem implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private int itemId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private DomainOrder order;
    @Column
    private int quantity;

    public DomainOrderItem(int id, int itemId, DomainOrder orderId, int quantity) {
        this.id = id;
        this.itemId = itemId;
        this.order = orderId;
        this.quantity = quantity;
    }

    public DomainOrderItem() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public DomainOrder getOrder() {
        return order;
    }

    public void setOrder(DomainOrder orderId) {
        this.order = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DomainOrderItem{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }
}

