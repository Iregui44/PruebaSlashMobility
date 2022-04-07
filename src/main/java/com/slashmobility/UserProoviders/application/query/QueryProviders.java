package com.slashmobility.UserProoviders.application.query;

import com.slashmobility.UserProoviders.domain.model.Provider;
import com.slashmobility.UserProoviders.domain.repository.IProviderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class QueryProviders {

    @Autowired
    private IProviderRepository providerRepository;

    public Optional<List<Provider>> handle() {
        return this.providerRepository.getAllProviders();
    }
}
