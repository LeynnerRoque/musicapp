package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.business.dto.requests.AlbunsRequest;
import br.com.music.app.musicapp.business.dto.responses.AlbunsResponse;
import br.com.music.app.musicapp.business.services.AlbunsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albuns")
public class AlbunsController {

    @Autowired
    private AlbunsService service;

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
}
