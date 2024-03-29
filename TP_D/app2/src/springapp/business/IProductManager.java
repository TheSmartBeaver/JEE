package springapp.business;

import java.util.Collection;

import springapp.model.Product;

public interface IProductManager {

    Collection<Product> findAll();

    void save(Product p);

    Product find(int number);

}
