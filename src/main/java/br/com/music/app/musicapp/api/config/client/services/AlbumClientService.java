package br.com.music.app.musicapp.api.config.client.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "albunsspotify",
        url = "${spring.feign.spotify.url.base}")
public interface AlbumClientService {

    @GetMapping("/albums/{id}")
    String getAlbumsBySpotifyName(@PathVariable("id") String id);
}
