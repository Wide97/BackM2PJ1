package marcowidesott.BackM2PJ1.services;

import marcowidesott.BackM2PJ1.entities.Postazione;
import marcowidesott.BackM2PJ1.repositories.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    public List<Postazione> getAllPostazioni() {
        return postazioneRepository.findAll();
    }

    public Optional<Postazione> getPostazioneById(Long id) {
        return postazioneRepository.findById(id);
    }

    public Postazione createOrUpdatePostazione(Postazione postazione) {
        return postazioneRepository.save(postazione);
    }

    public void deletePostazione(Long id) {
        postazioneRepository.deleteById(id);
    }

    public List<Postazione> findByTipoAndCitta(Postazione.Tipo tipo, String citta) {
        return postazioneRepository.findByTipoAndCittaEdificio(tipo, citta);
    }
}
