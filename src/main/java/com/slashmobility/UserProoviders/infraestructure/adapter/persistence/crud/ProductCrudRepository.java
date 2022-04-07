package com.slashmobility.UserProoviders.infraestructure.adapter.persistence.crud;

import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductCrudRepository extends JpaRepository<ProductEntity, Long> {

    public List<ProductEntity> findByType(String type);

    public List<ProductEntity> findByProviderNameIn(Collection<String> providersIds);

}
