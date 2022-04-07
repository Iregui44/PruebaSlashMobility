package com.slashmobility.UserProoviders.application.command;

import com.slashmobility.UserProoviders.domain.exception.EntityExistException;
import com.slashmobility.UserProoviders.domain.model.Provider;
import com.slashmobility.UserProoviders.domain.repository.IProviderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CmdCreateProvider {

    @Autowired
    private IProviderRepository providerRepository;

    public Provider handle(Provider provider) {
        if (this.providerRepository.getProviderById(provider.getName()).isPresent()) {
            throw new EntityExistException("The provider already exist");
        }
        return this.providerRepository.save(provider);
    }
}
