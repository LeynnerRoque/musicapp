package br.com.music.app.musicapp.domain.dto.requests;

public record AlbunsRequest(
        String name,
        String style,
        String artists
) {
}
