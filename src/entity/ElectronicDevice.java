package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "electronicdevices")
@PrimaryKeyJoinColumn(name = "product_id")
public class ElectronicDevice extends Product {
    private String model;
    private String brand;

    @Column(name = "production_year")
    private String productionYear;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectronicDevice that = (ElectronicDevice) o;
        return
                Objects.equals(getModel(), that.getModel()) &&
                        Objects.equals(getBrand(), that.getBrand()) &&
                        Objects.equals(getProductionYear(), that.getProductionYear());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getModel(), getBrand(), getProductionYear());
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                "id='" + super.getId() + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", price='" + super.getPrice() + '\'' +
                ", number='" + super.getProductNumber() + '\'' +
                '}';
    }
}
