
package at.spengergasse.domain;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Bike {
    @Id
    private Long bikeId;
    @NotNull(message = "Bezeichnung darf nicht null sein")
    @Size(min = 3, max = 50, message = "Bezeichnung muss zw 2 und 50 Zeichein groß sein")
    @NotBlank(message = "Bezeichnung darf nicht leer sein")
    private String bezeichnung;

    @Pattern(regexp = "Rot|Blau|Grün|Gelb|Schwarz|Weiß|Orange|Lila|Pink|Braun|Grau|Türkis|Silber|Gold|Dunkelblau", message = "Ungueltige Farbe")
    private String farbe;
    private Boolean verfuegbar;

    @DecimalMin(value = "100.0", message = "The min. price is 100.0 Eur")
    @DecimalMax(value = "10000.0", message = "The max. price is 1000.0 Eur")
    private Double preis;
    private LocalDate vdatum;
    private static final AtomicLong sequence = new AtomicLong(1000);

    public Bike(Long bikeId, String bezeichnung, String farbe,
                Boolean verfuegbar, Double preis, LocalDate vdatum) {
        setBikeId(bikeId);
        setBezeichnung(bezeichnung);
        setFarbe(farbe);
        setVerfuegbar(verfuegbar);
        setPreis(preis);
        setVdatum(vdatum);
    }


    public Bike(String bezeichnung, String farbe,
                Boolean verfuegbar, Double preis, LocalDate vdatum) {
        setBikeId();
        setBezeichnung(bezeichnung);
        setFarbe(farbe);
        setVerfuegbar(verfuegbar);
        setPreis(preis);
        setVdatum(vdatum);
    }

    public Bike() {
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


    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }
    public void setPreis(Double preis){
        this.preis = preis;
    }
    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }
    /**********************************************************************************/
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setVerfuegbar(Boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }

    public void setVdatum(LocalDate vdatum) {
        this.vdatum = vdatum;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getFarbe() {
        return farbe;
    }

    public Boolean getVerfuegbar() {
        return verfuegbar;
    }

    public Double getPreis() {
        return preis;
    }

    public LocalDate getVdatum() {
        return vdatum;
    }
    /****************************************************************************/
    @Override
    public Bike clone(){
        return new Bike(bikeId,bezeichnung,farbe,verfuegbar,preis,vdatum);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(bikeId, bike.bikeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bikeId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("bikeId: ").append(bikeId);
        sb.append(", bezeichnung: ").append(bezeichnung).append('\'');
        sb.append(", farbe: ").append(farbe).append('\'');
        sb.append(", verfuegbar: ").append(verfuegbar);
        sb.append(", preis: ").append(preis);
        sb.append(", vdatum: ").append(vdatum);
        return sb.toString();
    }
}
