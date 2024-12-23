package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.api.config.client.mappers.ArtistsSpotifyMapper;
import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import br.com.music.app.musicapp.api.config.client.services.ArtistsClientService;
import br.com.music.app.musicapp.domain.dto.mappers.ArtistsMapper;
import br.com.music.app.musicapp.domain.dto.requests.ArtistsRequest;
import br.com.music.app.musicapp.domain.dto.responses.ArtistsResponse;
import br.com.music.app.musicapp.domain.repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ArtistsService {

    private final ArtistsRepository repository;
    private final ArtistsMapper mapper;
    private final ArtistsClientService clientService;
    private final ArtistsSpotifyMapper spotifyMapper;

    @Autowired
    public ArtistsService(ArtistsRepository repository, ArtistsMapper mapper, ArtistsClientService clientService, ArtistsSpotifyMapper spotifyMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.clientService = clientService;
        this.spotifyMapper = spotifyMapper;
    }


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

    public ArtistsSpotifyResponse getBySpotify(String id){
        try{
            var response = clientService.getArtistsBySpotify(id);
            return spotifyMapper.toResponse(response);
        }catch (Exception e){
            return null;
        }
    }


    public ArtistsResponse createBySpotify(String codeSpotify){
        try{
            var artistSpotify = spotifyMapper.toResponse(clientService.getArtistsBySpotify(codeSpotify));
            var request = new ArtistsRequest(
                    artistSpotify.getName(),
                    artistSpotify.getType(),
                    "23/12/2024" ,
                    artistSpotify.getHref(),
                    "Unknow Record"
            );
            var entity = repository.save(mapper.fromRequesttoEntity(request));
            return mapper.toResponse(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
