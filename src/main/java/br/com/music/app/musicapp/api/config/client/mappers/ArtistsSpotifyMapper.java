package br.com.music.app.musicapp.api.config.client.mappers;

import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import br.com.music.app.musicapp.business.util.converters.ListConvert;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


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

        var images = listConvert.toImageResponseList("images",object);
        response.setImages(images);

        response.setPopularity(object.get("popularity").getAsInt());
        response.setUri(object.get("uri").getAsString());

        return response;
    }

    public ArtistsSpotifyResponse toResponseFromAlbum(List<String> values){
        ArtistsSpotifyResponse response = new ArtistsSpotifyResponse();
        response.setName(listConvert.getPropertyValue(values,"name:"));
        response.setId(listConvert.getPropertyValue(values,"id:"));
        response.setType(listConvert.getPropertyValue(values,"type:"));
        response.setHref(listConvert.getPropertyValue(values,"href:"));
        return response;
    }
}
