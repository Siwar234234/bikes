package at.spengergasse.service;

import at.spengergasse.domain.Bike;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BikeService {
    private final ArrayList<Bike> bikes= new ArrayList<>(1000);

    public void testDaten(int anzahl){
        String[] colors = {"Rot", "Blau", "Grün", "Gelb", "Schwarz", "Weiß", "Orange", "Lila", "Pink", "Braun", "Grau", "Türkis", "Silber", "Gold", "Dunkelblau"};
        Bike bike;
        Faker faker = new Faker();
        String bezeichnung;
        String farbe;
        Boolean verfuegbar;
        Double preis;
        LocalDate vdatum;

        for(int i = 0; i< anzahl;i++){

            bezeichnung = faker.animal().name();
            farbe = colors[faker.random().nextInt(0,colors.length-1)];
            vdatum = LocalDate.now().minusDays(faker.random().nextInt(0, 3650));
            preis = faker.number().randomDouble(2,175,4000);
            verfuegbar = faker.bool().bool();

            bike = new Bike(bezeichnung,farbe,verfuegbar, preis,vdatum);
            bikes.add(bike);

            }
    }


    public String toString(){
        return bikes.stream()
            .map(bike -> bike.toString())
            .collect(Collectors.joining("\n"));
    }
}
