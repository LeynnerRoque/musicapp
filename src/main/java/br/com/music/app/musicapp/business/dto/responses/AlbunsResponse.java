package br.com.music.app.musicapp.business.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbunsResponse {

    private Long id;
    private String name;
    private String artistsName;
    private String style;
}
