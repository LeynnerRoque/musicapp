package br.com.music.app.musicapp.business.util.converters;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ListConvert {

    public List<String> toList(String name, JsonObject object){
        return Arrays.stream(object.get(name)
                .toString()
                .replace("]","")
                .replace("[","")
                .replace("\"","")
                .split(",")).toList();
    }

    public List<String> getPropertyValue(List<String> values, String propertyName){
        List<String> valuesResult= new ArrayList<>();

        for (Object value : values){
            if(value.toString().contains(propertyName)){
                valuesResult.add(value.toString().replace(propertyName,""));
            }
        }
        return valuesResult;
    }
}
