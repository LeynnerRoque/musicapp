package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.api.config.client.response.ArtistsSpotifyResponse;
import br.com.music.app.musicapp.business.dto.requests.ArtistsRequest;
import br.com.music.app.musicapp.business.dto.responses.ArtistsResponse;
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

    @PostMapping
    public ResponseEntity<ArtistsResponse> create(@RequestBody ArtistsRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping
    public ResponseEntity<ArtistsResponse> update(@RequestBody ArtistsResponse response){
        return ResponseEntity.ok(service.update(response));
    }

    @GetMapping("/")
    @PostMapping
    public ResponseEntity<ArtistsResponse> findById(@RequestParam Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ArtistsResponse>> listAll(){
        return ResponseEntity.ok(service.listAll());
    }

    @DeleteMapping
    public ResponseEntity<String> remove(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/spotify/{id}")
    public ResponseEntity<ArtistsSpotifyResponse> getBySpotify(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getBySpotify(id));
    }
}
