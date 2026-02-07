package br.com.music.app.musicapp.api.config.client.response;

import br.com.music.app.musicapp.api.config.client.response.composes.ImageSpotifyResponse;
import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtistsSpotifyResponse {
    @FormProperty("href")
    private String href;
    @FormProperty("id")
    private String id;
    @FormProperty("name")
    private String name;
    @FormProperty("type")
    private String type;
    @FormProperty("genres")
    private List<String> genres;
    @FormProperty("images")
    private List<ImageSpotifyResponse> images;
    @FormProperty("popularity")
    private Integer popularity;
    @FormProperty("uri")
    private String uri;

}
