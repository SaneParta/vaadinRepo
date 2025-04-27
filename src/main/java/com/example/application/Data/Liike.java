package com.example.application.Data;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Liike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "treeniohjelma_id")
    private Treeniohjelma treeniohjelma;

    private String nimi;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Treeniohjelma getTreeniohjelma() {
        return treeniohjelma;
    }

    public void setTreeniohjelma(Treeniohjelma treeniohjelma) {
        this.treeniohjelma = treeniohjelma;
    }
    @Override
    public String toString() {
        return nimi;
    }
}
