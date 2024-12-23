package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.api.config.client.mappers.AlbumSpotifyMapper;
import br.com.music.app.musicapp.api.config.client.response.AlbumsSpotifyResponse;
import br.com.music.app.musicapp.api.config.client.services.AlbunClientService;
import br.com.music.app.musicapp.api.config.client.services.TrackClientService;
import br.com.music.app.musicapp.business.dto.mappers.AlbunsMapper;
import br.com.music.app.musicapp.business.dto.requests.AlbunsRequest;
import br.com.music.app.musicapp.business.dto.responses.AlbunsResponse;
import br.com.music.app.musicapp.business.repository.AlbunsRepository;
import br.com.music.app.musicapp.business.services.messages.KafkaProducerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbunsService {

    private final AlbunsRepository repository;
    private final AlbunsMapper mapper;
    private final AlbunClientService albunClientService;
    private final TrackClientService trackClientService;
    private final KafkaProducerService kafkaProducerService;
    private final AlbumSpotifyMapper spotifyMapper;

    @Autowired
    public AlbunsService(AlbunsRepository repository, AlbunsMapper mapper, AlbunClientService albunClientService, TrackClientService trackClientService, KafkaProducerService kafkaProducerService, AlbumSpotifyMapper spotifyMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.albunClientService = albunClientService;
        this.trackClientService = trackClientService;
        this.kafkaProducerService = kafkaProducerService;
        this.spotifyMapper = spotifyMapper;
    }


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

    public String getBySpotifyName(String id){
        try{
            var response = albunClientService.getAlbumsBySpotifyName(id);
            if(!response.isBlank()){
                var message = "Get Album in API Spotify: ";
                var albumConvert = spotifyMapper.toResponse(response);
                kafkaProducerService.sendMessage("consulta-api-spotify",message + albumConvert.getName());
            }else{
                var messageError = "Get Error on Album in API Spotify: "+ id;
                kafkaProducerService.sendMessage("consulta-api-spotify",messageError);
            }
            return response;
        }catch (Exception e){
           return null;
        }
    }


    public String getByTracksSpotify(String id){
        try{
            var response = trackClientService.getTracksBySpotifyName(id);
            if(!response.isBlank()){
                var message = "Get Album/Track in API Spotify: ";
                kafkaProducerService.sendMessage("consulta-api-spotify",message+response);
            }else{
                var messageError = "Error on get Album/Track in API Spotify: ";
                kafkaProducerService.sendMessage("consulta-api-spotify",messageError+id);
            }
            return response;
        }catch (Exception e){
            return null;
        }
    }
}
