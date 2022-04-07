package com.slashmobility.UserProoviders.application.query;

import com.slashmobility.UserProoviders.domain.model.Product;
import com.slashmobility.UserProoviders.domain.repository.IProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
@Service
public class QueryProduct {

    @Autowired
    private IProductRepository productRepository;

    public Optional<Product> handle(Long id) {
        return this.productRepository.getProductById(id);
    }
}
