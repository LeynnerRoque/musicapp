package br.com.music.app.musicapp.api.config.client.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "artistsspotify",
        url = "${spring.feign.spotify.url.base}")
public interface ArtistsClientService {

    @GetMapping("/artists/{id}")
    String getArtistsBySpotify(@PathVariable("id") String id);
}
