package entity;

import javax.persistence.*;

@Entity
@Table(name = "shoes")
@PrimaryKeyJoinColumn(name = "product_id")
public class Shoe extends Product {
    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "id='" + super.getId() + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", price='" + super.getPrice() + '\'' +
                ", number:'" + super.getProductNumber() + '\'' + "}";


    }
}