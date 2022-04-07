package com.slashmobility.UserProoviders.infraestructure.adapter.persistence.repository;

import com.slashmobility.UserProoviders.domain.model.Provider;
import com.slashmobility.UserProoviders.domain.repository.IProviderRepository;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.crud.ProviderCrudRepository;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.ProductEntity;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.ProviderEntity;
import com.slashmobility.UserProoviders.infraestructure.mapper.ProviderMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Repository
public class ProviderRepository implements IProviderRepository {

    @Autowired
    private ProviderCrudRepository providerCrudRepository;

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public Optional<Provider> getProviderById(String name) {
        return this.providerCrudRepository.findById(name).map(provider -> providerMapper.toDomain(provider));
    }

    @Override
    public Optional<List<Provider>> getAllProviders() {
        List<ProviderEntity> providers = this.providerCrudRepository.findAll();
        if(providers.size() > 0) {
            return Optional.of(this.providerMapper.toDomains(providers));
        }
        return Optional.empty();
    }

    @Override
    public Provider save(Provider provider) {
        return this.providerMapper
                .toDomain(this.providerCrudRepository.save(providerMapper.toEntity(provider)));
    }

    @Override
    public boolean updateProvider(Provider provider) {
        return this.providerCrudRepository.save(this.providerMapper.toEntity(provider)) != null;
    }

    @Override
    public Optional<List<Provider>> getProvidersByCity(String cityName) {
        List<ProviderEntity> providers = this.providerCrudRepository.findByCity(cityName);
        if(providers.size() > 0) {
            return Optional.of(this.providerMapper.toDomains(providers));
        }
        return Optional.empty();
    }

}
