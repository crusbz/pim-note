package com.api.notecontrol.repositories;

import com.api.notecontrol.models.TaskSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface TaskSpotRepository extends JpaRepository<TaskSpotModel, UUID> {
}
