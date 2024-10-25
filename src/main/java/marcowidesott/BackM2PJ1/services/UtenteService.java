package marcowidesott.BackM2PJ1.services;

import marcowidesott.BackM2PJ1.entities.Utente;
import marcowidesott.BackM2PJ1.exception.BadRequestException;
import marcowidesott.BackM2PJ1.exception.ResourceNotFoundException;
import marcowidesott.BackM2PJ1.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Utente getUtenteById(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utente con ID " + id + " non trovato"));
    }

    public Utente createOrUpdateUtente(Utente utente) {
        if (utente.getEmail() == null || utente.getEmail().isEmpty()) {
            throw new BadRequestException("L'email dell'utente non può essere vuota.");
        }
        return utenteRepository.save(utente);
    }

    public void deleteUtente(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utente con ID " + id + " non trovato");
        }
        utenteRepository.deleteById(id);
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

}
