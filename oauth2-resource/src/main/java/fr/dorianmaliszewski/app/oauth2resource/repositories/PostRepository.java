package fr.dorianmaliszewski.app.oauth2resource.repositories;

import fr.dorianmaliszewski.app.oauth2resource.domains.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCreator(String creator);
}
