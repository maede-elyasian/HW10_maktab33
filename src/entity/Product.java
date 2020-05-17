package entity;

import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private double price;
    private int productNumber;

    public Product() {
    }

    public Product(String name, double price, int productNumber) {
        this.name = name;
        this.price = price;
        this.productNumber = productNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId() == product.getId() &&
                Double.compare(product.getPrice(), getPrice()) == 0 &&
                getProductNumber() == product.getProductNumber() &&
                Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getPrice(), getProductNumber());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productNumber=" + productNumber +
                '}' + "\n";
    }
}
