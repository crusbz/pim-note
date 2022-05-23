package com.api.notecontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class NoteSpotDto {

    @NotBlank
    @Size(max = 20)
    private String title_note;
    @NotBlank
    @Size(max = 20)
    private String category_note;
    @NotBlank
    @Size(max = 200)
    private String description_note;
    @NotBlank
    private String user_note;

    public String getUser_note() {
        return user_note;
    }

    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }

    public String getTitle_note() {
        return title_note;
    }

    public void setTitle_note(String title_note) {
        this.title_note = title_note;
    }

    public String getCategory_note() {
        return category_note;
    }

    public void setCategory_note(String category_note) {
        this.category_note = category_note;
    }

    public String getDescription_note() {
        return description_note;
    }

    public void setDescription_note(String description_note) {
        this.description_note = description_note;
    }
}
