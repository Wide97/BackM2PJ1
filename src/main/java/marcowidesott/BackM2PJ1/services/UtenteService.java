package marcowidesott.BackM2PJ1.services;

import marcowidesott.BackM2PJ1.entities.Utente;
import marcowidesott.BackM2PJ1.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> getUtenteById(Long id) {
        return utenteRepository.findById(id);
    }

    public Utente createOrUpdateUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    public void deleteUtente(Long id) {
        utenteRepository.deleteById(id);
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

}
