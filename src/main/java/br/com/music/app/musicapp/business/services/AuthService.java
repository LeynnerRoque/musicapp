package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.api.config.client.AuthSpotify;
import br.com.music.app.musicapp.api.config.client.request.CredentialsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthSpotify service;

    @Value("${auth.spotify.clientid}")
    private String clientId;
    @Value("${auth.spotify.secret}")
    private String secret;
    @Value("${auth.spotify.granttype}")
    private String grantType;

    public String token(){
        var request = new CredentialsRequest(grantType,clientId,secret);
        return service.getAccess(request).getToken();
    }

}
