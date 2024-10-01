package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.business.dto.requests.RecordRequest;
import br.com.music.app.musicapp.business.dto.responses.RecordResponse;
import br.com.music.app.musicapp.business.services.RecordService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("records")
public class RecordController {

    @Autowired
    private RecordService service;

    @PostMapping
    public ResponseEntity<RecordResponse> create(@RequestBody RecordRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping
    public ResponseEntity<RecordResponse> update(@RequestBody RecordResponse response){
        return ResponseEntity.ok(service.update(response));
    }

    @GetMapping("/")
    public ResponseEntity<RecordResponse> findById(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecordResponse>> listAll(){
        return ResponseEntity.ok(service.listAll());
    }

    @DeleteMapping
    public ResponseEntity<String> remove(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
