package fr.dorianmaliszewski.app.oauth2resource.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String title;
    private String body;
}
