package bg.softuni.strengthstackshop.model.entity;

import bg.softuni.strengthstackshop.model.enums.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role() {
        //empty constructor
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
