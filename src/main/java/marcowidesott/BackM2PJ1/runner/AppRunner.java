package marcowidesott.BackM2PJ1.runner;

import marcowidesott.BackM2PJ1.entities.Postazione;
import marcowidesott.BackM2PJ1.entities.Prenotazione;
import marcowidesott.BackM2PJ1.entities.Utente;
import marcowidesott.BackM2PJ1.repositories.PostazioneRepository;
import marcowidesott.BackM2PJ1.repositories.PrenotazioneRepository;
import marcowidesott.BackM2PJ1.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Override
    public void run(String... args) throws Exception {
        Postazione postazione1 = new Postazione("001", "Attico con vista", Postazione.Tipo.PRIVATO, 4, "Edificio 1", "Via Milano 1", "Milano");
        Postazione postazione2 = new Postazione("002", "Open space", Postazione.Tipo.OPENSPACE, 6, "Edificio 2 ", "Via Roma 1", "Roma");
        Postazione postazione3 = new Postazione("003", "Sala riunioni", Postazione.Tipo.SALA_RIUNIONI, 8, "Edificio 3", "Via Napoli 1", "Napoli");

        postazioneRepository.save(postazione1);
        postazioneRepository.save(postazione2);
        postazioneRepository.save(postazione3);

        Utente utente1 = new Utente("Mario1", "Mario Rossi", "mariorossi@gmail.com");
        Utente utente2 = new Utente("Gino99", "Gino Pasticcio", "ginopast@gmail.com");
        Utente utente3 = new Utente("Eziocreed21", "Ezio Editore", "ezioeditore@gmail.com");

        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
        utenteRepository.save(utente3);

        Prenotazione prenotazione1 = new Prenotazione(utente1, postazione1, LocalDate.now());
        Prenotazione prenotazione2 = new Prenotazione(utente2, postazione2, LocalDate.now().plusMonths(2));
        Prenotazione prenotazione3 = new Prenotazione(utente3, postazione3, LocalDate.now().plusYears(3));

        prenotazioneRepository.save(prenotazione1);
        prenotazioneRepository.save(prenotazione2);
        prenotazioneRepository.save(prenotazione3);

        System.out.println("Postazioni create:");
        postazioneRepository.findAll().forEach(postazione -> System.out.println(postazione.getCodiceUnivoco() + " - " + postazione.getDescrizione()));

        System.out.println("Utenti creati:");
        utenteRepository.findAll().forEach(utente -> System.out.println(utente.getUsername() + " - " + utente.getNomeCompleto()));

        System.out.println("Prenotazioni create:");
        prenotazioneRepository.findAll().forEach(prenotazione ->
                System.out.println("Prenotazione: Utente " + prenotazione.getUtente().getUsername() +
                        " ha prenotato la postazione " + prenotazione.getPostazione().getCodiceUnivoco() +
                        " per il giorno " + prenotazione.getDataPrenotazione()));
    }
}
