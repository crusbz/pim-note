package com.api.notecontrol.services;

import com.api.notecontrol.models.TaskSpotModel;
import com.api.notecontrol.repositories.TaskSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskSpotService {

    final TaskSpotRepository taskSpotRepository;

    public TaskSpotService(TaskSpotRepository taskSpotRepository) {
        this.taskSpotRepository = taskSpotRepository;
    }

    @Transactional
    public TaskSpotModel save(TaskSpotModel taskSpotModel) {
        return taskSpotRepository.save(taskSpotModel);
    }

    public Page<TaskSpotModel> findAll(Pageable pageable){
        return taskSpotRepository.findAll(pageable);
    }

    public Optional<TaskSpotModel> findById(UUID id) {
        return taskSpotRepository.findById(id);
    }

    @Transactional
    public void delete(TaskSpotModel taskSpotModel) {
        taskSpotRepository.delete(taskSpotModel);
    }
}
