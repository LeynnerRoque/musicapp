package br.com.music.app.musicapp.api.config.client.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    @JsonProperty("access_token")
    private String token;
}
