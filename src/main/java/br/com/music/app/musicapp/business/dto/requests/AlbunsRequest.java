package br.com.music.app.musicapp.business.dto.requests;

public record AlbunsRequest(
        String name,
        String style,
        String artists
) {
}
