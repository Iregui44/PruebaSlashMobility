package com.slashmobility.UserProoviders.infraestructure.adapter.persistence.repository;

import com.slashmobility.UserProoviders.domain.model.User;
import com.slashmobility.UserProoviders.domain.repository.IUserRepository;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.crud.UserCrudRepository;
import com.slashmobility.UserProoviders.infraestructure.adapter.persistence.entity.UserEntity;
import com.slashmobility.UserProoviders.infraestructure.mapper.UserMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        this.userCrudRepository.save(this.userMapper.toEntity(user));
    }

    @Override
    public boolean updatePassword(String userName, String newPassword) {
        return this.userCrudRepository.findById(userName).map(user -> {
            user.setPassword(newPassword);
            this.userCrudRepository.save(user);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<User> getUserById(String userName) {
        return this.userCrudRepository.findById(userName)
                .map(user -> this.userMapper.toDomain(user));
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        List<UserEntity> users = this.userCrudRepository.findAll();
        if(users.size() > 0) {
            return Optional.of(this.userMapper.toDomains(users));

        }
        return Optional.empty();
    }

    @Override
    public boolean updateEmailConfirmation(String userName, boolean isConfirm) {
        return this.userCrudRepository.findById(userName).map(user -> {
            user.setEmailConfirm(isConfirm);
            this.userCrudRepository.save(user);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateUser(User user) {
        Optional<UserEntity> actualUser = this.userCrudRepository.findById(user.getUserName());
        if(!actualUser.isPresent()) {
            return false;
        }
        UserEntity userEntity = actualUser.get();
        userMapper.toEntityUpdate(user, userEntity);
        return this.userCrudRepository.save(userEntity) != null;
    }
}
