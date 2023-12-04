package via.sep4.sep4test.database.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
  @Column
  private String manufacturer;
  @Column
  private int stock;

  public DomainItem(int id, String title, String description, double price, String manufacturer, int stock) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.price = price;
    this.manufacturer = manufacturer;
    this.stock = stock;
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

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
