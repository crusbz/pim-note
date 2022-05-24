package com.api.notecontrol.repositories;

import com.api.notecontrol.models.NoteSpotModel;
import com.api.notecontrol.models.UserSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteSpotRepository extends JpaRepository<NoteSpotModel, UUID> {
    List<NoteSpotModel> findByUserId(UserSpotModel userId);

    Optional<NoteSpotModel> findByUserIdAndId(UserSpotModel userId, UUID id);
}
