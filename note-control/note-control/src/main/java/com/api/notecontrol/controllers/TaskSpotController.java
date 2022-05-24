package com.api.notecontrol.controllers;

import com.api.notecontrol.dtos.TaskSpotDto;
import com.api.notecontrol.models.NoteSpotModel;
import com.api.notecontrol.models.TaskSpotModel;
import com.api.notecontrol.models.UserSpotModel;
import com.api.notecontrol.services.TaskSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/task-spot")
public class TaskSpotController {

    final TaskSpotService taskSpotService;

    public TaskSpotController(TaskSpotService taskSpotService){
        this.taskSpotService = taskSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTaskSpot(@RequestBody TaskSpotModel taskSpotModel){
        taskSpotModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskSpotService.save(taskSpotModel));
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Object> getOneNoteAllTasksSpot(@PathVariable(value = "noteId") NoteSpotModel noteId) {
        List<TaskSpotModel> taskSpotModel = taskSpotService.findNoteAllTasks(noteId);
        return ResponseEntity.status(HttpStatus.OK).body(taskSpotModel);
    }

    @GetMapping("/{noteId}/{taskId}")
    public ResponseEntity<Object> getOneTaskSpot(@PathVariable(value = "noteId") NoteSpotModel noteId, @PathVariable(value = "taskId") UUID taskId){
        Optional<TaskSpotModel> taskSpotModel = taskSpotService.findById(noteId, taskId);
        if (!taskSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskSpotModel.get());
    }

    @DeleteMapping("/{noteId}/{taskId}")
    public ResponseEntity<Object> deleteTaskSpot(@PathVariable(value = "taskId") UUID taskId, @PathVariable(value = "noteId") NoteSpotModel noteId){
        Optional<TaskSpotModel> taskSpotModelOptional = taskSpotService.findById(noteId, taskId);
        if (!taskSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Spot not found.");
        }
        taskSpotService.delete(taskSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task Spot deleted successfully.");
    }

    @PutMapping("/{noteId}/{taskId}")
    public ResponseEntity<Object> updateTaskSpot(@PathVariable(value = "taskId") UUID taskId, @PathVariable(value = "noteId") NoteSpotModel noteId, @RequestBody TaskSpotModel taskSpotModel){
        Optional<TaskSpotModel> taskSpotModelOptional = taskSpotService.findById(noteId, taskId);
        if (!taskSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Spot not found.");
        }
        taskSpotModel.setNoteId(noteId);
        taskSpotModel.setId(taskId);
        taskSpotModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(taskSpotService.save(taskSpotModel));
    }
}
