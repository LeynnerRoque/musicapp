package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.business.dto.mappers.AlbunsMapper;
import br.com.music.app.musicapp.business.dto.requests.AlbunsRequest;
import br.com.music.app.musicapp.business.dto.responses.AlbunsResponse;
import br.com.music.app.musicapp.business.repository.AlbunsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbunsService {

    @Autowired
    private AlbunsRepository repository;

    @Autowired
    private AlbunsMapper mapper;

    public AlbunsResponse create(AlbunsRequest request){
        try{
            var entity = repository.save(mapper.toEntity(request));
            return mapper.toResponse(entity);
        }catch (Exception e){
            return null;
        }
    }

    public AlbunsResponse update(AlbunsResponse response){
        try{
            var entity = repository.save(mapper.fromResponseToEntity(response));
            return mapper.toResponse(entity);
        }catch (Exception e){
            return null;
        }
    }

    public AlbunsResponse findById(Long id){
        return mapper.toResponse(repository.findById(id).get());
    }


    public List<AlbunsResponse> listAll(){
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
