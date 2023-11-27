package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
//used for composite keys
//@IdClass(DomainAddress.class)
public class DomainAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String address;
    @Column
    private String apartmentNr;
    @Column
    private String postalCode;
    @Column
    private String city;

    public DomainAddress(int id, String address, String apartmentNr, String postalCode, String city) {
        this.id = id;
        this.address = address;
        this.apartmentNr = apartmentNr;
        this.postalCode = postalCode;
        this.city = city;
    }

    public DomainAddress() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApartmentNr() {
        return apartmentNr;
    }

    public void setApartmentNr(String apartmentNr) {
        this.apartmentNr = apartmentNr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
