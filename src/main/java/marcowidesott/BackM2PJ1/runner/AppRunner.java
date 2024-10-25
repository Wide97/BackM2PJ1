package marcowidesott.BackM2PJ1.runner;

import marcowidesott.BackM2PJ1.entities.Postazione;
import marcowidesott.BackM2PJ1.entities.Prenotazione;
import marcowidesott.BackM2PJ1.entities.Utente;
import marcowidesott.BackM2PJ1.services.PostazioneService;
import marcowidesott.BackM2PJ1.services.PrenotazioneService;
import marcowidesott.BackM2PJ1.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Override
    public void run(String... args) throws Exception {
        // 1. Creare postazioni
        Postazione postazione1 = postazioneService.createOrUpdatePostazione(new Postazione("001", "Attico con vista", Postazione.Tipo.PRIVATO, 4, "Edificio 1", "Via Milano 1", "Milano"));
        Postazione postazione2 = postazioneService.createOrUpdatePostazione(new Postazione("002", "Open space ampio", Postazione.Tipo.OPENSPACE, 10, "Edificio 2", "Corso Italia 10", "Roma"));
        Postazione postazione3 = postazioneService.createOrUpdatePostazione(new Postazione("003", "Sala riunioni moderna", Postazione.Tipo.SALA_RIUNIONI, 8, "Edificio 3", "Via Napoli 3", "Napoli"));

        // 2. Creare utenti
        Utente utente1 = utenteService.createOrUpdateUtente(new Utente("mario1", "Mario Rossi", "mariorossi@gmail.com"));
        Utente utente2 = utenteService.createOrUpdateUtente(new Utente("gino99", "Gino Pasticcio", "ginopast@gmail.com"));
        Utente utente3 = utenteService.createOrUpdateUtente(new Utente("eziocreed21", "Ezio Editore", "ezioeditore@gmail.com"));

        // 3. Creare prenotazioni
        Prenotazione prenotazione1 = prenotazioneService.createOrUpdatePrenotazione(new Prenotazione(utente1, postazione1, LocalDate.now()));
        Prenotazione prenotazione2 = prenotazioneService.createOrUpdatePrenotazione(new Prenotazione(utente2, postazione2, LocalDate.now().plusDays(1)));
        Prenotazione prenotazione3 = prenotazioneService.createOrUpdatePrenotazione(new Prenotazione(utente3, postazione3, LocalDate.now().plusDays(2)));

        // 4. Recuperare tutte le prenotazioni
        System.out.println("Tutte le prenotazioni:");
        List<Prenotazione> prenotazioni = prenotazioneService.getAllPrenotazioni();
        prenotazioni.forEach(p -> System.out.println(p.getId() + ": Utente " + p.getUtente().getNomeCompleto() + " ha prenotato la postazione " + p.getPostazione().getCodiceUnivoco()));

        // 5. Recuperare una prenotazione per ID
        Prenotazione prenotazioneById = prenotazioneService.getPrenotazioneById(prenotazione1.getId());
        System.out.println("Prenotazione trovata per ID: " + prenotazioneById.getId());

        // 6. Recuperare prenotazioni per postazione e data
        System.out.println("Prenotazioni per postazione e data:");
        List<Prenotazione> prenotazioniPerPostazione = prenotazioneService.findByPostazioneAndData(postazione1, LocalDate.now());
        prenotazioniPerPostazione.forEach(p -> System.out.println("Prenotazione ID: " + p.getId() + " per postazione " + p.getPostazione().getCodiceUnivoco()));

        // 7. Recuperare prenotazioni per utente e data
        System.out.println("Prenotazioni per utente e data:");
        List<Prenotazione> prenotazioniPerUtente = prenotazioneService.findByUtenteAndData(utente1, LocalDate.now());
        prenotazioniPerUtente.forEach(p -> System.out.println("Prenotazione ID: " + p.getId() + " per utente " + p.getUtente().getNomeCompleto()));

        // 8. Aggiornare una prenotazione
        prenotazioneById.setDataPrenotazione(LocalDate.now().plusDays(5));
        Prenotazione prenotazioneAggiornata = prenotazioneService.createOrUpdatePrenotazione(prenotazioneById);
        System.out.println("Prenotazione aggiornata: " + prenotazioneAggiornata.getId() + " per data " + prenotazioneAggiornata.getDataPrenotazione());

        // 9. Eliminare una prenotazione
        prenotazioneService.deletePrenotazione(prenotazione1.getId());
        System.out.println("Prenotazione eliminata: ID " + prenotazione1.getId());

        // 10. Eliminare un utente
        utenteService.deleteUtente(utente1.getId());
        System.out.println("Utente eliminato: ID " + utente1.getId());

        // 11. Eliminare una postazione
        postazioneService.deletePostazione(postazione1.getId());
        System.out.println("Postazione eliminata: ID " + postazione1.getId());
    }
}

