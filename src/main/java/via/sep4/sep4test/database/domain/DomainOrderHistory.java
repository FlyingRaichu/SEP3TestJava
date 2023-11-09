package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
//@IdClass(DomainOrderHistory.class)
public class DomainOrderHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToMany
    @JoinColumn(name = "orderIds")
    private List<DomainOrder> orders;

    public DomainOrderHistory(int id, List<DomainOrder> orders) {
        this.id = id;
        this.orders = orders;
    }

    public DomainOrderHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DomainOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<DomainOrder> orders) {
        this.orders = orders;
    }
}
