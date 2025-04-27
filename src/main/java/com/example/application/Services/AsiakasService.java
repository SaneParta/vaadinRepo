package com.example.application.Services;

import com.example.application.Data.Asiakas;
import com.example.application.Data.AsiakasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsiakasService {
    private final AsiakasRepository asiakasRepository;

    AsiakasService(AsiakasRepository asiakasRepository) {
        this.asiakasRepository = asiakasRepository;
    }

    public List<Asiakas> getAll() {
        return asiakasRepository.findAll();
    }

    public Optional<Asiakas> getById(long id) {
        return asiakasRepository.findById(id);
    }

    public Asiakas create(Asiakas asiakas) {
        return asiakasRepository.save(asiakas);
    }

    public Asiakas editById(long id, Asiakas asiakas) {
        return asiakasRepository.save(asiakas);
    }
    public void deleteById(long id) {
        asiakasRepository.deleteById(id);
    }

    public List<Asiakas> findByNimiAndSahkoposti(String nimi, String sahkoposti) {
        if (nimi == null && sahkoposti == null) {
            return asiakasRepository.findAll();
        } else if (nimi == null) {
            return asiakasRepository.findBySahkopostiContaining(sahkoposti);
        } else if (sahkoposti == null) {
            return asiakasRepository.findByNimiContaining(nimi);
        } else {
            return asiakasRepository.findByNimiContainingAndSahkopostiContaining(nimi, sahkoposti);
        }
    }
}
