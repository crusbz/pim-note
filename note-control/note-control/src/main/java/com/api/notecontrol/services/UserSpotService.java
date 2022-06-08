package com.api.notecontrol.services;

import com.api.notecontrol.models.TaskSpotModel;
import com.api.notecontrol.models.UserSpotModel;
import com.api.notecontrol.repositories.TaskSpotRepository;
import com.api.notecontrol.repositories.UserSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserSpotService {
    final UserSpotRepository userSpotRepository;

    public UserSpotService(UserSpotRepository userSpotRepository) {
        this.userSpotRepository = userSpotRepository;
    }

    @Transactional
    public UserSpotModel save(UserSpotModel userSpotModel) {
        return userSpotRepository.save(userSpotModel);
    }

    public List<UserSpotModel> findAll(){
        return userSpotRepository.findAll();
    }

    public Optional<UserSpotModel> findById(UUID id) {
        return userSpotRepository.findById(id);
    }

    @Transactional
    public void delete(UserSpotModel userSpotModel) {
        userSpotRepository.delete(userSpotModel);
    }
}
