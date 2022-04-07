package com.slashmobility.UserProoviders.application.command;

import com.slashmobility.UserProoviders.domain.exception.EntityExistException;
import com.slashmobility.UserProoviders.domain.exception.EntityNoExistException;
import com.slashmobility.UserProoviders.domain.model.Product;
import com.slashmobility.UserProoviders.domain.repository.IProductRepository;
import com.slashmobility.UserProoviders.domain.repository.IProviderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CmdUpdateProduct {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProviderRepository providerRepository;

    public boolean handle(Product product) {
        if(product.getProviderName() != null
                && !this.providerRepository.getProviderById(product.getProviderName()).isPresent()) {
            throw new EntityNoExistException("The provider does not exist");

        }
        if (!this.productRepository.getProductById(product.getId()).isPresent()) {
            throw new EntityNoExistException("The Product does not exist");
        }
        return this.productRepository.updateProduct(product);
    }
}

