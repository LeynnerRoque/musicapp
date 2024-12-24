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

    @PostMapping
    public ResponseEntity<AlbunsResponse> create(@RequestBody AlbunsRequest request){
        return  ResponseEntity.ok(service.create(request));
    }

    @PutMapping
    public ResponseEntity<AlbunsResponse> update(@RequestBody AlbunsResponse response){
        return  ResponseEntity.ok(service.update(response));
    }

    @GetMapping("/")
    public ResponseEntity<AlbunsResponse> findbyId(@RequestParam Long id){
        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AlbunsResponse>> listAll(){
        return ResponseEntity.ok(service.listAll());
    }

    @DeleteMapping
    public ResponseEntity<String> remove(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/spotify/{id}")
    public ResponseEntity<AlbumsSpotifyResponse> getBySpotifyName(@PathVariable("id") String id){
        return ResponseEntity.ok(service.getBySpotifyName(id));
    }

    @PostMapping("/spotify-create-by/")
    public ResponseEntity<AlbunsResponse> createBySpotify(@RequestBody SendToFind request){
        return ResponseEntity.ok(service.createBySpotify(request.codeSpotify()));
    }
}
