package com.api.notecontrol.repositories;

import com.api.notecontrol.models.NoteSpotModel;
import com.api.notecontrol.models.TaskSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskSpotRepository extends JpaRepository<TaskSpotModel, UUID> {

    List<TaskSpotModel> findByNoteId(NoteSpotModel noteId);

    Optional<TaskSpotModel> findByNoteIdAndId(NoteSpotModel noteId, UUID id);
}
