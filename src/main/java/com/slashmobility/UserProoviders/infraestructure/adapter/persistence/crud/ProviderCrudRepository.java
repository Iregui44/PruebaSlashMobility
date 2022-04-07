package com.slashmobility.UserProoviders.infraestructure.adapter.persistence.crud;

import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderCrudRepository extends JpaRepository<ProviderEntity, String> {

    public List<ProviderEntity> findByCity(String cityName);

}
