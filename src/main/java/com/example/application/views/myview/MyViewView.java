package com.example.application.views.myview;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("My View")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class MyViewView extends Composite<VerticalLayout> {

    public MyViewView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        H1 h1 = new H1();
        Avatar avatar = new Avatar();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Anchor link = new Anchor();
        Anchor link2 = new Anchor();
        Anchor link3 = new Anchor();
        Anchor link4 = new Anchor();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H3 h3 = new H3();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        Paragraph textSmall = new Paragraph();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        h1.setText("Kuntovalmennus");
        h1.setWidth("max-content");
        avatar.setName("Firstname Lastname");
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.START);
        link.setText("Asiakkaat");
        link.setHref("#");
        link.setWidth("min-content");
        link2.setText("Treeniohjelmat");
        link2.setHref("#");
        link2.setWidth("min-content");
        link3.setText("Treenikerrat");
        link3.setHref("#");
        link3.setWidth("min-content");
        link4.setText("Liikkeet");
        link4.setHref("#");
        link4.setWidth("min-content");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutColumn3.getStyle().set("flex-grow", "1");
        h3.setText("Tervetuloa");
        h3.setWidth("max-content");
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.setHeight("min-content");
        textSmall.setText("Kuntovalmennus sovellus by Santeri Partanen");
        textSmall.setWidth("100%");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        getContent().add(layoutRow);
        layoutRow.add(layoutRow2);
        layoutRow2.add(h1);
        layoutRow2.add(avatar);
        getContent().add(layoutRow3);
        layoutRow3.add(layoutColumn2);
        layoutColumn2.add(link);
        layoutColumn2.add(link2);
        layoutColumn2.add(link3);
        layoutColumn2.add(link4);
        layoutRow3.add(layoutColumn3);
        layoutColumn3.add(h3);
        getContent().add(layoutRow4);
        layoutRow4.add(textSmall);
    }
}
