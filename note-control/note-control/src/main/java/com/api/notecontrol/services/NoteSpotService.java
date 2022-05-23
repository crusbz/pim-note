package com.api.notecontrol.services;

import com.api.notecontrol.models.NoteSpotModel;
import com.api.notecontrol.repositories.NoteSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Page<NoteSpotModel> findAll(Pageable pageable){
        return noteSpotRepository.findAll(pageable);
    }

    public Optional<NoteSpotModel> findById(UUID id) {
        return noteSpotRepository.findById(id);
    }

    @Transactional
    public void delete(NoteSpotModel noteSpotModel) {
        noteSpotRepository.delete(noteSpotModel);
    }

}
