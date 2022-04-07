package com.slashmobility.UserProoviders.infraestructure.adapter.persistence.crud;

import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepository extends JpaRepository<UserEntity, String> {

}
