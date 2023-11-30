package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.dto.user.UserRegisterBindingModel;
import bg.softuni.strengthstackshop.model.entity.User;

public interface UserService {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    User findByUsername(String username);
}
