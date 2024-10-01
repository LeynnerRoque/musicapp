package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.business.dto.mappers.ArtistsMapper;
import br.com.music.app.musicapp.business.dto.requests.ArtistsRequest;
import br.com.music.app.musicapp.business.dto.responses.ArtistsResponse;
import br.com.music.app.musicapp.business.repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistsService {
    @Autowired
    private ArtistsRepository repository;

    @Autowired
    private ArtistsMapper mapper;
    public ArtistsResponse create(ArtistsRequest request){
        try{
            var entity = repository.save(mapper.fromRequesttoEntity(request));
            return mapper.toResponse(entity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArtistsResponse update(ArtistsResponse response){
        try{
            var entity = repository.save(mapper.toEntity(response));
            return mapper.toResponse(entity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public ArtistsResponse findById(Long id){
        return mapper.toResponse(repository.findById(id).get());
    }

    public List<ArtistsResponse> listAll(){
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
