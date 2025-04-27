package com.example.application.views;

import com.example.application.Data.Asiakas;
import com.example.application.Data.AsiakasRepository;
import com.example.application.Services.AsiakasService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.annotation.security.PermitAll;


import java.awt.*;

@Route(value = "Asiakkaat", layout = MainLayout.class)
@PermitAll
public class AsiakasView extends VerticalLayout {

    private final AsiakasService asiakasService;

    private Grid <Asiakas> asiakkaat = new Grid<>(Asiakas.class);
    private Button lisaaAsiakas = new Button("Lisää asiakas");
    private TextField filterNimi = new TextField("Suodata nimen perusteella");
    private TextField filterEmail = new TextField("Suodata sähköpostin perusteella");

    public AsiakasView(AsiakasService asiakasService) {
        this.asiakasService = asiakasService;
        lisaaAsiakas.getElement().getStyle().setBackgroundColor("red");

        filterNimi.addValueChangeListener(e -> updateFilter());
        filterEmail.addValueChangeListener(e -> updateFilter());

        asiakkaat.setColumns("id", "nimi", "sahkoposti");
        asiakkaat.setItems(asiakasService.getAll());

        lisaaAsiakas.addClickListener(e->avaaAsiakasformi(new Asiakas()));

        asiakkaat.setItems(asiakasService.getAll());

        // Klikkaus: muokkaa
        asiakkaat.addItemClickListener(e -> {
            Asiakas valittuAsiakas = e.getItem();
            avaaAsiakasmuokkausformi(valittuAsiakas);
        });

        add(new H2("AsiakasHallinta"), filterNimi, filterEmail, asiakkaat, lisaaAsiakas);

    }
    
    private void updateFilter() {
        asiakkaat.setItems(asiakasService.findByNimiAndSahkoposti(
                filterNimi.getValue(),
                filterEmail.getValue()
        ));
    }


    public void avaaAsiakasformi(Asiakas asiakas) {
        Dialog dialog = new Dialog();
        TextField nimi = new TextField("Nimi");
        TextField sahkoposti = new TextField("Sahkoposti");
        Button tallenna = new Button("Tallenna");

        nimi.setValue(asiakas.getNimi() != null ? asiakas.getNimi() : "");
        sahkoposti.setValue(asiakas.getSahkoposti() != null ? asiakas.getSahkoposti() : "");


        tallenna.addClickListener((e)-> {
           asiakas.setNimi(nimi.getValue());
           asiakas.setSahkoposti(sahkoposti.getValue());

           asiakasService.create(asiakas);

           asiakkaat.setItems(asiakasService.getAll());
           dialog.close();

        });

        dialog.add(new FormLayout(nimi, sahkoposti,tallenna));
        dialog.open();


    }

    public void avaaAsiakasmuokkausformi(Asiakas asiakas) {
        Dialog dialog = new Dialog();
        TextField nimi = new TextField("Nimi");
        TextField sahkoposti = new TextField("Sahkoposti");
        Button tallenna = new Button("Tallenna muutokset");
        Button poista = new Button("Poista asiakas");

        nimi.setValue(asiakas.getNimi() != null ? asiakas.getNimi() : "");
        sahkoposti.setValue(asiakas.getSahkoposti() != null ? asiakas.getSahkoposti() : "");
        tallenna.addClickListener((e)-> {
            asiakas.setNimi(nimi.getValue());
            asiakas.setSahkoposti(sahkoposti.getValue());
            asiakasService.editById(asiakas.getId(), asiakas);

            asiakkaat.setItems(asiakasService.getAll());

            dialog.close();
        });

        poista.addClickListener((e) -> {
            Dialog confirmDialog = new Dialog();
            confirmDialog.add("Haluatko varmasti poistaa asiakkaan: " + asiakas.getNimi() + "?");

            Button kylla = new Button("Kyllä", event -> {
                asiakasService.deleteById(asiakas.getId());
                asiakkaat.setItems(asiakasService.getAll());
                confirmDialog.close();
                dialog.close(); // ← suljetaan vasta tässä!
            });

            Button ei = new Button("Ei", clickEvent -> {
                confirmDialog.close();
            });

            confirmDialog.add(new FormLayout(kylla, ei));
            confirmDialog.open();
        });

        dialog.add(new FormLayout(nimi, sahkoposti,tallenna, poista));
        dialog.open();

    }

}
