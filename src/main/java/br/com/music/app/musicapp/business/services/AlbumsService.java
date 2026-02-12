package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.api.config.client.mappers.AlbumSpotifyMapper;
import br.com.music.app.musicapp.api.config.client.response.AlbumsSpotifyResponse;
import br.com.music.app.musicapp.api.config.client.services.AlbumClientService;
import br.com.music.app.musicapp.domain.dto.mappers.AlbunsMapper;
import br.com.music.app.musicapp.domain.dto.requests.AlbunsRequest;
import br.com.music.app.musicapp.domain.dto.responses.AlbunsResponse;
import br.com.music.app.musicapp.domain.repository.AlbunsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AlbumsService {

    private final AlbunsRepository repository;
    private final AlbunsMapper mapper;
    private final AlbumClientService albunClientService;
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
        } catch (RuntimeException e) {
            log.warn("Error on create: {}",e.getMessage());
            return new AlbunsResponse();
        }

    }

    public List<AlbunsResponse> listAll(){
        return mapper.toList(repository.findAll());
    }

    public AlbumsSpotifyResponse getBySpotifyName(String id){
        try{
            var response = albunClientService.getAlbumsBySpotifyName(id);
            return response != null ? spotifyMapper.toResponse(response) : new AlbumsSpotifyResponse();
        }catch (Exception e){
            log.warn("Error on Search. Cause: {}", e.getMessage());
           return new AlbumsSpotifyResponse();
        }
    }


    public AlbunsResponse createBySpotify(String codeSpotify){
        try{
            var responseSpotify = getBySpotifyName(codeSpotify);

            if(artistsService.findByName(responseSpotify.getArtists().getName()) == null){
                artistsService.createBySpotify(responseSpotify.getArtists().getId());
            }
            var artists = artistsService.findByName(responseSpotify.getArtists().getName());

            var request = new AlbunsRequest(
                    responseSpotify.getName(),
                    artists.getGenres().getFirst(),
                    responseSpotify.getArtists().getName()
            );

            return create(request);
        } catch (Exception e) {
            log.warn("Error on create, {}",e.getMessage());
            return new AlbunsResponse();
        }
    }

}
