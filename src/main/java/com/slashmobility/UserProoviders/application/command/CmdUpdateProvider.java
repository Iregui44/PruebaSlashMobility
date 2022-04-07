package com.slashmobility.UserProoviders.application.command;

import com.slashmobility.UserProoviders.domain.exception.EntityNoExistException;
import com.slashmobility.UserProoviders.domain.model.Provider;
import com.slashmobility.UserProoviders.domain.repository.IProviderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CmdUpdateProvider {

    @Autowired
    private IProviderRepository providerRepository;

    public boolean handle(Provider provider) {
        if(!this.providerRepository.getProviderById(provider.getName()).isPresent()) {
            throw new EntityNoExistException("The provider name does not exist");
        }
        return this.providerRepository.updateProvider(provider);
    }
}
