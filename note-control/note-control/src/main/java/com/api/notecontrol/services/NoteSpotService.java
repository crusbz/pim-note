package com.api.notecontrol.services;

import com.api.notecontrol.models.NoteSpotModel;
import com.api.notecontrol.models.UserSpotModel;
import com.api.notecontrol.repositories.NoteSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteSpotService {

    final NoteSpotRepository noteSpotRepository;

    public NoteSpotService(NoteSpotRepository noteSpotRepository) {
        this.noteSpotRepository = noteSpotRepository;
    }

    @Transactional
    public NoteSpotModel save(NoteSpotModel noteSpotModel) {
        return noteSpotRepository.save(noteSpotModel);
    }

    public Optional<NoteSpotModel> findById(UserSpotModel userId, UUID id) {
        Optional<NoteSpotModel> obj = noteSpotRepository.findByUserIdAndId(userId, id);
        return obj;
    }

    public List<NoteSpotModel> findUserAllNotes(UserSpotModel userId) {
       List<NoteSpotModel> obj = noteSpotRepository.findByUserId(userId);
        return obj;
    }

    @Transactional
    public void delete(NoteSpotModel noteSpotModel) {
        noteSpotRepository.delete(noteSpotModel);
    }

}
