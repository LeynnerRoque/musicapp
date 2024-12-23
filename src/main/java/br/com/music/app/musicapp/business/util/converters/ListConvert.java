package br.com.music.app.musicapp.business.util.converters;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ListConvert {

    public List toList(String name, JsonObject object){
        return Arrays.stream(object.get(name)
                .toString()
                .replace("]","")
                .replace("[","")
                .replace("\"","")
                .split(",")).toList();
    }
}
