package com.slashmobility.UserProoviders.infraestructure.mapper;

import com.slashmobility.UserProoviders.domain.model.Provider;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.ProviderEntity;
import com.slashmobility.UserProoviders.infraestructure.type.ProviderUpdateRequest;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProviderMapper {

    public Provider toDomain(ProviderEntity providerEntity);

    public ProviderEntity toEntity(Provider provider);

    public List<Provider> toDomains(List<ProviderEntity> providers);

    public Provider toDomainOfUpdateRequest(ProviderUpdateRequest providerUpdateRequest);
}
