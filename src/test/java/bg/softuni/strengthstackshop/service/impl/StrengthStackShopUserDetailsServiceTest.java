package bg.softuni.strengthstackshop.service.impl;

import bg.softuni.strengthstackshop.model.entity.Role;
import bg.softuni.strengthstackshop.model.entity.User;
import bg.softuni.strengthstackshop.model.enums.RoleName;
import bg.softuni.strengthstackshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StrengthStackShopUserDetailsServiceTest {

    private StrengthStackShopUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new StrengthStackShopUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("pesho@softuni.bg")
        );
    }

    @Test
    void testMock(){
        User user = new User();
        user.setUsername("nikola");

        when(mockUserRepository.findUserByEmail("nikola@example.bg"))
                .thenReturn(Optional.of(user));

        Optional<User> userOpt =
                mockUserRepository.findUserByEmail("nikola@example.bg");

        User userEntity = userOpt.get();

        Assertions.assertEquals("nikola", user.getUsername());
    }



    @Test
    void testUserFoundException() {
        // Arrange
        User testUserEntity = createTestUser();
        when(mockUserRepository.findUserByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        // Act
        UserDetails userDetails =
                serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        // Assert
        assertNotNull(userDetails);
        assertEquals(
                testUserEntity.getEmail(),
                userDetails.getUsername(),
                "Username is not mapped to email.");

        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + RoleName.ADMIN),
                "The user is not admin");
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + RoleName.USER),
                "The user is not user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static User createTestUser() {

        User testUser = new User();

        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);

        testUser.setUsername("nikola");
        testUser.setEmail("pesho@softuni.bg");
        testUser.setPassword("topsecret");
        testUser.setRoles(List.of(adminRole, userRole));

        return testUser;
    }
}
