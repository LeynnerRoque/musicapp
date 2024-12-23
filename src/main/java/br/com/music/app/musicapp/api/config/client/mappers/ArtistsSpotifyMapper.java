package br.com.music.app.musicapp.api.config.client.mappers;

import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import br.com.music.app.musicapp.business.util.converters.ListConvert;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ArtistsSpotifyMapper {

    @Autowired
    private ListConvert listConvert;

    public ArtistsSpotifyResponse toResponse(String responseByApi){
        JsonObject object = new Gson().fromJson(responseByApi, JsonObject.class);
        var response = new ArtistsSpotifyResponse();
        response.setName(object.get("name").getAsString());
        response.setId(object.get("id").getAsString());
        response.setHref(object.get("href").getAsString());
        response.setType(object.get("type").getAsString());

        var genres = listConvert.toList("genres", object);

        response.setGenres(genres);

        return response;
    }
}
