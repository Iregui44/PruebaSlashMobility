package com.slashmobility.UserProoviders.application.command;

import com.slashmobility.UserProoviders.domain.repository.IUserRepository;
import com.slashmobility.UserProoviders.domain.valueobject.ResetPassword;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CmdResetPassword {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean handle(String userName, ResetPassword resetPassword) {
        return this.userRepository.getUserById(userName).map(user -> {
            if(this.passwordEncoder.matches(resetPassword.getOldPassword(), user.getPassword())) {
                return userRepository.updatePassword(userName, this.passwordEncoder.encode(resetPassword.getNewPassword()));
            }
            return false;
        }).orElse(false);
    }
}
