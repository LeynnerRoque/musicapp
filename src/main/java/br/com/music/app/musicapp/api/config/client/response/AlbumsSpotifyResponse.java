package br.com.music.app.musicapp.api.config.client.response;

import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumsSpotifyResponse {
    @FormProperty("album_type")
    private String albumType;
}
