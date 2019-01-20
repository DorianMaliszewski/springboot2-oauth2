package fr.dorianmaliszewski.app.oauth2server.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permissions_per")
@Getter
@Setter
public class Permission extends BaseEntity {
    private String name;
}
