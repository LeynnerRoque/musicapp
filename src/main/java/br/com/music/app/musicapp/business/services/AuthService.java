package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.api.config.client.AuthSpotify;
import br.com.music.app.musicapp.api.config.client.request.CredentialsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthSpotify service;

    private String clientId = "cbcb5362c4cb45afa8fd954d6af69e65";
    private String secret = "30aeb35013e74aa6ba36e3bc80aa68d4";
    private String grantType = "client_credentials";

    public String token(){
        var request = new CredentialsRequest(grantType,clientId,secret);
        return service.getAccess(request).getToken();
    }

}
