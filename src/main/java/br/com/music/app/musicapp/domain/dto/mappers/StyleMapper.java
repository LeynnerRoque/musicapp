package br.com.music.app.musicapp.domain.dto.mappers;

import br.com.music.app.musicapp.domain.dto.responses.StyleResponse;
import br.com.music.app.musicapp.domain.models.Style;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class StyleMapper{

    public StyleResponse toResponse(Object o) {
        var response =new StyleResponse();
        BeanUtils.copyProperties(o,response);
        return response;
    }


    public Style toEntity(Object o) {
        var entity = new Style();
        BeanUtils.copyProperties(o, entity);
        return entity;
    }

    public Style fromRequestTomodel(Object o) {
        return toEntity(o);
    }

    public List<StyleResponse> toList(List list) {
        return list.stream().filter(Objects::nonNull).map(this::toResponse).toList();
    }
}
