package fr.dorianmaliszewski.app.oauth2server.domains;

import fr.dorianmaliszewski.app.oauth2server.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles_rol")
@Getter
@Setter
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions_rpe",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
            )
    private List<Permission> permissions;
}
