package com.example.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

import java.awt.*;

@Route(value = "Etusivu", layout = MainLayout.class)
@PermitAll
public class EtusivuView extends VerticalLayout {
    public EtusivuView() {
        OpenFrontPage();
    }

    private void OpenFrontPage() {
        H1 title = new H1("Tervetuloa Treenisovellukseen");

        add(
                title,
                new Paragraph("Tämä sovellus on suunniteltu kuntovalmentajille helpottamaan asiakashallintaa ja treenien seuraamista."),
                new Paragraph("Voit hallita asiakkaita, rakentaa treeniohjelmia ja kirjata mittaustuloksia."),
                new Paragraph("Aloita navigoimalla vasemmalla olevasta valikosta tai valitsemalla haluamasi toiminto."),
                new Button("Siirry asiakashallintaan", e -> getUI().ifPresent(ui -> ui.navigate("Asiakkaat"))),
                new Button("Siirry Treeniohjelmiin", e -> getUI().ifPresent(ui -> ui.navigate("Ohjelmat")))

        );
    }
}
