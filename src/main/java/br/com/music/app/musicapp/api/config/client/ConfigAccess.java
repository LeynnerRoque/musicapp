package br.com.music.app.musicapp.api.config.client;

import br.com.music.app.musicapp.business.services.AuthService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class ConfigAccess{

    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String BEARER = "Bearer ";

    @Bean
    public RequestInterceptor getAccess(){
        var auth = new AuthService();
        var token = auth.token();
       return requestTemplate -> {
           String accesstoken = token;
           requestTemplate.header(AUTHORIZATION_HEADER,BEARER+accesstoken);
       };
    }

}
