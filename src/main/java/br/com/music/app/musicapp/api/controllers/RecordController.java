package br.com.music.app.musicapp.api.controllers;

import br.com.music.app.musicapp.domain.dto.requests.RecordRequest;
import br.com.music.app.musicapp.domain.dto.responses.RecordResponse;
import br.com.music.app.musicapp.business.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("records")
public class RecordController {

    @Autowired
    private RecordService service;

    @PostMapping("/add")
    public ResponseEntity<RecordResponse> create(@RequestBody RecordRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/update")
    public ResponseEntity<RecordResponse> update(@RequestBody RecordResponse response){
        return ResponseEntity.ok(service.update(response));
    }

    @GetMapping("/find")
    public ResponseEntity<RecordResponse> findById(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecordResponse>> listAll(){
        return ResponseEntity.ok(service.listAll());
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
