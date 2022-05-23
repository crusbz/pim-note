package com.api.notecontrol.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_TASK_SPOT")
public class TaskSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = false, length = 200)
    private String description_task;
    @Column(nullable = false, length = 70)
    private LocalDateTime created_at;
    @Column()
    private boolean status_task;
    @ManyToOne
    @JoinColumn(name="note_id", referencedColumnName = "id", nullable = false)
    private NoteSpotModel noteSpotModel;

    public boolean isStatus_task() {
        return status_task;
    }

    public void setStatus_task(boolean status_task) {
        this.status_task = status_task;
    }

    public NoteSpotModel getNoteSpotModel() {
        return noteSpotModel;
    }

    public void setNoteSpotModel(NoteSpotModel noteSpotModel) {
        this.noteSpotModel = noteSpotModel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getDescription_task() {
        return description_task;
    }

    public void setDescription_task(String description_task) {
        this.description_task = description_task;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
