package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.api.config.client.mappers.ArtistsSpotifyMapper;
import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import br.com.music.app.musicapp.api.config.client.services.ArtistsClientService;
import br.com.music.app.musicapp.business.util.converters.DateConverters;
import br.com.music.app.musicapp.domain.dto.mappers.ArtistsMapper;
import br.com.music.app.musicapp.domain.dto.requests.ArtistsRequest;
import br.com.music.app.musicapp.domain.dto.requests.StyleRequest;
import br.com.music.app.musicapp.domain.dto.responses.ArtistDetailResponse;
import br.com.music.app.musicapp.domain.dto.responses.ArtistsResponse;
import br.com.music.app.musicapp.domain.repository.ArtistsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ArtistsService {

    private final ArtistsRepository repository;
    private final ArtistsMapper mapper;
    private final ArtistsClientService clientService;
    private final ArtistsSpotifyMapper spotifyMapper;
    private final DateConverters converters;
    private final StyleService styleService;
    private final AlbumsService albumsService;
    //private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ArtistsService(ArtistsRepository repository, ArtistsMapper mapper, ArtistsClientService clientService, ArtistsSpotifyMapper spotifyMapper, DateConverters converters, StyleService styleService, @Lazy AlbumsService albumsService) {
        this.repository = repository;
        this.mapper = mapper;
        this.clientService = clientService;
        this.spotifyMapper = spotifyMapper;
        this.converters = converters;
        this.styleService = styleService;
        this.albumsService = albumsService;
    }


    public ArtistsResponse create(ArtistsRequest request){
        try{
            var entity = repository.save(mapper.fromRequestToEntity(request));
            return mapper.toResponse(entity);
        }catch (Exception e){
            return null;
        }
    }

    public ArtistsResponse update(ArtistsResponse response){
        try{
            var entity = repository.save(mapper.toEntity(response));
            return mapper.toResponse(entity);
        }catch (Exception e){
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

    public ArtistsSpotifyResponse findByName(String name){
        try{
            var entity = repository.findArtistsByName(name);
            return getBySpotify(entity.getSpotifyCode());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public ArtistsSpotifyResponse getBySpotify(String id){
        return spotifyMapper.toResponse(clientService.getArtistsBySpotify(id));
    }


    public ArtistsResponse createBySpotify(String codeSpotify){
        try{
            var artistSpotify = spotifyMapper.toResponse(clientService.getArtistsBySpotify(codeSpotify));
            var inputDate = converters.convertToFormatbyInstant(new Date());

            var request = new ArtistsRequest(
                    artistSpotify.getName(),
                    artistSpotify.getType(),
                    inputDate ,
                    artistSpotify.getHref(),
                    "Unknow Record"
            );

            artistSpotify.getGenres().stream()
                    .filter(Objects::nonNull)
                    .forEach(s -> styleService.create(new StyleRequest(s)));

            var entity = mapper.fromRequestToEntity(request);
            entity.setSpotifyCode(codeSpotify);
            repository.save(entity);

            return mapper.toResponse(entity);
        } catch (Exception e) {
            return null;
        }
    }

    public ArtistDetailResponse details(String name){
        var artist = findByName(name);
        var artistBase = repository.findArtistsByName(name);
        ArtistDetailResponse detail = new ArtistDetailResponse();
        detail.setDetail(artist);
        detail.setAlbums(albumsService.buildList(artistBase.getId()));
        return detail;
    }

}
