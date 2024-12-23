package br.com.music.app.musicapp.api.config.client.response;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumsSpotifyResponse {
    @FormProperty("album_type")
    private String albumType;
    @FormProperty("total_tracks")
    private int totalTracks;
    @FormProperty("name")
    private String name;
    @FormProperty("href")
    private String href;
    @FormProperty("artists")
    private List<String> artists;
}
