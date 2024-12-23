package br.com.music.app.musicapp.api.config.client.response;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;

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

}
