package com.example.application.Data;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Treeniohjelma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ohjelma;


    @OneToOne
    @JoinColumn(name = "asiakas_id")
    private Asiakas asiakas;

    @OneToMany(mappedBy = "treeniohjelma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Liike> liikkeet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOhjelma() {
        return ohjelma;
    }

    public void setOhjelma(String ohjelma) {
        this.ohjelma = ohjelma;
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public List<Liike> getLiikkeet() {
        return liikkeet;
    }

    public void setLiikkeet(List<Liike> liikkeet) {
        this.liikkeet = liikkeet;
    }
}
