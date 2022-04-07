package com.slashmobility.UserProoviders.application.query;


import com.slashmobility.UserProoviders.domain.model.Product;
import com.slashmobility.UserProoviders.domain.repository.IProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class QueryProductsByType {

    @Autowired
    private IProductRepository productRepository;

    public Optional<List<Product>> handle(String type) {
        return this.productRepository.getProductsByType(type);
    }
}
