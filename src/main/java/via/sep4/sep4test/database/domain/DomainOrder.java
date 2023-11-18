package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;
import via.sep4.protobuf.OrderItem;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
//@IdClass(DomainOrder.class)
public class DomainOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String username;
    @ManyToOne
    @JoinColumn(name = "addressId")
    private DomainAddress address;
    @ManyToOne
    @JoinColumn(name = "fullNameId")
    private DomainFullName fullName;
    @Column
    private double price;

    @OneToMany
    @JoinColumn(name = "OrderItemId")
    private List<DomainOrderItem> orderItems;

    public DomainOrder(int id, String username, DomainAddress address, DomainFullName fullName, double price, List<DomainOrderItem> orderItems) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.fullName = fullName;
        this.price = price;
        this.orderItems = orderItems;
    }

    public DomainOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DomainAddress getAddress() {
        return address;
    }

    public void setAddress(DomainAddress address) {
        this.address = address;
    }

    public DomainFullName getFullName() {
        return fullName;
    }

    public void setFullName(DomainFullName fullName) {
        this.fullName = fullName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<DomainOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<DomainOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
