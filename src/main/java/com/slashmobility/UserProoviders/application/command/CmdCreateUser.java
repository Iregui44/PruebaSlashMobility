package com.slashmobility.UserProoviders.application.command;

import com.slashmobility.UserProoviders.domain.exception.EntityExistException;
import com.slashmobility.UserProoviders.domain.model.User;
import com.slashmobility.UserProoviders.domain.repository.IUserRepository;
import com.slashmobility.UserProoviders.domain.service.IEmailService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CmdCreateUser {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IEmailService emailService;

    public void handle(User user) throws EntityExistException {
        if(userRepository.getUserById(user.getUserName()).isPresent()) {
            throw new EntityExistException("The User already Exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmailConfirm(false);
        this.userRepository.save(user);
        this.emailService.sendConfirmationEmail(user);
    }

}
