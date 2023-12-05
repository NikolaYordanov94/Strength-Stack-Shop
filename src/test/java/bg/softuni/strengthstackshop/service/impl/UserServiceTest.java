package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.dto.user.UserRegisterBindingModel;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.repository.RoleRepository;
import bg.softuni.strengthstackshop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSaveUser(){
        User user = new User();

        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
        user.setPassword("password");

        userService.saveUser(user);

        verify(userRepository).save(any(User.class));

    }

    @Test
    public void testFindByUsername_UserFound() {
        // Arrange
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.findByUsername(username);

        // Assert
        assertNotNull(foundUser);
        assertEquals(username, foundUser.getUsername());
    }

    @Test
    public void testFindByUsername_UserNotFound() {
        // Arrange
        String username = "testuser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.findByUsername(username));
    }

    @Test
    public void testExistsByEmail() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(new User()));

        // Act
        boolean exists = userService.existsByEmail(email);

        // Assert
        assertTrue(exists);
    }

    @Test
    public void testExistsByPhoneNumber() {
        // Arrange
        String phoneNumber = "1234567890";
        when(userRepository.findUserByPhoneNumber(phoneNumber)).thenReturn(Optional.of(new User()));

        // Act
        boolean exists = userService.existsByPhoneNumber(phoneNumber);

        // Assert
        assertTrue(exists);
    }

    @Test
    public void testExistsByUsername() {
        // Arrange
        String username = "testuser";
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(new User()));

        // Act
        boolean exists = userService.existsByUsername(username);

        // Assert
        assertTrue(exists);
    }

//    @Test
//    public void testRegisterWithNullInput() {
//        // Arrange & Act
//        boolean result = userService.register(null);
//
//        // Assert
//        assertFalse(result);
//    }

    @Test
    public void testRegisterWhenPasswordEncoderThrowsException() {
        // Arrange
        UserRegisterBindingModel model = createUserRegisterBindingModel();
        when(passwordEncoder.encode(anyString())).thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> userService.register(model));
        assertEquals("Test exception", exception.getMessage());
    }

    @Test
    public void testFindByUsernameWithNullInput() {
        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.findByUsername(null));
    }

    @Test
    public void testExistsByEmailWithNullInput() {
        // Arrange & Act
        boolean result = userService.existsByEmail(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testExistsByPhoneNumberWithNullInput() {
        // Arrange & Act
        boolean result = userService.existsByPhoneNumber(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testExistsByUsernameWithNullInput() {
        // Arrange & Act
        boolean result = userService.existsByUsername(null);

        // Assert
        assertFalse(result);
    }

    private UserRegisterBindingModel createUserRegisterBindingModel() {
        UserRegisterBindingModel model = new UserRegisterBindingModel();
        model.setUsername("username");
        model.setEmail("email@example.com");
        model.setPassword("password");
        model.setConfirmPassword("password");
        return model;
    }
}
