package at.spengergasse.views.shipping;

import at.spengergasse.views.bikes.BikeView;
import com.vaadin.copilot.shaded.reactor.netty.http.internal.Http3;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.http.codec.KotlinSerializationStringEncoder;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.Optional;
import java.util.OptionalDouble;

@PageTitle("Shipping")
@Route("shipping")
@Menu(order = 1, icon = LineAwesomeIconUrl.AMAZON)
public class ShippingView extends VerticalLayout {

    public ShippingView() {
        setSpacing(false);
        add(BikeView.getHeader());

        Component card1 = getCard("Deutschland,Österreich,Schweiz",0,OptionalDouble.empty());
        Component card2 = getCard("Frankreich",700,OptionalDouble.empty());
        Component card3 = getCard("USA",35,OptionalDouble.of(17));
        Component card4 = getCard("Frankreich",700,OptionalDouble.empty());
        Component card5 = getCard("Frankreich",700,OptionalDouble.empty());

        FlexLayout cards = new FlexLayout(card1, card2, card3,card4,card5);
        cards.setWidthFull();
        cards.setJustifyContentMode(JustifyContentMode.CENTER);
        cards.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        add(cards);

        Paragraph info = new Paragraph("Versandt innerhalb von 2 Wochen garantiert!");
        info.setWidth("100%");
        info.getStyle().set("text-align", "center");
        add(info);
    }

    private static Component getCard(String land, double kosten, OptionalDouble zollgebuehren){
        H3 country = new H3(land);
        Paragraph cost;
        Paragraph zoll;

        if(kosten > 0){
            cost = new Paragraph("Versandtgebuehren: "+kosten+" Euro");
        }else{
            cost = new Paragraph("Der Vesandt ist kostenlos");
        }
        if(zollgebuehren.isPresent()){
            zoll = new Paragraph("Zusätzliche Zollgebuehr: " + zollgebuehren.getAsDouble()+ " %");

            VerticalLayout card = new VerticalLayout(country,cost,zoll);
            card.setWidth("350px");
            card.setPadding(true);
            card.setSpacing(false);
            card.getStyle()
                    .set("border", "1px solid lightgray")
                    .set("border-radius", "10px")
                    .set("margin", "10px");
            return card;
        }
        VerticalLayout card = new VerticalLayout(country,cost);
        card.setWidth("350px");
        card.setPadding(true);
        card.setSpacing(false);
        card.getStyle()
                .set("border", "1px solid lightgray")
                .set("border-radius", "10px")
                .set("margin", "10px");
        return card;
    }
}
