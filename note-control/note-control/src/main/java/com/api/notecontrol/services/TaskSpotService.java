package com.api.notecontrol.services;

import com.api.notecontrol.models.NoteSpotModel;
import com.api.notecontrol.models.TaskSpotModel;
import com.api.notecontrol.repositories.TaskSpotRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    public Optional<TaskSpotModel> findById(NoteSpotModel noteId, UUID id) {
        Optional<TaskSpotModel> obj = taskSpotRepository.findByNoteIdAndId(noteId, id);
        return obj;
    }

    public List<TaskSpotModel> findNoteAllTasks(NoteSpotModel noteId) {
        List<TaskSpotModel> obj = taskSpotRepository.findByNoteId(noteId);
        return obj;
    }

    @Transactional
    public void delete(TaskSpotModel taskSpotModel) {
        taskSpotRepository.delete(taskSpotModel);
    }
}
