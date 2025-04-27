package com.example.application.Data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsiakasRepository extends JpaRepository<Asiakas, Long> {
    Asiakas findByNimiLike(String asiakkaannimi);

    List<Asiakas> findBySahkopostiContaining(String sahkoposti);

    List<Asiakas> findByNimiContaining(String nimi);

    List<Asiakas> findByNimiContainingAndSahkopostiContaining(String nimi, String sahkoposti);
}
