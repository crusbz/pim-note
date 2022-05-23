package com.api.notecontrol.controllers;

import com.api.notecontrol.dtos.TaskSpotDto;
import com.api.notecontrol.models.TaskSpotModel;
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
    public ResponseEntity<Object> saveTaskSpot(@RequestBody @Valid TaskSpotDto taskSpotDto){
        var taskSpotModel = new TaskSpotModel();
        BeanUtils.copyProperties(taskSpotDto, taskSpotModel);
        taskSpotModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskSpotService.save(taskSpotModel));
    }

    @GetMapping
    public ResponseEntity<Page<TaskSpotModel>> getAllTaskSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(taskSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTaskSpot(@PathVariable(value = "id") UUID id){
        Optional<TaskSpotModel> taskSpotModel = taskSpotService.findById(id);
        if (!taskSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskSpotModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTaskSpot(@PathVariable(value = "id") UUID id){
        Optional<TaskSpotModel> taskSpotModelOptional = taskSpotService.findById(id);
        if (!taskSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Spot not found.");
        }
        taskSpotService.delete(taskSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task Spot deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTaskSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid TaskSpotDto taskSpotDto){
        Optional<TaskSpotModel> taskSpotModelOptional = taskSpotService.findById(id);
        if (!taskSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Spot not found.");
        }
        var taskSpotModel = new TaskSpotModel();
        BeanUtils.copyProperties(taskSpotDto, taskSpotModel);
        taskSpotModel.setId(taskSpotModelOptional.get().getId());
        taskSpotModel.setCreated_at(taskSpotModelOptional.get().getCreated_at());
        return ResponseEntity.status(HttpStatus.OK).body(taskSpotService.save(taskSpotModel));
    }
}
