package br.com.music.app.musicapp.api.config.client;

import br.com.music.app.musicapp.api.config.client.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class ConfigAccess {

    private final String clientId = "cbcb5362c4cb45afa8fd954d6af69e65";
    private final String secret = "30aeb35013e74aa6ba36e3bc80aa68d4";
    private final String grantType = "client_credentials";

    @Autowired
    private AuthSpotify spotify;


    public String getAccess(){
        var request = new AuthRequest(grantType,clientId,secret);
        var response = spotify.getAccess(request);
        System.out.println(response.getToken());
        return response.getToken();
    }
}
