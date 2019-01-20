package fr.dorianmaliszewski.app.oauth2server.controllers;

import fr.dorianmaliszewski.app.oauth2server.domains.User;
import fr.dorianmaliszewski.app.oauth2server.dtos.DTO;
import fr.dorianmaliszewski.app.oauth2server.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity findAll() {
        DTO<User> userDTO = new DTO<>();
        userDTO.setItems(userRepository.findAll());
        userDTO.setNumFound(userDTO.getItems().size());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/current")
    public ResponseEntity getMyInfo(Principal principal) {
        return ResponseEntity.ok(this.userRepository.findByUsername(principal.getName()));
    }
}
