package br.com.music.app.musicapp.api.config.client;

import br.com.music.app.musicapp.api.config.client.request.AuthRequest;
import br.com.music.app.musicapp.api.config.client.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "spotifyauth", url = "https://accounts.spotify.com/api/token")
public interface AuthSpotify {

    @PostMapping
    AuthResponse getAccess(@RequestBody AuthRequest request);
}
