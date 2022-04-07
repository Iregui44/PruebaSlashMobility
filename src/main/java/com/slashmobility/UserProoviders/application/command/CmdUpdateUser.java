package com.slashmobility.UserProoviders.application.command;

import com.slashmobility.UserProoviders.domain.exception.EntityNoExistException;
import com.slashmobility.UserProoviders.domain.model.User;
import com.slashmobility.UserProoviders.domain.repository.IUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CmdUpdateUser {

    @Autowired
    private IUserRepository userRepository;

    public boolean handle(User user) {
        if(!this.userRepository.getUserById(user.getUserName()).isPresent()) {
            throw new EntityNoExistException("The Username does not exist");
        }
        return this.userRepository.updateUser(user);
    }
}
