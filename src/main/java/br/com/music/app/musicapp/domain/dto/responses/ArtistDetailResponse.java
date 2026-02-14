package br.com.music.app.musicapp.domain.dto.responses;

import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtistDetailResponse {
    private ArtistsSpotifyResponse detail;
    private List<AlbunsResponse> albums;
}
