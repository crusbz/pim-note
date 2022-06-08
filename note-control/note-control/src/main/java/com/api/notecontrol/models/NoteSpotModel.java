package com.api.notecontrol.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_NOTE_SPOT")
public class NoteSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 20)
    private String title_note;
    @Column(length = 200)
    private String description_note;
    @Column(nullable = false, length = 20)
    private String category_note;
    @Column(nullable = false, length = 70)
    private LocalDateTime created_at;
    @ManyToOne()
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="userId", referencedColumnName = "id")
    private UserSpotModel userId;

    public UUID getUserId() {
        return userId.getId();
    }

    public void setUserId(UserSpotModel userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getDescription_note() {
        return description_note;
    }

    public void setDescription_note(String description_note) {
        this.description_note = description_note;
    }
}
