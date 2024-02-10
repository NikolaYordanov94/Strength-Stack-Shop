package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.user.UserAdminViewDTO;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.model.enums.RoleName;
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

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserAdminViewDTO> getAllUsers() {
        List<UserAdminViewDTO> adminViewDTOList = new ArrayList<>();

        userRepository.findUsersWithOnlyUserRole().forEach(user -> {
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
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        user.getRoles().clear();
        userRepository.removeUserById(id);
    }

    @Override
    public void changeRoleToAdmin(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        user.getRoles().forEach(role -> {
            role.setRoleName(RoleName.ADMIN);
        });

        userRepository.save(user);
    }

    @Override
    public void changeRoleToUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        user.getRoles().forEach(role -> {
            role.setRoleName(RoleName.USER);
        });

        userRepository.save(user);
    }

}
