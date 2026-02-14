package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.domain.dto.mappers.RecordMapper;
import br.com.music.app.musicapp.domain.dto.requests.RecordRequest;
import br.com.music.app.musicapp.domain.dto.responses.RecordResponse;
import br.com.music.app.musicapp.domain.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordMapper mapper;

    @Autowired
    private RecordRepository repository;


    public RecordResponse create(RecordRequest request){
        try{
            var existsRecord = findByName(request.name());
            if(existsRecord != null){
                return existsRecord;
            }
            var entity = repository.save(mapper.toEntity(request));
            return mapper.toResponse(entity);
        }catch (Exception e){
            return null;
        }
    }

    public List<RecordResponse> listAll(){
        return mapper.toList(repository.findAll());
    }

    public RecordResponse findByName(String name){
        return mapper.toResponse(repository.findRecordByName(name));
    }


    public String delete(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "api.service.log.success.remove";
        }else{
            return "api.service.log.error.not.found";
        }
    }
}
