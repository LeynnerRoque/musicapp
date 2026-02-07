package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.api.config.client.mappers.AlbumSpotifyMapper;
import br.com.music.app.musicapp.api.config.client.response.AlbumsSpotifyResponse;
import br.com.music.app.musicapp.api.config.client.services.AlbumClientService;
import br.com.music.app.musicapp.domain.dto.mappers.AlbunsMapper;
import br.com.music.app.musicapp.domain.dto.requests.AlbunsRequest;
import br.com.music.app.musicapp.domain.dto.responses.AlbunsResponse;
import br.com.music.app.musicapp.domain.repository.AlbunsRepository;
import br.com.music.app.musicapp.business.services.messages.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumsService {

    private final AlbunsRepository repository;
    private final AlbunsMapper mapper;
    private final AlbumClientService albunClientService;
    //private final KafkaProducerService kafkaProducerService;
    private final AlbumSpotifyMapper spotifyMapper;
    private final ArtistsService artistsService;

    @Autowired
    public AlbumsService(AlbunsRepository repository, AlbunsMapper mapper, AlbumClientService albunClientService, AlbumSpotifyMapper spotifyMapper, ArtistsService artistsService) {
        this.repository = repository;
        this.mapper = mapper;
        this.albunClientService = albunClientService;
        this.spotifyMapper = spotifyMapper;
        this.artistsService = artistsService;
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

    public AlbumsSpotifyResponse getBySpotifyName(String id){
        try{
            var response = albunClientService.getAlbumsBySpotifyName(id);
            if(!response.isBlank()){
                var message = "Get Album in API Spotify: ";
                var albumConvert = spotifyMapper.toResponse(response);
               // kafkaProducerService.sendMessage("consulta-api-spotify",message + albumConvert.getName());
                return albumConvert;
            }else{
                var messageError = "Get Error on Album in API Spotify: "+ id;
                //kafkaProducerService.sendMessage("consulta-api-spotify",messageError);
                return null;
            }
        }catch (Exception e){
           return null;
        }
    }


    public AlbunsResponse createBySpotify(String codeSpotify){
        try{
            var responseSpotify = getBySpotifyName(codeSpotify);
            var artists = artistsService.createBySpotify(responseSpotify.getArtists().getId());
            var type = artistsService.getBySpotify(responseSpotify.getArtists().getId());

            var request = new AlbunsRequest(
                    responseSpotify.getName(),
                    type.getGenres().getFirst(),
                    artists.getName()
            );

            var saved = repository.save(mapper.toEntity(request));
//            if(saved!=null){
//                kafkaProducerService.sendMessage("database-saved","create item on database");
//            }else{
//                kafkaProducerService.sendMessage("database-saved", "dont create database item");
//            }
            return mapper.toResponse(saved);
        } catch (Exception e) {
            return null;
        }
    }

}
