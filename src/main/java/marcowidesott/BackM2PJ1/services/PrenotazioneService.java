package marcowidesott.BackM2PJ1.services;

import marcowidesott.BackM2PJ1.entities.Postazione;
import marcowidesott.BackM2PJ1.entities.Prenotazione;
import marcowidesott.BackM2PJ1.entities.Utente;
import marcowidesott.BackM2PJ1.exception.BadRequestException;
import marcowidesott.BackM2PJ1.exception.ResourceNotFoundException;
import marcowidesott.BackM2PJ1.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;


    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione con ID " + id + " non trovata"));
    }

    public Prenotazione createOrUpdatePrenotazione(Prenotazione prenotazione) {
        if (prenotazione.getDataPrenotazione() == null || prenotazione.getDataPrenotazione().isBefore(LocalDate.now())) {
            throw new BadRequestException("La data di prenotazione non può essere nel passato.");
        }
        return prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prenotazione con ID " + id + " non trovata");
        }
        prenotazioneRepository.deleteById(id);
    }

    public List<Prenotazione> findByPostazioneAndData(Postazione postazione, LocalDate dataPrenotazione) {
        return prenotazioneRepository.findByPostazioneAndDataPrenotazione(postazione, dataPrenotazione);
    }

    public List<Prenotazione> findByUtenteAndData(Utente utente, LocalDate dataPrenotazione) {
        return prenotazioneRepository.findByUtenteAndDataPrenotazione(utente, dataPrenotazione);
    }
}
