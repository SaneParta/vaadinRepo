package com.example.application.Services;

import com.example.application.Data.Liike;
import com.example.application.Data.LiikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiikeService {

    private final LiikeRepository liikeRepository;


    public LiikeService(LiikeRepository liikeRepository) {
        this.liikeRepository = liikeRepository;
    }

    public List<Liike> getAll() {
        return liikeRepository.findAll();
    }
    public void create(Liike liike) {
        liikeRepository.save(liike);
    }
    public Optional<Liike> getById(long id) {
        return liikeRepository.findById(id);
    }
}
