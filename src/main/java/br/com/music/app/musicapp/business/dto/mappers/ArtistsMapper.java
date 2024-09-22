package br.com.music.app.musicapp.business.dto.mappers;

import br.com.music.app.musicapp.business.dto.requests.ArtistsRequest;
import br.com.music.app.musicapp.business.dto.responses.ArtistsResponse;
import br.com.music.app.musicapp.business.repository.RecordRepository;
import br.com.music.app.musicapp.business.util.DateConverters;
import br.com.music.app.musicapp.models.Artists;
import br.com.music.app.musicapp.models.Record;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Component
public class ArtistsMapper {

    @Autowired
    private RecordRepository repository;

    @Autowired
    private DateConverters converters;

    public ArtistsResponse toResponse(Artists entity){
        var response = new ArtistsResponse();
        BeanUtils.copyProperties(entity,response);
        var dateFormat = converters.convertToFormat(entity.getDateCreate());
        response.setRecord(entity.getRecordByRecordId().getName());
        response.setDateCreated(dateFormat);
        return response;
    }


    public Artists fromRequesttoEntity(ArtistsRequest request){
        var entity = new Artists();
        BeanUtils.copyProperties(request,entity);
        var recordBy = repository.findRecordByName(request.record());
        if(recordBy == null){
            var record = new Record();
            record.setName(request.record());
            recordBy = repository.save(record);
        }
        var dateCreated = converters.convertToDate(request.created());
        entity.setRecordByRecordId(recordBy);
        entity.setDateCreate(dateCreated);
        return entity;
    }

    public Artists toEntity(ArtistsResponse response){
        var entity = new Artists();
        BeanUtils.copyProperties(response,entity);
        var recordBy = repository.findRecordByName(response.getRecord());
        var dateCreated = converters.convertToDate(response.getDateCreated());
        entity.setRecordByRecordId(recordBy);
        entity.setDateCreate(dateCreated);
        return entity;
    }


    public List<ArtistsResponse> toList(List<Artists> models){
        return models.stream()
                .filter(Objects::nonNull)
                .map(this::toResponse)
                .toList();
    }


}
