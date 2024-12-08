package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.business.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotify")
public class SpotifyController {

    private final AuthService service;

    @Autowired
    public SpotifyController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/token")
    public ResponseEntity<String> returnToken(){
        return ResponseEntity.ok(service.token());
    }
}
