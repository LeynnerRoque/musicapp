package br.com.music.app.musicapp.api.config.client.mappers;

import br.com.music.app.musicapp.api.config.client.response.AlbumsSpotifyResponse;
import br.com.music.app.musicapp.business.util.converters.ListConvert;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AlbumSpotifyMapper {

    private final ListConvert listConvert;

    private final ArtistsSpotifyMapper mapper;

    @Autowired
    public AlbumSpotifyMapper(ListConvert listConvert, ArtistsSpotifyMapper mapper) {
        this.listConvert = listConvert;
        this.mapper = mapper;
    }


    public AlbumsSpotifyResponse toResponse(String responseByApi){
        JsonObject object = new Gson().fromJson(responseByApi, JsonObject.class);
        AlbumsSpotifyResponse response = new AlbumsSpotifyResponse();
        response.setAlbumType(object.get("album_type").getAsString());
        response.setTotalTracks(object.get("total_tracks").getAsInt());
        response.setName(object.get("name").getAsString());
        response.setHref(object.get("href").getAsString());
        var artists = listConvert.toList("artists", object);
        response.setArtists(mapper.toResponseFromAlbum(artists));
        return response;
    }



}
