package at.spengergasse.views.bikes;
import com.vaadin.copilot.shaded.reactor.netty.http.internal.Http3;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import org.vaadin.lineawesome.LineAwesomeIconUrl;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Bike")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.BIKING_SOLID)
public class BikeView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public BikeView() {

        H3 titelp1 = new H3("Willkommen in unserem Fahrradshop");
        Paragraph p1 = new Paragraph("Entdecke hochwertige Fahrräder für Alltag, Sport und Freizeit. Ob Citybike, Mountainbike oder E-Bike – bei uns findest du das passende Modell für deine Bedürfnisse.");
        p1.getStyle().set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");
        VerticalLayout lp1 = new VerticalLayout(titelp1,p1);

        H3 titelp2 = new H3("Qualität & Beratung");
        Paragraph p2 = new Paragraph("Wir bieten dir moderne Fahrräder von renommierten Marken sowie persönliche Beratung durch unser erfahrenes Team. Auch Zubehör und Ersatzteile bekommst du direkt bei uns.");
        p2.getStyle().set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");
        VerticalLayout lp2 = new VerticalLayout(titelp2,p2);

        H3 titelp3 = new H3("Fahrradservice & Werkstatt");
        Paragraph p3 = new Paragraph("Unsere Werkstatt sorgt dafür, dass dein Fahrrad jederzeit sicher und zuverlässig bleibt. Von Reparaturen bis zur Inspektion – wir kümmern uns professionell um dein Bike.");
        p3.getStyle().set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");
        VerticalLayout lp3 = new VerticalLayout(titelp3,p3);

        H3 titelp4 = new H3("Nachhaltig mobil");
        Paragraph p4 = new Paragraph("Mit dem Fahrrad bist du flexibel, gesund und umweltfreundlich unterwegs. Starte jetzt mit einem neuen Fahrrad in deine nächste Tour.");
        p4.getStyle().set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");
        VerticalLayout lp4 = new VerticalLayout(titelp4,p4);

        H3 name = new H3("Bikes GMBH");
        H3 street = new H3("Rennweg");
        H3 city = new H3("1030 Wien");
        HorizontalLayout footer = new HorizontalLayout(name,street,city);

        VerticalLayout ergebniss = new VerticalLayout(getHeader(),lp1,lp2,lp3,lp4,footer);
        add(ergebniss);
    }

    public static Component getHeader(){
        H1 ueberschrift = new H1("Bikes");
        H2 ueberschrift2 = new H2("Preisguenstige Premium-Fahrräder");
        Image img = new Image("images/fahrrad.png","logo");
        img.setWidth("330px");
        img.setHeight("330px");
        VerticalLayout l1 = new VerticalLayout(ueberschrift,ueberschrift2,img);
        return l1;
    }
}
