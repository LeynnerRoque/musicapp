package br.com.music.app.musicapp.api.config.client.services;

import br.com.music.app.musicapp.api.config.client.response.SpotifySearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "spotify-search", url = "${spring.feign.spotify.url.base}")
public interface SpotifySearchClient {

    @GetMapping("/search")
    SpotifySearchResponse searchArtist(
        @RequestParam("q") String query,
        @RequestParam("type") String type
    );
}