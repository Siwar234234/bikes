package at.spengergasse.views.models;

import at.spengergasse.domain.Bike;
import at.spengergasse.domain.BikeException;
import at.spengergasse.service.BikeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;


import java.awt.*;
import java.time.LocalDate;

@PageTitle("Models")
@Route("models")
@Menu(order = 2, icon = LineAwesomeIconUrl.WRENCH_SOLID)
public class ModelsView extends VerticalLayout {

    private final Button buttonRemoveAll = new Button("Remove all bikes");
    private final Button buttonAdd10 = new Button("Add 10 bikes");
    private final Button addWrongModell = new Button("Add wrong button");
    private final Button buttonAdd1= new Button("Add 1");
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
        buttonAdd1.addClickListener(b -> addOne());
        addWrongModell.addClickListener(b -> addWrongModell());
        HorizontalLayout buttons = new HorizontalLayout(buttonRemoveAll,buttonAdd10,addWrongModell,buttonAdd1);
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

    public void addOne(){
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Fahhrad hinzufuegen");

        TextField bikeId = new TextField("BikeId");
        bikeId.setReadOnly(true);
        TextField bezeichnung = new TextField("Modell-Bezeichnung");
        Checkbox verfuegbar = new Checkbox("Verfuegbar");
        NumberField preis = new NumberField("Preis");
        ComboBox<String> farbe = new ComboBox<>("Farbe");
        farbe.setItems("Rot", "Grün", "Gelb", "Schwarz", "Weiß", "Orange", "Lila", "Pink",
                "Braun", "Grau", "Türkis", "Silber", "Gold", "Dunkelblau");
        DatePicker vdatum = new DatePicker("Veroeffentlicht");

        BeanValidationBinder binder = new BeanValidationBinder(Bike.class);

        binder.forField(bezeichnung).bind("bezeichnung");
        binder.forField(verfuegbar).bind("verfuegbar");
        binder.forField(preis).bind("preis");
        binder.forField(vdatum).bind("vdatum");

        Bike bike = new Bike();
        binder.setBean(bike);
        bikeId.setValue(""+bike.getBikeId());
        VerticalLayout v = new VerticalLayout();
        v.add(bikeId, bezeichnung, verfuegbar, preis, farbe, vdatum);

        Button addButton = new Button("add");
        addButton.addClickListener(e ->{
            if(binder.validate().isOk()){
                bikeService.addBike(bike);
                reload();
                dialog.close();
            }else{
                Notification.show("Fehlber bei den Daten");
            }
        });
        Button cancelButton = new Button("cancel");
        cancelButton.addClickListener(e -> dialog.close());
        dialog.getFooter().add(addButton,cancelButton);
        dialog.add(v);
        dialog.open();


    }
    private void reload(){
        grid.setItems(bikeService.findAll());
    }
}
