package fr.dorianmaliszewski.app.oauth2server.repositories;

import fr.dorianmaliszewski.app.oauth2server.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
