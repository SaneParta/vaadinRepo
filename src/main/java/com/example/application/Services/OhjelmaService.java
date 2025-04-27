package com.example.application.Services;

import com.example.application.Data.Treeniohjelma;
import com.example.application.Data.TreeniohjelmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OhjelmaService {
    private final TreeniohjelmaRepository treeniohjelmaRepository;

    OhjelmaService(TreeniohjelmaRepository treeniohjelmaRepository) {
        this.treeniohjelmaRepository = treeniohjelmaRepository;
    }
    public List<Treeniohjelma> getAll() {
        return treeniohjelmaRepository.findAll();
    }
    public Treeniohjelma editById(long id, Treeniohjelma treeniohjelma) {
        return treeniohjelmaRepository.save(treeniohjelma);
    }
    public void deleteById(long id) {
        treeniohjelmaRepository.deleteById(id);
    }
    public Treeniohjelma create(Treeniohjelma treeniohjelma) {
        treeniohjelmaRepository.save(treeniohjelma);
        return treeniohjelma;
    }

}
