package fr.dorianmaliszewski.app.oauth2resource.controllers;

import fr.dorianmaliszewski.app.oauth2resource.domains.Post;
import fr.dorianmaliszewski.app.oauth2resource.dtos.DTO;
import fr.dorianmaliszewski.app.oauth2resource.repositories.PostRepository;
import fr.dorianmaliszewski.app.oauth2resource.requests.PostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public ResponseEntity findAll() {
        DTO<Post> postDTO = new DTO<>();
        postDTO.setItems(this.postRepository.findAll());
        postDTO.setNumFound(postDTO.getItems().size());
        return ResponseEntity.ok(postDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity findOneById(@PathVariable Long id) {
        Optional<Post> post = this.postRepository.findById(id);
        return post.isEmpty() ?
            ResponseEntity.notFound().build() :
            ResponseEntity.ok(post.get());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PostRequest postRequest, Principal principal) {
        Post post = new Post();
        post.setBody(postRequest.getBody());
        post.setTitle(postRequest.getTitle());
        post.setCreator(principal.getName());
        post = this.postRepository.save(post);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody PostRequest postRequest, @PathVariable Long id, Principal principal) {
        Optional<Post> post = this.postRepository.findById(id);

        if (post.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!post.get().getCreator().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (postRequest.getBody() != null) {
            post.get().setBody(postRequest.getBody());
        }

        if (postRequest.getTitle() != null) {
            post.get().setTitle(postRequest.getTitle());
        }

        return ResponseEntity.ok(this.postRepository.save(post.get()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id, Principal principal) {
        Optional<Post> optionalPost = this.postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if(optionalPost.get().getCreator().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        postRepository.delete(optionalPost.get());

        return ResponseEntity.ok().build();
    }
}
