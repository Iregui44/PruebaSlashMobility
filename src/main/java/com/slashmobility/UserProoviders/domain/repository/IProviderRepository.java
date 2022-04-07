package com.slashmobility.UserProoviders.domain.repository;

import com.slashmobility.UserProoviders.domain.model.Provider;

import java.util.List;
import java.util.Optional;

public interface IProviderRepository {

    public Optional<Provider> getProviderById(String name);

    public Optional<List<Provider>> getAllProviders();

    public Provider save(Provider provider);

    public boolean updateProvider(Provider provider);

    public Optional<List<Provider>> getProvidersByCity(String cityName);

}
