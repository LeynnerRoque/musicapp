package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.api.config.client.request.SendToFind;
import br.com.music.app.musicapp.api.config.client.response.AlbumsSpotifyResponse;
import br.com.music.app.musicapp.domain.dto.requests.AlbunsRequest;
import br.com.music.app.musicapp.domain.dto.responses.AlbunsResponse;
import br.com.music.app.musicapp.business.services.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albuns")
public class AlbumsController {

    @Autowired
    private AlbumsService service;

    @GetMapping("/all")
    public ResponseEntity<List<AlbunsResponse>> listAll(){
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/code-spotify/{id}")
    public ResponseEntity<AlbumsSpotifyResponse> getBySpotifyName(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getBySpotifyName(id));
    }

    @PostMapping("/create-by-spotify/")
    public ResponseEntity<AlbunsResponse> createBySpotify(@RequestBody SendToFind request){
        return ResponseEntity.ok(service.createBySpotify(request.codeSpotify()));
    }
}
