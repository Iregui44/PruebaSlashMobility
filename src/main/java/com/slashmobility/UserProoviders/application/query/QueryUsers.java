package com.slashmobility.UserProoviders.application.query;

import com.slashmobility.UserProoviders.domain.model.User;
import com.slashmobility.UserProoviders.domain.repository.IUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class QueryUsers {

    @Autowired
    private IUserRepository userRepository;

    public Optional<List<User>> handle() {
        return this.userRepository.getAllUsers();
    }
}
