package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.dto.user.UserRegisterBindingModel;

public interface UserService {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);
}
