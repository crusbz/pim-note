package com.api.notecontrol.controllers;

import com.api.notecontrol.models.NoteSpotModel;
import com.api.notecontrol.models.UserSpotModel;
import com.api.notecontrol.services.NoteSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
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

    @PostMapping()
    public ResponseEntity<Object> saveNoteSpot(@RequestBody NoteSpotModel noteSpotModel){
        noteSpotModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(noteSpotService.save(noteSpotModel));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getOneUserAllNotesSpot(@RequestHeader(value = "userId") UserSpotModel userId){
        List<NoteSpotModel> noteSpotModel = noteSpotService.findUserAllNotes(userId);
        return ResponseEntity.status(HttpStatus.OK).body(noteSpotModel);
    }
    @GetMapping("/{id_note}")
    public ResponseEntity<Object> getOneNoteSpot(@PathVariable(value = "id_note") UUID id, @RequestHeader(value = "userId") UserSpotModel userId){
        Optional<NoteSpotModel> noteSpotModel = noteSpotService.findById(userId, id);
        if (!noteSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(noteSpotModel.get());
    }

    @DeleteMapping("/{id_note}")
    public ResponseEntity<Object> deleteNoteSpot(@PathVariable(value = "id_note") UUID id, @RequestHeader(value = "userId") UserSpotModel userId){
        Optional<NoteSpotModel> noteSpotModelOptional = noteSpotService.findById(userId, id);
        if (!noteSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note Spot not found.");
        }
        noteSpotService.delete(noteSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Note Spot deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNoteSpot(@PathVariable(value = "id") UUID id, @RequestHeader(value = "userId") UserSpotModel userId, @RequestBody NoteSpotModel noteSpotModel){
        Optional<NoteSpotModel> noteSpotModelOptional = noteSpotService.findById(userId, id);
        if (!noteSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note Spot not found.");
        }
        noteSpotModel.setUserId(userId);
        noteSpotModel.setId(id);
        noteSpotModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(noteSpotService.save(noteSpotModel));
    }
}
