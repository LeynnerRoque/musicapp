package br.com.music.app.musicapp.api.config.client.services;

import br.com.music.app.musicapp.api.config.client.response.AlbumsSpotifyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "albunsspotify",
        url = "https://api.spotify.com/v1")
public interface AlbunClientService {

    @GetMapping("/albums/{id}")
    String getAlbumsBySpotifyName(@PathVariable("id") String id);
}
