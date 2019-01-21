package fr.dorianmaliszewski.app.oauth2resource.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="post_pos")
public class Post extends BaseEntity {
    private String title;
    @Lob
    private String body;

    private String creator;
}
