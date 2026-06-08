package at.spengergasse.service;

import at.spengergasse.domain.Bike;
import at.spengergasse.domain.BikeException;
import at.spengergasse.repository.BikeRepsitory;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Service
public class BikeService {
    private final BikeRepsitory repository;

    public BikeService(BikeRepsitory repository){
        this.repository = repository;
    }
    public void init() {
        if (repository.count() == 0) {
            testDaten(100);
        }
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
            repository.save(bike);
            }
    }
    public void addBike(Bike b){
        repository.save(b);
    }

    public ArrayList<Bike> findAll() {
        ArrayList<Bike> copy = (ArrayList<Bike>) repository.findAll();
        return copy;
    }

    public void removeAll(){
        repository.deleteAll();
    }
    public String toString(){
        return repository.findAll().stream()
            .map(bike -> bike.toString())
            .collect(Collectors.joining("\n"));
    }

    public void removeById(Long id){
        if(id== null){
            throw new BikeException("Uebergebene Id ist null");
        }
        if(!repository.existsById(id)){
            throw new BikeException("Fahrrad wurde bereits gelöscht");
        }
        repository.deleteById(id);
    }

    public void plusHundertEur(Long id){
        repository.findAll().stream()
                .filter(b -> b.getBikeId().equals(id))
                .forEach(b -> b.setPreis(b.getPreis()+100.0));
    }
}
