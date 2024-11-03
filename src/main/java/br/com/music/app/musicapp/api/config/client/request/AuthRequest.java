package br.com.music.app.musicapp.api.config.client.request;

import feign.form.FormProperty;

public record AuthRequest(
        @FormProperty("grant-type")
        String grantType,
        @FormProperty("client_id")
        String clientId,
        @FormProperty("client_secret")
        String secret
) {
}
