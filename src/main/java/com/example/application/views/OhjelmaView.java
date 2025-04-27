package com.example.application.views;

import com.example.application.Data.Asiakas;
import com.example.application.Data.AsiakasRepository;
import com.example.application.Data.Liike;
import com.example.application.Data.Treeniohjelma;
import com.example.application.Services.AsiakasService;
import com.example.application.Services.LiikeService;
import com.example.application.Services.OhjelmaService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.dialog.Dialog;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Route(value = "Ohjelmat", layout = MainLayout.class)
@PermitAll
@CssImport("./themes/treeni.css")
public class OhjelmaView extends VerticalLayout {

    private final OhjelmaService ohjelmaService;
    private final AsiakasService asiakasService;
    private final LiikeService liikeService;
    private final AsiakasRepository asiakasRepository;
    Grid<Treeniohjelma> treeniohjelmat = new Grid<>(Treeniohjelma.class);
    Button lisaaOhjelma = new Button("Lisää ohjelma");
    Button lisaaLiikkeita = new Button("Lisää liikkeitä");





    public OhjelmaView(OhjelmaService ohjelmaService, AsiakasService asiakasService,
                       LiikeService liikeService, AsiakasRepository asiakasRepository) {
        this.ohjelmaService = ohjelmaService;
        this.asiakasService = asiakasService;
        this.liikeService = liikeService;
        this.asiakasRepository = asiakasRepository;

        lisaaOhjelma.addClassName("ohjelma-button1");
        lisaaLiikkeita.addClassName("ohjelma-button2");

        treeniohjelmat.removeAllColumns();
        treeniohjelmat.addColumn(t -> t.getAsiakas() != null ? t.getAsiakas().getNimi() : "").setHeader("Asiakas");
        treeniohjelmat.addColumn(Treeniohjelma::getOhjelma).setHeader("Ohjelma");
        treeniohjelmat.addColumn(t ->
                t.getLiikkeet() != null ?
                        t.getLiikkeet().stream().map(Liike::getNimi).collect(Collectors.joining(", ")) :
                        ""
        ).setHeader("Liikkeet");
        treeniohjelmat.setItems(ohjelmaService.getAll());

        lisaaOhjelma.addClickListener(e->avaaOhjelmaformi(new Treeniohjelma()));
        lisaaLiikkeita.addClickListener(e -> {
            List<Liike> liikeLista = new ArrayList<>();
            avaaLiikeFormi(liikeLista);
        });
        treeniohjelmat.addItemClickListener(e->{
            Treeniohjelma valittuTreeni = e.getItem();
            avaaOhjelmaMuokkausFormi(valittuTreeni);
        });

        add (new H2("Treeniohjelmat"),treeniohjelmat, lisaaOhjelma, lisaaLiikkeita);
    }

    public void avaaLiikeFormi(List<Liike> liikeLista) {
        Dialog liikeDialog = new Dialog();
        liikeDialog.add(new H2("Lisää mahdollisia treenejä"));
        TextField treeni = new TextField("Anna lisättävä treeni");
        Button lisaa = new Button("Lisää");
        Button lopeta = new Button("Sulje ikkuna");

        lisaa.addClickListener(e -> {
            Liike liike = new Liike();
            liike.setNimi(treeni.getValue());
            liikeLista.add(liike);
            liikeService.create(liike);
            Notification.show("Liike lisätty", 3000, Notification.Position.TOP_CENTER);
        });
        lopeta.addClickListener(e -> {
            liikeDialog.close();
        });

        liikeDialog.add(treeni,lisaa,lopeta);
        liikeDialog.open();

    }

    public void avaaOhjelmaformi(Treeniohjelma treeniohjelma) {
        Dialog dialog = new Dialog();
        TextField treeniteksti = new TextField("Anna treeniohjelman nimi");
        Button tallenna = new Button("Tallenna muutokset");
        MultiSelectComboBox<Liike> valitseLiikkeet = new MultiSelectComboBox<Liike>("Valitse liikkeet treeniin");
        valitseLiikkeet.setItemLabelGenerator(Liike::getNimi);

        ComboBox<Asiakas> asikasComboBox = new ComboBox<>("Valise asiakas");
        asikasComboBox.setItems(asiakasService.getAll());
        asikasComboBox.setItemLabelGenerator(Asiakas::getNimi);

        valitseLiikkeet.setItems(liikeService.getAll());


        tallenna.addClickListener(e-> {

            Asiakas asiakas = asikasComboBox.getValue();

            treeniohjelma.setOhjelma(treeniteksti.getValue());
            treeniohjelma.setAsiakas(asiakas);
            Treeniohjelma tallennettuOhjelma = ohjelmaService.create(treeniohjelma);

            List<Liike> valitutLiikkeet = new ArrayList<>();
            for (Liike liike : valitseLiikkeet.getSelectedItems()) {
                Optional<Liike> liikeKannasta = liikeService.getById(liike.getId());
                if (liikeKannasta.isPresent()) {
                    Liike liike1 = liikeKannasta.get();
                    liike1.setTreeniohjelma(tallennettuOhjelma);
                    liikeService.create(liike1);
                }
            }
            treeniohjelma.setLiikkeet(valitutLiikkeet);


            treeniohjelmat.setItems(ohjelmaService.getAll());

            dialog.close();

        });

        dialog.add(new FormLayout(asikasComboBox, treeniteksti, valitseLiikkeet, tallenna));
        dialog.open();

    }

    public void avaaOhjelmaMuokkausFormi(Treeniohjelma treeniohjelma){
        Dialog dialog = new Dialog();
        ComboBox<Asiakas> asiakasComboBox = new ComboBox<>("Valittu asiakas");
        TextField treeniteksti = new TextField("Valittu treeni");
        MultiSelectComboBox<Liike> valitutTreenit = new MultiSelectComboBox<>("Muuta valittuja treenejä");
        Button tallenna = new Button("Tallenna");
        Button poista = new Button("Poista treeni");

        valitutTreenit.setItems(liikeService.getAll());
        valitutTreenit.setItemLabelGenerator(Liike::getNimi);
        valitutTreenit.select(treeniohjelma.getLiikkeet());



        asiakasComboBox.setItems(asiakasService.getAll());
        asiakasComboBox.setItemLabelGenerator(Asiakas::getNimi);
        asiakasComboBox.setValue(treeniohjelma.getAsiakas());
        treeniteksti.setValue(treeniohjelma.getOhjelma());

        tallenna.addClickListener(e -> {
            treeniohjelma.setOhjelma(treeniteksti.getValue());
            treeniohjelma.setAsiakas(asiakasComboBox.getValue());

            List<Liike> valitutLiiketMuokkaus = new ArrayList<>(valitutTreenit.getSelectedItems());

            List<Liike> vanhatLiikkeet = treeniohjelma.getLiikkeet();


            for (Liike vanhaLiike : vanhatLiikkeet) {
                if (!valitutLiiketMuokkaus.contains(vanhaLiike)) {
                    vanhaLiike.setTreeniohjelma(null);
                    liikeService.create(vanhaLiike);
                }
            }


            for (Liike valittuLiike : valitutLiiketMuokkaus) {
                valittuLiike.setTreeniohjelma(treeniohjelma);
                liikeService.create(valittuLiike);
            }

            treeniohjelma.setLiikkeet(valitutLiiketMuokkaus);

            ohjelmaService.editById(treeniohjelma.getId(), treeniohjelma);

            treeniohjelmat.setItems(ohjelmaService.getAll());
            dialog.close();
        });

        poista.addClickListener(e-> {
            Dialog confirmDeleteDialog = new Dialog();
            confirmDeleteDialog.add("Haluatko varmasti poistaa reenin?");
            Button kylla = new Button("Kylla");
            Button ei = new Button("Ei");

            kylla.addClickListener(event -> {
                Asiakas asiakas = treeniohjelma.getAsiakas();
                if (asiakas != null) {
                    asiakas.setOhjelma(null);
                    asiakasService.create(asiakas);
                }
                treeniohjelma.setAsiakas(null);
                ohjelmaService.create(treeniohjelma);


                ohjelmaService.deleteById(treeniohjelma.getId());
                treeniohjelmat.setItems(ohjelmaService.getAll());
                confirmDeleteDialog.close();
                dialog.close();
            });
            ei.addClickListener(event -> {
                confirmDeleteDialog.close();
            });

            confirmDeleteDialog.add(new FormLayout(kylla, ei));
            confirmDeleteDialog.open();


        });

        dialog.add(asiakasComboBox,treeniteksti,valitutTreenit,tallenna,poista);
        dialog.open();
    }

}
