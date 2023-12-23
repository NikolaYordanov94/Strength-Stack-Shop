package bg.softuni.strengthstackshop.service;

import bg.softuni.strengthstackshop.model.dto.user.UserAdminViewDTO;

import java.util.List;

public interface AdminService {
    List<UserAdminViewDTO> getAllUsers();

    void removeUserById(Long id);

}
