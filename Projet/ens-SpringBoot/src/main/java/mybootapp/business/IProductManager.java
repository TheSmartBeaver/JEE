package mybootapp.business;

import java.util.Collection;

import mybootapp.model.Product;

public interface IProductManager {

    Collection<Product> findAll();

    void save(Product p);

    Product find(int number);

}
