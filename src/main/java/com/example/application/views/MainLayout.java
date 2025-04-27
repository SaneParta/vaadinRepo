package com.example.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.util.List;

@Layout
@AnonymousAllowed
@CssImport("./themes/global-styles.css")
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void logOut() {
        VaadinSession.getCurrent().getSession().invalidate();
        UI.getCurrent().navigate("login");
    }

    private void addDrawerContent() {
        Button kirjauduUlos = new Button("Kirjaudu Ulos");

        kirjauduUlos.addClickListener(e -> {
            logOut();
        });

        RouterLink etusivuLink = new RouterLink("Etusivu", EtusivuView.class);
        RouterLink asiakasLink = new RouterLink("Asiakkaat", AsiakasView.class);
        RouterLink ohjelmaLink = new RouterLink("Treenit", OhjelmaView.class);
        RouterLink adminLink = new RouterLink("Admin", AdminView.class);


        addToDrawer(new VerticalLayout(
                etusivuLink,
                asiakasLink,
                ohjelmaLink,
                adminLink,
                kirjauduUlos
                ));
    }


    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        return MenuConfiguration.getPageHeader(getContent()).orElse("");
    }
}
