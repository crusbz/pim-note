package com.api.notecontrol.controllers;

import com.api.notecontrol.dtos.NoteSpotDto;
import com.api.notecontrol.models.NoteSpotModel;
import com.api.notecontrol.services.NoteSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/note-spot")
public class NoteSpotController {

    final NoteSpotService noteSpotService;

    public NoteSpotController(NoteSpotService noteSpotService){
        this.noteSpotService = noteSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveNoteSpot(@RequestBody @Valid NoteSpotDto noteSpotDto){
        var noteSpotModel = new NoteSpotModel();
        BeanUtils.copyProperties(noteSpotDto, noteSpotModel);
        noteSpotModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(noteSpotService.save(noteSpotModel));
    }

    @GetMapping
    public ResponseEntity<Page<NoteSpotModel>> getAllNoteSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(noteSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<NoteSpotModel> noteSpotModel = noteSpotService.findById(id);
        if (!noteSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(noteSpotModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<NoteSpotModel> noteSpotModelOptional = noteSpotService.findById(id);
        if (!noteSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        noteSpotService.delete(noteSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Note Spot deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid NoteSpotDto noteSpotDto){
        Optional<NoteSpotModel> noteSpotModelOptional = noteSpotService.findById(id);
        if (!noteSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note Spot not found.");
        }
        var noteSpotModel = new NoteSpotModel();
        BeanUtils.copyProperties(noteSpotDto, noteSpotModel);
        noteSpotModel.setId(noteSpotModelOptional.get().getId());
        noteSpotModel.setCreated_at(noteSpotModelOptional.get().getCreated_at());
        return ResponseEntity.status(HttpStatus.OK).body(noteSpotService.save(noteSpotModel));
    }
}
