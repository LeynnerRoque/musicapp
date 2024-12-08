package br.com.music.app.musicapp.api.config.client;

import br.com.music.app.musicapp.business.services.AuthService;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAccess{

    @Autowired
    private AuthService authService;
    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String BEARER = "Bearer ";

    @Bean
    public RequestInterceptor getSpotifyAuthenticationAccess(){
        var token = authService.token();
       return requestTemplate -> {
           String accesstoken = token;
           requestTemplate.header(AUTHORIZATION_HEADER,BEARER+accesstoken);
       };
    }

}
