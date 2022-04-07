package com.slashmobility.UserProoviders.application.command;

import com.slashmobility.UserProoviders.domain.exception.EntityNoExistException;
import com.slashmobility.UserProoviders.domain.model.Product;
import com.slashmobility.UserProoviders.domain.repository.IProductRepository;
import com.slashmobility.UserProoviders.domain.repository.IProviderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@NoArgsConstructor
@Service
public class CmdCreateProduct {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProviderRepository providerRepository;

    public Product handle(Product product) throws EntityNoExistException {
        if (!this.providerRepository.getProviderById(product.getProviderName()).isPresent()) {
            throw new EntityNoExistException("The provider doesn´t exist");
        }
        return this.productRepository.save(product);
    }
}
