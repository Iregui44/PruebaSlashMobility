package com.slashmobility.UserProoviders.domain.repository;

import com.slashmobility.UserProoviders.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    public void save(User user);

    public boolean updatePassword(String userName, String newPassword);

    public Optional<User> getUserById(String userName);

    public Optional<List<User>> getAllUsers();

    public boolean updateEmailConfirmation(String userName, boolean isConfirm);

    public boolean updateUser(User user);

}
