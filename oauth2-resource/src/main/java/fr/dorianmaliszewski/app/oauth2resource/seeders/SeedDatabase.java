package fr.dorianmaliszewski.app.oauth2resource.seeders;

import fr.dorianmaliszewski.app.oauth2resource.domains.Post;
import fr.dorianmaliszewski.app.oauth2resource.repositories.PostRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class SeedDatabase {

    private final PostRepository postRepository;

    public SeedDatabase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.postRepository.deleteAll();

        for(int i = 0; i < 10 ; i ++) {
            Post p = new Post();
            p.setTitle("Best Post" + i);
            p.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. In nulla posuere sollicitudin aliquam ultrices sagittis. Et netus et malesuada fames ac turpis egestas. Sed viverra ipsum nunc aliquet bibendum enim facilisis gravida. Elementum sagittis vitae et leo duis. Donec et odio pellentesque diam volutpat commodo sed. Tristique senectus et netus et malesuada fames ac turpis. Nisl vel pretium lectus quam id leo. Ultrices neque ornare aenean euismod elementum nisi quis eleifend quam. Dictum fusce ut placerat orci nulla pellentesque dignissim.");
            p.setCreator("admin");
            this.postRepository.save(p);
        }
    }
}
