package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.api.config.client.request.SendToFind;
import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import br.com.music.app.musicapp.domain.dto.requests.ArtistsRequest;
import br.com.music.app.musicapp.domain.dto.responses.ArtistsResponse;
import br.com.music.app.musicapp.business.services.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistsController {

    @Autowired
    private ArtistsService service;


    @GetMapping("/all")
    public ResponseEntity<List<ArtistsResponse>> listAll(){
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/code-spotify/{id}")
    public ResponseEntity<ArtistsSpotifyResponse> getBySpotify(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getBySpotify(id));
    }

    @PostMapping("/create-by-spotify/")
    public ResponseEntity<ArtistsResponse> createBySpotify(@RequestBody SendToFind codeSpotify){
        return ResponseEntity.ok(service.createBySpotify(codeSpotify.codeSpotify()));
    }

    @GetMapping("/find-name/{name}")
    public ResponseEntity<ArtistsSpotifyResponse> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(service.findByName(name));
    }

}
