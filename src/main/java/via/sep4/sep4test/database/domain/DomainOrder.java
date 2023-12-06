package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class DomainOrder implements Serializable {
    @Id
    @Column
    private int id;
    @Column
    String orderFullName;
    @Column
    int postCode;
    @Column
    String address;
    @Column
    String city;
    @Column
    long phoneNumber;
    @Column
    String status;
    @Column
    Date date;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DomainOrderItem> orderItems = new ArrayList<>();

    @Column
    int userId;

    public DomainOrder(int id, String orderFullName, int postCode, String address,
                       String city, long phoneNumber, String status, Date date,
                       List<DomainOrderItem> orderItems, int userId) {
        this.id = id;
        this.orderFullName = orderFullName;
        this.postCode = postCode;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.date = date;
        this.orderItems = orderItems;
        this.userId = userId;
    }

    public DomainOrder() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderFullName() {
        return orderFullName;
    }

    public void setOrderFullName(String orderFullName) {
        this.orderFullName = orderFullName;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DomainOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<DomainOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "DomainOrder{" +
                "id=" + id +
                ", orderFullName='" + orderFullName + '\'' +
                ", postCode=" + postCode +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", orderItems=" + orderItems +
                ", userId=" + userId +
                '}';
    }
}
