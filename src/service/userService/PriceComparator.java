package service.userService;

import entity.Product;
import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getPrice() > p2.getPrice() ? 1 : p1.getPrice() < p2.getPrice() ? -1 : 0 ;
    }
}
