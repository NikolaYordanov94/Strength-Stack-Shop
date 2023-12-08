package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.user.UserRegisterBindingModel;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.model.enums.RoleName;
import bg.softuni.strengthstackshop.repository.RoleRepository;
import bg.softuni.strengthstackshop.repository.UserRepository;
import bg.softuni.strengthstackshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
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

        userRegisterBindingModel.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        User user = modelMapper.map(userRegisterBindingModel, User.class);
        user.getRoles().add(roleRepository.findByRoleName(RoleName.USER));

        userRepository.save(user);

        return true;
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber).isPresent();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
