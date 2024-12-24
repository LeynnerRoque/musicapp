package br.com.music.app.musicapp.api.config.client.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumsSpotifyResponse {

    private String albumType;
    private int totalTracks;
    private String name;
    private String href;
    private List<String> artists;
}
