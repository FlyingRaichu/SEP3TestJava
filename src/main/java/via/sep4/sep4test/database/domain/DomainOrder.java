package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

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
    @OneToOne
    @JoinColumn(name = "addressId")
    private DomainAddress address;
    @OneToOne
    @JoinColumn(name = "fullNameId")
    private DomainFullName fullName;
    @Column
    private double price;

    public DomainOrder(int id, String username, DomainAddress address, DomainFullName fullName, double price) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.fullName = fullName;
        this.price = price;
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
}
