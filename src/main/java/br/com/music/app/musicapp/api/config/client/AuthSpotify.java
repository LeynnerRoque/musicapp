package br.com.music.app.musicapp.api.config.client;

import br.com.music.app.musicapp.api.config.client.request.CredentialsRequest;
import br.com.music.app.musicapp.api.config.client.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "spotifyauth",
        url = "https://accounts.spotify.com")
public interface AuthSpotify {

    @PostMapping(value = "/api/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AuthResponse getAccess(@RequestBody CredentialsRequest request);
}
