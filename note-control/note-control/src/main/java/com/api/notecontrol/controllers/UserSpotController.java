package com.api.notecontrol.controllers;

import com.api.notecontrol.dtos.UserSpotDto;
import com.api.notecontrol.models.UserSpotModel;
import com.api.notecontrol.services.UserSpotService;
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
@RequestMapping("/user-spot")
public class UserSpotController {

    final UserSpotService userSpotService;

    public UserSpotController(UserSpotService userSpotService){
        this.userSpotService = userSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUserSpot(@RequestBody @Valid UserSpotDto userSpotDto){
        var userSpotModel = new UserSpotModel();
        BeanUtils.copyProperties(userSpotDto, userSpotModel);
        userSpotModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userSpotService.save(userSpotModel));
    }

    @GetMapping
    public ResponseEntity<Page<UserSpotModel>> getAllUserSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUserSpot(@PathVariable(value = "id") UUID id){
        Optional<UserSpotModel> userSpotModel = userSpotService.findById(id);
        if (!userSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userSpotModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserSpot(@PathVariable(value = "id") UUID id){
        Optional<UserSpotModel> userSpotModelOptional = userSpotService.findById(id);
        if (!userSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        userSpotService.delete(userSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User Spot deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserSpotDto userSpotDto){
        Optional<UserSpotModel> userSpotModelOptional = userSpotService.findById(id);
        if (!userSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Spot not found.");
        }
        var userSpotModel = new UserSpotModel();
        BeanUtils.copyProperties(userSpotDto, userSpotModel);
        userSpotModel.setId(userSpotModelOptional.get().getId());
        userSpotModel.setCreated_at(userSpotModelOptional.get().getCreated_at());
        return ResponseEntity.status(HttpStatus.OK).body(userSpotService.save(userSpotModel));
    }
}
