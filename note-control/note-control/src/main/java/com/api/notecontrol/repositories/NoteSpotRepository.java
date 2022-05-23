package com.api.notecontrol.repositories;

import com.api.notecontrol.models.NoteSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteSpotRepository extends JpaRepository<NoteSpotModel, UUID> {
}
