package marcowidesott.BackM2PJ1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codiceUnivoco;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private int maxOccupanti;
    private String nomeEdificio;
    private String indirizzoEdificio;
    private String cittaEdificio;
    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioni;

    public Postazione(String codiceUnivoco, String descrizione, Tipo tipo, int maxOccupanti, String nomeEdificio, String indirizzoEdificio, String cittaEdificio) {
        this.codiceUnivoco = codiceUnivoco;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.maxOccupanti = maxOccupanti;
        this.nomeEdificio = nomeEdificio;
        this.indirizzoEdificio = indirizzoEdificio;
        this.cittaEdificio = cittaEdificio;
    }

    public enum Tipo {
        PRIVATO, OPENSPACE, SALA_RIUNIONI
    }
}
