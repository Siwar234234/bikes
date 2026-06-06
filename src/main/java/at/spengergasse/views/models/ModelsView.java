package at.spengergasse.views.models;

import at.spengergasse.domain.Bike;
import at.spengergasse.domain.BikeException;
import at.spengergasse.service.BikeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.*;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.notification.Notification;


import java.awt.*;
import java.time.LocalDate;

@PageTitle("Models")
@Route("models")
@Menu(order = 2, icon = LineAwesomeIconUrl.WRENCH_SOLID)
public class ModelsView extends VerticalLayout {

    private final Button buttonRemoveAll = new Button("Remove all bikes");
    private final Button buttonAdd10 = new Button("Add 10 bikes");
    private final Button addWrongModell = new Button("Add wrong button");
    private final Grid<Bike> grid = new Grid<>(Bike.class,false);
    private final BikeService bikeService;

    public ModelsView(@Autowired BikeService bikeService) {
        this.bikeService = bikeService;
        setSpacing(true);
        setSizeFull();
        Image img = new Image("images/fahrrad.webp","Bild");
        img.setWidth("32px");
        Span span = new Span("Modelle");

        HorizontalLayout spalteheader = new HorizontalLayout(img,span);

        grid.setSizeFull();
        grid.addColumn(Bike::getBikeId).setHeader("ID").setSortable(true);
        grid.addColumn(Bike::getBezeichnung).setHeader(spalteheader).setSortable(true);
        grid.addColumn(Bike::getPreis).setHeader("Preis").setSortable(true);
        grid.addColumn(Bike::getFarbe).setHeader("Farbe").setSortable(true);
        grid.addColumn(Bike::getVdatum).setHeader("Erscheinungsdatum").setSortable(true);
        grid.addColumn(Bike::getVerfuegbar).setHeader("verfuegbar").setSortable(true);
        grid.addColumn(bike ->{
            Checkbox cb = new Checkbox();
            cb.setValue(Boolean.TRUE.equals(bike.getVerfuegbar()));
            cb.setReadOnly(true);
            return cb;})
                .setHeader("verfuegbar")
                .setSortable(true);
        grid.addComponentColumn(bike -> new Button("Delete", b -> deleteById(bike.getBikeId())))
                .setHeader("Action")
                .setSortable(false);
        grid.addComponentColumn(bike -> new Button("Preis+100",b -> plusHundert(bike.getBikeId())))
                .setHeader("Action2")
                .setSortable(false);

        buttonRemoveAll.addClickListener(b -> setButtonRemoveAll());
        buttonAdd10.addClickListener(b -> setTestDaten());
        addWrongModell.addClickListener(b -> addWrongModell());
        HorizontalLayout buttons = new HorizontalLayout(buttonRemoveAll,buttonAdd10,addWrongModell);
        buttons.setSpacing(true);
        add(buttons,grid);
        reload();
    }
    public void addWrongModell(){
        try{
            Bike bike = new Bike("Modell a","test",true,-5.0, LocalDate.now());
            bikeService.addBike(bike);
            reload();
        }catch (BikeException e){
            Notification.show(e.getMessage());
        }
    }
    public void setTestDaten(){
        try{
            bikeService.testDaten(10);
            buttonRemoveAll.setEnabled(true);
            reload();
        }catch (BikeException e){
            Notification.show(e.getMessage());
        }
    }
    public void setButtonRemoveAll(){
        bikeService.removeAll();
        buttonRemoveAll.setEnabled(false);
        reload();
    }

    public void deleteById(Long id){
        try{
            bikeService.removeById(id);
            reload();
        }catch (BikeException e){
            Notification.show(e.getMessage());
        }
    }
    public void plusHundert(Long id){
        bikeService.plusHundertEur(id);
        reload();
    }
    private void reload(){
        grid.setItems(bikeService.findAll());
    }
}
