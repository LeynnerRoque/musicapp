package br.com.music.app.musicapp.api.config.client.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "tracksspotify",
        url = "${spring.feign.spotify.url.base}")
public interface TrackClientService {

    @GetMapping("/tracks/{id}")
    String getTracksBySpotifyName(@PathVariable("id") String id);
}
