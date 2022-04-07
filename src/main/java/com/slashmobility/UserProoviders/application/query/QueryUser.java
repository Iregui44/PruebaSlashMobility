package com.slashmobility.UserProoviders.application.query;

import com.slashmobility.UserProoviders.domain.model.User;
import com.slashmobility.UserProoviders.domain.repository.IUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@NoArgsConstructor
@Service
public class QueryUser implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    public Optional<User> handle(String userName) {
        return this.userRepository.getUserById(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserById(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("UserName not found");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUserName(),
                user.get().getPassword(), new ArrayList<>());
    }
}
