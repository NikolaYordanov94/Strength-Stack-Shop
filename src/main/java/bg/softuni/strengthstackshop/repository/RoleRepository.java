package bg.softuni.strengthstackshop.repository;
import bg.softuni.strengthstackshop.model.entity.Role;
import bg.softuni.strengthstackshop.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(RoleName roleName);

}
