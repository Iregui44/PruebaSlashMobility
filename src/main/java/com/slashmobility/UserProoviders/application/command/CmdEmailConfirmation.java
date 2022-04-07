package com.slashmobility.UserProoviders.application.command;

import com.slashmobility.UserProoviders.domain.repository.IUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CmdEmailConfirmation {

    @Autowired
    private IUserRepository userRepository;

    public boolean handle(String userName) {
        return this.userRepository.updateEmailConfirmation(userName, true);
    }
}
