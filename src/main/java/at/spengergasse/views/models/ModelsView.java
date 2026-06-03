package at.spengergasse.views.models;

import at.spengergasse.domain.Bike;
import at.spengergasse.service.BikeService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Models")
@Route("models")
@Menu(order = 2, icon = LineAwesomeIconUrl.WRENCH_SOLID)
public class ModelsView extends VerticalLayout {

    private final Grid<Bike> grid = new Grid<>(Bike.class,true);
    private final BikeService bikeService;

    public ModelsView(@Autowired BikeService bikeService) {
        this.bikeService = bikeService;
        setSpacing(true);

        setSizeFull();
        grid.setSizeFull();
        add(grid);
        reload();
    }
    private void reload(){
        grid.setItems(bikeService.findAll());
    }
}
