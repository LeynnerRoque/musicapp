package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.domain.dto.requests.StyleRequest;
import br.com.music.app.musicapp.domain.dto.responses.StyleResponse;
import br.com.music.app.musicapp.business.services.StyleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/styles")
public class StyleController {
    @Autowired
    private StyleService service;

    @PostMapping("/add")
    public ResponseEntity<StyleResponse> create(@Valid @RequestBody StyleRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/update")
    public ResponseEntity<StyleResponse> update(@Valid @RequestBody StyleResponse request){
        return ResponseEntity.ok(service.update(request));
    }

    @GetMapping("/find")
    public ResponseEntity<StyleResponse> create(@RequestParam Long id){
        return ResponseEntity.ok(service.findBy(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<StyleResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }

}
