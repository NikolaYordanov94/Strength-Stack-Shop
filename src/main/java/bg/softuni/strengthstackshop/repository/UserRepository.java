package bg.softuni.strengthstackshop.repository;

import bg.softuni.strengthstackshop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    Optional<User> findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE NOT EXISTS (SELECT r FROM u.roles r WHERE r.roleName = 'ADMIN')")
    List<User> findUsersWithOnlyUserRole();

    void removeUserById(Long id);

}
