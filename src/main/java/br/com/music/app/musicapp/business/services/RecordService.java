package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.business.dto.mappers.RecordMapper;
import br.com.music.app.musicapp.business.dto.requests.RecordRequest;
import br.com.music.app.musicapp.business.dto.responses.RecordResponse;
import br.com.music.app.musicapp.business.repository.RecordRepository;
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
            var entity = repository.save(mapper.toEntity(request));
            return mapper.toResponse(entity);
        }catch (Exception e){
            return null;
        }
    }

    public RecordResponse update(RecordResponse response){
        try{
            var entity = repository.save(mapper.toEntity(response));
            return mapper.toResponse(entity);
        }catch (Exception e){
            return null;
        }
    }

    public RecordResponse findById(Long id){
        try{
            return mapper.toResponse(repository.findById(id).get());
        }catch (Exception e){
            return null;
        }
    }

    public List<RecordResponse> listAll(){
        return mapper.toList(repository.findAll());
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
