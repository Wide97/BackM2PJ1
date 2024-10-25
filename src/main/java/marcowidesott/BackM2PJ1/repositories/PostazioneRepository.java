package marcowidesott.BackM2PJ1.repositories;

import marcowidesott.BackM2PJ1.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoAndCittaEdificio(Postazione.Tipo tipo, String citta);
}
