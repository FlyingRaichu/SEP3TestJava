package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
@IdClass(DomainAddress.class)
public class DomainAddress implements Serializable {
    @Id
    @Column
    private int id;

    @Column
    private String address;
    @Column
    private String apartmentNr;
    @Column
    private int postalCode;
    @Column
    private String city;

    public DomainAddress(int id, String address, String apartmentNr, int postalCode, String city) {
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
