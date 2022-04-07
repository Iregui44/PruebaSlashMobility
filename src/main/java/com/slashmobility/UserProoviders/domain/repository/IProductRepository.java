package com.slashmobility.UserProoviders.domain.repository;

import com.slashmobility.UserProoviders.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    public Optional<Product> getProductById(Long id);

    public Optional<List<Product>> getAllProducts();

    public Product save(Product product);

    public Optional<List<Product>> getProductsByType(String type);

    public boolean updateProduct(Product product);

    public Optional<List<Product>> getProductsByProviders(List<String> providersIds);

}
