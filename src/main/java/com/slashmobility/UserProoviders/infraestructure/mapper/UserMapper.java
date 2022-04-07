package com.slashmobility.UserProoviders.infraestructure.mapper;

import com.slashmobility.UserProoviders.domain.model.User;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.UserEntity;
import com.slashmobility.UserProoviders.infraestructure.type.UserResponse;
import com.slashmobility.UserProoviders.infraestructure.type.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User toDomain(UserEntity userEntity);

    public UserEntity toEntity(User user);

    public List<User> toDomains(List<UserEntity> users);

    public UserResponse toResponse(User user);

    public List<UserResponse> toResponses(List<User> users);

    public User toDomainOfUpdateRequest(UserUpdateRequest userUpdateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void toEntityUpdate(User user, @MappingTarget UserEntity userEntity);

}
