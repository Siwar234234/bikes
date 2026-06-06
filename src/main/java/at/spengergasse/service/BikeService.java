package at.spengergasse.service;

import at.spengergasse.domain.Bike;
import at.spengergasse.domain.BikeException;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
public class BikeService {
    private final ArrayList<Bike> bikes;
    public BikeService(){
        bikes= new ArrayList<>(1000);
        testDaten(100);
    }

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
    public boolean addBike(Bike b){
        return bikes.add(b);
    }

    public ArrayList<Bike> findAll() {
        ArrayList<Bike> copy = new ArrayList<>(bikes);
        return copy;
    }

    public void removeAll(){
        bikes.clear();
    }
    public String toString(){
        return bikes.stream()
            .map(bike -> bike.toString())
            .collect(Collectors.joining("\n"));
    }

    public void removeById(Long id){
        Iterator<Bike> iter = bikes.iterator();
        Bike b;
        boolean gefunden = false;
        while(iter.hasNext()){
            b = iter.next();
            if(b.getBikeId() == id){
                iter.remove();
                gefunden = true;
            }
        }
        if(!gefunden){
            throw new BikeException("Entfernen hat nicht funktioniert");
        }
    }

    public void plusHundertEur(Long id){
        for(Bike b : bikes){
            if(b.getBikeId() == id){
                b.setPreis(b.getPreis()+100.0);
            }
        }
    }
}
