package br.com.music.app.musicapp.domain.dto.requests;

public record ArtistsRequest(
        String name,
        String type,
        String created,
        String origin,
        String record
) {
}
