package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.domain.dto.mappers.StyleMapper;
import br.com.music.app.musicapp.domain.dto.requests.StyleRequest;
import br.com.music.app.musicapp.domain.dto.responses.StyleResponse;
import br.com.music.app.musicapp.domain.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StyleService {

    @Autowired
    private StyleMapper mapper;

    @Autowired
    private StyleRepository repository;

    public StyleResponse create(StyleRequest request){
        try{
            var existStyle = findByName(request.nameStyle());
            if(existStyle != null){
                return existStyle;
            }
            var entity = repository.save(mapper.fromRequestTomodel(request));
            return mapper.toResponse(entity);
        }catch (Exception e){
            return new StyleResponse();
        }
    }

    public List<StyleResponse> findAll(){
        return mapper.toList(repository.findAll());
    }

    private StyleResponse findByName(String name){
        return mapper.toResponse(repository.findStyleByNameStyle(name));
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
