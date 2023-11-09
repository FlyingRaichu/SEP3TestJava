package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
//@IdClass(DomainItem.class)
public class DomainItem implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column
  private String title;
  @Column
  private String manufacture;
  @Column
  private String description;
  @Column
  private double price;
  @Column
  private int stock;
  @OneToMany
  @JoinColumn(name = "reviewIds")
  private List<DomainReview> reviews;

  public DomainItem(int id, String title, String manufacture, String description, double price, int stock, List<DomainReview> reviews) {
    this.id = id;
    this.title = title;
    this.manufacture = manufacture;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.reviews = reviews;
  }

  public DomainItem() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getManufacture() {
    return manufacture;
  }

  public void setManufacture(String manufacture) {
    this.manufacture = manufacture;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public List<DomainReview> getReviews() {
    return reviews;
  }

  public void setReviews(List<DomainReview> reviews) {
    this.reviews = reviews;
  }
}
