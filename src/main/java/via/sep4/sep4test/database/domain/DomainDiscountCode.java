package via.sep4.sep4test.database.domain;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
public class DomainDiscountCode implements Serializable {

  @Id
  @Column
  private int id;
  @Column
  private String code;
  @Column
  private  int discountPercentage;


  // Add other fields as needed

  public DomainDiscountCode(int id, String code, int discount) {
    this.id = id;
    this.code = code;
    this.discountPercentage = discount;
  }

  public DomainDiscountCode() {
    // Default constructor
  }

  // Add getters and setters as needed

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(int discountPercentage) {
    this.discountPercentage = discountPercentage;
  }
}
