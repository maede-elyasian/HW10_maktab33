package dto;

import java.util.Objects;

public class Shoe extends Product {
    private String brand;
    private String color;
    private String size;

    public Shoe(){}

    public Shoe(String name, double price, int productNumber, String brand, String color, String size) {
        super(name, price, productNumber);
        this.brand = brand;
        this.color = color;
        this.size = size;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoe shoe = (Shoe) o;
        return Objects.equals(getBrand(), shoe.getBrand()) &&
                Objects.equals(getColor(), shoe.getColor()) &&
                Objects.equals(getSize(), shoe.getSize());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getBrand(), getColor(), getSize());
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "id='"+
                super.getId()+'\''+
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}