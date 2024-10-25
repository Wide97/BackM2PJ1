package marcowidesott.BackM2PJ1.services;

import marcowidesott.BackM2PJ1.entities.Postazione;
import marcowidesott.BackM2PJ1.exception.BadRequestException;
import marcowidesott.BackM2PJ1.exception.ResourceNotFoundException;
import marcowidesott.BackM2PJ1.repositories.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    public List<Postazione> getAllPostazioni() {
        return postazioneRepository.findAll();
    }

    public Postazione getPostazioneById(Long id) {
        return postazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postazione con ID " + id + " non trovata"));
    }

    public Postazione createOrUpdatePostazione(Postazione postazione) {
        if (postazione.getMaxOccupanti() <= 0) {
            throw new BadRequestException("Il numero massimo di occupanti deve essere maggiore di zero.");
        }
        return postazioneRepository.save(postazione);
    }

    public void deletePostazione(Long id) {
        if (!postazioneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Postazione con ID " + id + " non trovata");
        }
        postazioneRepository.deleteById(id);
    }

    public List<Postazione> findByTipoAndCitta(Postazione.Tipo tipo, String citta) {
        return postazioneRepository.findByTipoAndCittaEdificio(tipo, citta);
    }
}
