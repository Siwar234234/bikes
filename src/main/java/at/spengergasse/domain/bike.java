
package at.spengergasse.domain;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode (of = "orderId", callSuper = false)
@Entity
public class bike {
    @Id
    private Long      bikeId;
    private String    bezeichnung;
    private String    Farben;
    private Boolean   verfuegbar;
    private Double    Kosten;
    private LocalDate vdatum;


    private static final AtomicLong sequence = new AtomicLong(1000);


    public void setAccountId(){
        this.bikeId = sequence.getAndIncrement();
    }
}
