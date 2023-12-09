package bg.softuni.strengthstackshop.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import bg.softuni.strengthstackshop.model.dto.user.UserAdminViewDTO;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

class AdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_WithUsers() {
        List<User> users = List.of(new User()); // Replace with actual User creation logic
        when(userRepository.findUsersWithOnlyUserRole()).thenReturn(users);

        List<UserAdminViewDTO> result = adminService.getAllUsers();

        assertFalse(result.isEmpty());
        assertEquals(users.size(), result.size());
    }

    @Test
    void getAllUsers_NoUsers() {
        when(userRepository.findUsersWithOnlyUserRole()).thenReturn(List.of());

        List<UserAdminViewDTO> result = adminService.getAllUsers();

        assertTrue(result.isEmpty());
    }

    @Test
    void removeUserById_UserExists() {
        Long userId = 1L;
        User user = new User(); // Replace with actual User creation logic
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        adminService.removeUserById(userId);

        verify(userRepository).removeUserById(userId);
    }

    @Test
    void removeUserById_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> adminService.removeUserById(userId));
    }
}
