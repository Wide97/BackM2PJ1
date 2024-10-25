package marcowidesott.BackM2PJ1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codiceUnivoco;
    private String descrizione;
    private Tipo tipo;
    private int maxOccupanti;
    private String nomeEdificio;
    private String indirizzoEdificio;
    private String cittaEdificio;
    public enum Tipo {
        PRIVATO, OPENSPACE, SALA_RIUNIONI
    }
}
