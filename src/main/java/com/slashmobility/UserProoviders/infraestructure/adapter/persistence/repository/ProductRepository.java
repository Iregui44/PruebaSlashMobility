package com.slashmobility.UserProoviders.infraestructure.adapter.persistence.repository;

import com.slashmobility.UserProoviders.domain.model.Product;
import com.slashmobility.UserProoviders.domain.repository.IProductRepository;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.crud.ProductCrudRepository;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.ProductEntity;
import com.slashmobility.UserProoviders.infraestructure.mapper.ProductMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Repository
public class ProductRepository implements IProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Optional<Product> getProductById(Long id) {
        return this.productCrudRepository.findById(id)
                .map(product -> this.productMapper.toDomain(product));
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<ProductEntity> products = this.productCrudRepository.findAll();
        if(products.size() > 0) {
            return Optional.of(this.productMapper.toDomains(products));
        }
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return productMapper
                .toDomain(this.productCrudRepository
                        .save(productMapper.toEntity(product)));
    }

    @Override
    public Optional<List<Product>> getProductsByType(String type) {
        List<ProductEntity> productsType = this.productCrudRepository.findByType(type);
        if(productsType.size() > 0) {
            return Optional.of(this.productMapper.toDomains(productsType));
        }
        return Optional.empty();
    }

    @Override
    public boolean updateProduct(Product product) {
        return this.productCrudRepository.save(productMapper.toEntity(product)) != null;
    }

    @Override
    public Optional<List<Product>> getProductsByProviders(List<String> providersIds) {
        List<ProductEntity> products = this.productCrudRepository.findByProviderNameIn(providersIds);
        if(products.size() > 0) {
            return Optional.of(this.productMapper.toDomains(products));
        }
        return Optional.empty();
    }
}
