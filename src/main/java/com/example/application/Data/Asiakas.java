package com.example.application.Data;

import jakarta.persistence.*;

@Entity
public class Asiakas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nimi;
    private String sahkoposti;


    @OneToOne (mappedBy = "asiakas", cascade = CascadeType.ALL)
    private Treeniohjelma ohjelma;

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

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public Treeniohjelma getOhjelma() {
        return ohjelma;
    }

    public void setOhjelma(Treeniohjelma ohjelma) {
        this.ohjelma = ohjelma;
    }

    @Override
    public String toString() {
        return nimi;
    }
}
