package marcowidesott.BackM2PJ1.repositories;

import marcowidesott.BackM2PJ1.entities.Postazione;
import marcowidesott.BackM2PJ1.entities.Prenotazione;
import marcowidesott.BackM2PJ1.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate dataPrenotazione);

    List<Prenotazione> findByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione);
}
