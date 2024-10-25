package marcowidesott.BackM2PJ1.services;

import marcowidesott.BackM2PJ1.entities.Postazione;
import marcowidesott.BackM2PJ1.entities.Prenotazione;
import marcowidesott.BackM2PJ1.entities.Utente;
import marcowidesott.BackM2PJ1.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Optional<Prenotazione> getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id);
    }

    public Prenotazione createOrUpdatePrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(Long id) {
        prenotazioneRepository.deleteById(id);
    }

    public List<Prenotazione> findByPostazioneAndData(Postazione postazione, LocalDate dataPrenotazione) {
        return prenotazioneRepository.findByPostazioneAndDataPrenotazione(postazione, dataPrenotazione);
    }

    public List<Prenotazione> findByUtenteAndData(Utente utente, LocalDate dataPrenotazione) {
        return prenotazioneRepository.findByUtenteAndDataPrenotazione(utente, dataPrenotazione);
    }
}
