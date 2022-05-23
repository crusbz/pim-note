package com.api.notecontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class TaskSpotDto {

    @NotBlank
    @Size(max = 200)
    private String description_task;
    @NotNull
    private UUID note_id;

    public String getDescription_task() {
        return description_task;
    }

    public void setDescription_task(String description_task) {
        this.description_task = description_task;
    }

    public UUID getNote_id() {
        return note_id;
    }

    public void setNote_id(UUID note_id) {
        this.note_id = note_id;
    }
}
