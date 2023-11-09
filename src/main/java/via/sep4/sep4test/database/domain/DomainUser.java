package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
//@IdClass(DomainUser.class)
public class DomainUser implements Serializable {
    @Id
    @Column
    private String username;

    //todo or save the hash, depends, but for now it's password
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String role;
    @OneToOne
    @JoinColumn(name = "orderHistoryId")
    private DomainOrderHistory orderHistory;

    public DomainUser(String username, String password, String email, String role, DomainOrderHistory orderHistory) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.orderHistory = orderHistory;
    }

    public DomainUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DomainOrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(DomainOrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }
}
