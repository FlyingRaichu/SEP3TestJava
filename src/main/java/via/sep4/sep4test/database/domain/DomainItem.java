package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
public class DomainItem implements Serializable
{
  @Id
  @Column
  private int id;

  @Column
  private String title;
  @Column
  private String description;
  @Column
  private double price;

  public DomainItem(int id, String title, String description, double price) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.price = price;
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
}
