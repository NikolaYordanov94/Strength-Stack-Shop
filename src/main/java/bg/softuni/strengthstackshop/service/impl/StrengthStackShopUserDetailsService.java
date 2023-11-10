package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class StrengthStackShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public StrengthStackShopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User" + username + "not found!"));
    }

    private UserDetails map(bg.softuni.strengthstackshop.model.entity.User user){
        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of()) //todo - add roles
                .build();
    }
}
