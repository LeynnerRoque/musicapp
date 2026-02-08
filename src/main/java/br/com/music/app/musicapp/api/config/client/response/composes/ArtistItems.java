package br.com.music.app.musicapp.api.config.client.response.composes;

import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtistItems {
    private List<ArtistsSpotifyResponse> items;
}