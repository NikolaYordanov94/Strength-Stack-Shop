package bg.softuni.strengthstackshop.init;

import bg.softuni.strengthstackshop.model.entity.Role;
import bg.softuni.strengthstackshop.model.enums.RoleName;
import bg.softuni.strengthstackshop.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DBInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        long count = roleRepository.count();

        if(count == 0){
            List<Role> userRoles = new ArrayList<>();

            Arrays.stream(RoleName.values())
                    .forEach(roleName -> {
                        Role role = new Role();
                        role.setRoleName(roleName);
                        userRoles.add(role);
                    });

            roleRepository.saveAll(userRoles);
        }
    }
}
