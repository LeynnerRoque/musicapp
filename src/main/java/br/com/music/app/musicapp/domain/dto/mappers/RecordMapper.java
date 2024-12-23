package br.com.music.app.musicapp.domain.dto.mappers;

import br.com.music.app.musicapp.domain.dto.responses.RecordResponse;
import br.com.music.app.musicapp.domain.models.Record;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RecordMapper {

    public RecordResponse toResponse(Object o){
        var response = new RecordResponse();
        BeanUtils.copyProperties(o, response);
        return response;
    }
    public Record toEntity(Object o){
        var entity = new Record();
        BeanUtils.copyProperties(o,entity);
        return entity;
    }

    public Record fromRequesttoEntity(Object o){return toEntity(o);}

    public List<RecordResponse> toList(List models){
        return models.stream()
                .filter(Objects::nonNull)
                .map(this::toResponse).toList();
    }
}
