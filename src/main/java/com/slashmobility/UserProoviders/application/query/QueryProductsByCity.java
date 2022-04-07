package com.slashmobility.UserProoviders.application.query;

import com.slashmobility.UserProoviders.domain.model.Product;
import com.slashmobility.UserProoviders.domain.model.Provider;
import com.slashmobility.UserProoviders.domain.repository.IProductRepository;
import com.slashmobility.UserProoviders.domain.repository.IProviderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class QueryProductsByCity {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProviderRepository providerRepository;

    public Optional<List<Product>> handle(String cityName) {
        Optional<List<Provider>> providersCity = this.providerRepository.getProvidersByCity(cityName);
        if(!providersCity.isPresent()) {
            return Optional.empty();
        }
        List<String> providersNames = providersCity.get().stream().map(provider -> provider.getName())
                .collect(Collectors.toList());
        return this.productRepository.getProductsByProviders(providersNames);
    }
}
