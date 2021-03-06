package com.api.notecontrol.repositories;

import com.api.notecontrol.models.UserSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserSpotRepository extends JpaRepository<UserSpotModel, UUID> {
}
