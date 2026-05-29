
package at.spengergasse.domain;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode (of = "bikeid", callSuper = false)
public class bike {
    @Id
    private Long bikeId;
    private String bezeichnung;
    private String farbe;
    private Boolean verfuegbar;
    private Double preis;
    private LocalDate vdatum;
    private static final AtomicLong sequence = new AtomicLong(1000);

    public bike(Long bikeId, String bezeichnung, String farbe,
                Boolean verfuegbar, Double preis, LocalDate vdatum) {
        setBikeId(bikeId);
        setBezeichnung(bezeichnung);
        setFarbe(farbe);
        setVerfuegbar(verfuegbar);
        setPreis(preis);
        setVdatum(vdatum);
    }

    public bike(String bezeichnung, String farbe,
                Boolean verfuegbar, Double preis, LocalDate vdatum) {
        setBikeId();
        setBezeichnung(bezeichnung);
        setFarbe(farbe);
        setVerfuegbar(verfuegbar);
        setPreis(preis);
        setVdatum(vdatum);
    }

    public bike() {
        setBikeId();
        setBezeichnung("UNKN");
        setFarbe("schwarz");
        setVerfuegbar(false);
        setPreis(0.0);
        setVdatum(LocalDate.now());
    }

    public void setBikeId() {
        this.bikeId = sequence.getAndIncrement();
    }

    private static final String[] colors = {"Rot", "Blau", "Grün", "Gelb", "Schwarz", "Weiß", "Orange", "Lila", "Pink", "Braun", "Grau", "Türkis", "Silber", "Gold", "Dunkelblau"};

    public void setFarbe(String farbe) {
        if (!Arrays.asList(farbe).contains(farbe)) {
            throw new BikeException("Farbe gibt es nicht");
        }
        this.farbe = farbe;
    }
    public void setPreis(Double preis){
        if(Double.valueOf(preis)< 0 || Double.valueOf(preis)>10000){
            throw new BikeException("Preis darf nicht negativ sein und darf höchstens 10000 sein.");
        }
    }

    @Override
    public bike clone(){
        return new bike(bikeId,bezeichnung,farbe,verfuegbar,preis,vdatum);
    }
}
