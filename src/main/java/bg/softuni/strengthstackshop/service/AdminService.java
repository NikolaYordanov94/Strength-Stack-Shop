package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.dto.user.UserAdminViewDTO;
import bg.softuni.strengthstackshop.model.entity.User;

import java.util.List;

public interface AdminService {
    List<UserAdminViewDTO> getAllUsers();

    void removeUserById(Long id);

}
