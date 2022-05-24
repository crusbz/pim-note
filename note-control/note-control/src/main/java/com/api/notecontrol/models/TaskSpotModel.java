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
    @Column(columnDefinition = "boolean default false")
    private boolean done_task;
    @ManyToOne
    @JoinColumn(name="note_id", referencedColumnName = "id", nullable = false)
    private NoteSpotModel noteId;

    public UUID getNoteId() {
        return noteId.getId();
    }
    public void setNoteId(NoteSpotModel noteId) {
        this.noteId = noteId;
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

    public boolean isDone_task() {
        return done_task;
    }

    public void setDone_task(boolean done_task) {
        this.done_task = done_task;
    }
}
