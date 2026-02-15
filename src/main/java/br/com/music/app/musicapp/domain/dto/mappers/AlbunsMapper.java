package br.com.music.app.musicapp.domain.dto.mappers;

import br.com.music.app.musicapp.domain.dto.requests.AlbunsRequest;
import br.com.music.app.musicapp.domain.dto.responses.AlbunsResponse;
import br.com.music.app.musicapp.domain.repository.ArtistsRepository;
import br.com.music.app.musicapp.domain.repository.StyleRepository;
import br.com.music.app.musicapp.domain.models.Albuns;
import br.com.music.app.musicapp.domain.models.Style;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AlbunsMapper {

    @Autowired
    private ArtistsRepository repository;

    @Autowired
    private StyleRepository styleRepository;

    public AlbunsResponse toResponse(Albuns entity){
        var response = new AlbunsResponse();
        BeanUtils.copyProperties(entity,response);
        response.setArtistsName(entity.getArtistsByArtistsId().getName());
        response.setStyle(entity.getStyleByStyleId().getNameStyle());
        return response;
    }

    public Albuns toEntity(AlbunsRequest request){
        var entity = new Albuns();
        BeanUtils.copyProperties(request,entity);
        var artists = repository.findArtistsByName(request.artists());
        var listAlbums = styleRepository.findStyleByNameStyle(request.style());
        var style = listAlbums.isEmpty() ? null : listAlbums.getFirst();

        if(style == null){
            var entityStyle = new Style();
            entityStyle.setNameStyle(request.style());
            style = styleRepository.save(entityStyle);
        }
        entity.setArtistsByArtistsId(artists);
        entity.setStyleByStyleId(style);
        return entity;
    }

    public Albuns fromResponseToEntity(AlbunsResponse response){
        var entity = new Albuns();
        BeanUtils.copyProperties(response,entity);
        var artists = repository.findArtistsByName(response.getArtistsName());
        var style = styleRepository.findStyleByNameStyle(response.getStyle()).getFirst();
        entity.setArtistsByArtistsId(artists);
        entity.setStyleByStyleId(style);
        return entity;
    }

    public List<AlbunsResponse> toList(List<Albuns> albuns){
        return albuns.stream().filter(Objects::nonNull).map(this::toResponse).toList();
    }


}
