package br.com.music.app.musicapp.api.config.client.mappers;

import br.com.music.app.musicapp.api.config.client.response.AlbumsSpotifyResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;


@Service
public class AlbumSpotifyMapper {

    public AlbumsSpotifyResponse toResponse(String responseByApi){
        JsonObject object = new Gson().fromJson(responseByApi, JsonObject.class);
        AlbumsSpotifyResponse response = new AlbumsSpotifyResponse();
        response.setAlbumType(object.get("album_type").getAsString());
        response.setTotalTracks(object.get("total_tracks").getAsInt());
        response.setName(object.get("name").getAsString());
        return response;
    }



}
