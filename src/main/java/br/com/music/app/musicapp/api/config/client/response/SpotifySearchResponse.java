package br.com.music.app.musicapp.api.config.client.response;

import br.com.music.app.musicapp.api.config.client.response.composes.ArtistItems;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotifySearchResponse {
    private ArtistItems artistItems;
}
