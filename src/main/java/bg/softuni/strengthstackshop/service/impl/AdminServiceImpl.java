package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.user.UserAdminViewDTO;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.model.enums.RoleName;
import bg.softuni.strengthstackshop.repository.RoleRepository;
import bg.softuni.strengthstackshop.repository.UserRepository;
import bg.softuni.strengthstackshop.service.AdminService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final String USER_NOT_FOUND_WITH_ID = "User not found with id: ";

    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
    }


    @Override
    public List<UserAdminViewDTO> getAllUsers() {
        List<UserAdminViewDTO> adminViewDTOList = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            UserAdminViewDTO userAdminViewDTO = new UserAdminViewDTO();
            userAdminViewDTO.setUsername(user.getUsername());
            userAdminViewDTO.setId(user.getId());
            adminViewDTOList.add(userAdminViewDTO);
        });

        return adminViewDTOList;
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_WITH_ID + id));

        user.getRoles().clear();
        userRepository.removeUserById(id);
    }

    @Override
    public void changeRoleToAdmin(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_WITH_ID + id));

        user.getRoles().clear();

        user.getRoles()
                .add(roleRepository
                .findByRoleName(RoleName.ADMIN));

        userRepository.save(user);
    }

    @Override
    public void changeRoleToUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_WITH_ID + id));

        user.getRoles().clear();

        user.getRoles()
                .add(roleRepository
                .findByRoleName(RoleName.USER));

        userRepository.save(user);
    }

}
