package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.user.UserRegisterBindingModel;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.UserRepository;
import bg.softuni.strengthstackshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {

        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            return false;
        }

        boolean existByUsernameOrEmail = userRepository.existsByUsernameOrEmail(
                userRegisterBindingModel.getUsername(),
                userRegisterBindingModel.getEmail());

        if(existByUsernameOrEmail){
            return false;
        }

        User user = new User();
        user.setUsername(userRegisterBindingModel.getUsername());
        user.setEmail(userRegisterBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        user.setAddress(userRegisterBindingModel.getAddress());
        user.setPhoneNumber(userRegisterBindingModel.getPhoneNumber());

        userRepository.save(user);

        return true;
    }
}
