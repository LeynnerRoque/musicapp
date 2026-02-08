package br.com.music.app.musicapp.business.util.converters;

import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import br.com.music.app.musicapp.api.config.client.response.composes.ImageSpotifyResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ListConvert {

    private final Gson gson = new Gson();

    public List<String> toList(String name, JsonObject object){
        return Arrays.stream(object.get(name)
                .toString()
                .replace("]","")
                .replace("[","")
                .replace("\"","")
                .split(",")).toList();
    }

    public String getPropertyValue(List<String> values, String propertyName){
        String valuesResult = "";

        for (Object value : values){
            if(value.toString().contains(propertyName)){
                valuesResult = value.toString().replace(propertyName,"");
            }
        }
        return valuesResult;
    }

    public List<ImageSpotifyResponse> toImageResponseList(String name, JsonObject object) {
        if (object.get(name) == null || object.get(name).isJsonNull()) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<ImageSpotifyResponse>>(){}.getType();
        return gson.fromJson(object.get(name), listType);
    }


    public List<ArtistsSpotifyResponse> toSearchResponse(String responseByApi) {
        JsonObject root = new Gson().fromJson(responseByApi, JsonObject.class);

        JsonObject artistsObj = root.getAsJsonObject("artists");
        JsonArray itemsArray = artistsObj.getAsJsonArray("items");

        List<ArtistsSpotifyResponse> results = new ArrayList<>();

        itemsArray.forEach(element -> {
            JsonObject artistJson = element.getAsJsonObject();
            var artist = new ArtistsSpotifyResponse();

            artist.setName(artistJson.get("name").getAsString());
            artist.setId(artistJson.get("id").getAsString());
            artist.setType(artistJson.get("type").getAsString());
            artist.setUri(artistJson.get("uri").getAsString());

            artist.setGenres(toList("genres", artistJson));
            artist.setImages(toImageResponseList("images", artistJson));

            results.add(artist);
        });

        return results;
    }
}
