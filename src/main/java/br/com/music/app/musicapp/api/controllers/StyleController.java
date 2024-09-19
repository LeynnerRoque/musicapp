package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.business.dto.requests.StyleRequest;
import br.com.music.app.musicapp.business.dto.responses.StyleResponse;
import br.com.music.app.musicapp.business.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styles")
public class StyleController {
    @Autowired
    private StyleService service;

    @PostMapping
    public ResponseEntity<StyleResponse> create(@RequestBody StyleRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping
    public ResponseEntity<StyleResponse> update(@RequestBody StyleResponse request){
        return ResponseEntity.ok(service.update(request));
    }

    @GetMapping("/")
    public ResponseEntity<StyleResponse> create(@RequestParam Long id){
        return ResponseEntity.ok(service.findBy(id));
    }

    @GetMapping
    public ResponseEntity<List<StyleResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

}
