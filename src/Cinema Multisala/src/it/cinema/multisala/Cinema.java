package it.cinema.multisala;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Classe <code>Cinema</code> contiene informazioni inerenti il cinema e le sale.
 * 
 * @version 1.00 05 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Cinema {
	
	/** Id del cinema. */
	private int idCinema;
	
	/** Nome del cinema. */
	private String nome;
	
	/** Città del cinema. */
	private String citta;
	
	/** Indirizzo del cinema. */
	private String indirizzo;
	
	/** Sale del cinema. */
	private ArrayList<Sala> sala;
	
	/** Programmazione del cinema. */
	private ArrayList<Programmazione> programmazione;

	/**
	 * Costruttore di cinema.
	 * 
	 * @param idCinema id del cinema
	 * @param nome nome del cinema
	 * @param citta città del cinema
	 * @param indirizzo indirizzo del cinema
	 */
	public Cinema(int idCinema, String nome, String citta, String indirizzo) {
		super();
		this.idCinema = idCinema;
		this.nome = nome;
		this.citta = citta;
		this.indirizzo = indirizzo;
		this.sala = new ArrayList<Sala>();
		this.programmazione = new ArrayList<Programmazione>();
	}

	
	/**
	 * Get id cinema.
	 * 
	 * @return id cinema
	 */
	public int getIdCinema() {
		return idCinema;
	}

	/**
	 * Set id cinema.
	 * 
	 * @param idCinema id del cinema
	 */
	public void setIdCinema(int idCinema) {
		this.idCinema = idCinema;
	}

	/**
	 * Get nome del cinema.
	 * 
	 * @return nome del cinema
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Set nome cinema.
	 * 
	 * @param nome nome del cinema
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Get nome della città in cui si trova il cinema.
	 * 
	 * @return nome della città
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * Set del nome della città.
	 * 
	 * @param citta nome della città
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * Get indirizzo cinema.
	 * 
	 * @return indirizzo cinema
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Set indirizzo cinema.
	 * 
	 * @param indirizzo indirizzo del cinema
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Get sale contenute nel cinema.
	 * 
	 * @return sale del cinema
	 */
	public ArrayList<Sala> getSala() {
		return sala;
	}

	/**
	 * Set sale del cinema.
	 * 
	 * @param sala sale del cinema (tutte le sale possedute
	 * dal cinema)
	 */
	public void setSala(ArrayList<Sala> sala) {
		this.sala = sala;
	}
	
	/**
	 * Get programmazione del cinema.
	 * 
	 * @return programmazione cinema (tutte le programmazioni 
	 * associate al cinema)
	 */
	public ArrayList<Programmazione> getProgrammazione() {
		return programmazione;
	}

	/**
	 * Set programmazione cinema.
	 * 
	 * @param programmazione programmazione del cinema
	 */
	public void setProgrammazione(ArrayList<Programmazione> programmazione) {
		this.programmazione = programmazione;
	}

	/**
	 * Aggiunge una sala tra quelle disponibili nel cinema.
	 * 
	 * @param s nuova sala da inserire
	 * @return true inserimento corretto, false altrimenti
	 */
	public boolean aggiungiSala(Sala s) {
		if(sala.contains(s)) {
			System.err.println("la sala è già stata inserita");
			return false;
		}else{
			sala.add(s);
			return true;
		}
	}
	
	/**
	 * Rimuove una sala tra quelle disponibili nel cinema.
	 * 
	 * @param idSala id della sala da rimuovere.
	 * @return true se l'operazione va a buon fine, false altrimenti
	 */
	public boolean rimuoviSala(int idSala) {
		int index = -1;
		for(Sala s : sala) {
			if(s.getIdSala() == idSala) {
				index = sala.indexOf(s);
				sala.remove(index);
				return true;
			}
		}
		System.err.println("sala non trovata");
		return false;
	}
	
	/**
	 * Aggiunge una programmazione al cinema.
	 * 
	 * @param p nuova programmazione
	 * @return true inserimento andato a buon fine, false altrimenti
	 */
	public boolean aggiungiProgrammazioneFilm(Programmazione p) {
		if(programmazione.contains(p)) {
			System.err.println("la programmazione è già stata inserita");
			return false;
		}else if(controlloSovrapposizioni(p)) {
			System.err.println("sovrapposizione nella programmazione");
			return false;
		}else{
			programmazione.add(p);
			return true;
		}
	}
	
	/**
	 * Modifico la programmazione di un film.
	 * 
	 * @param progDaModificare programmazione con i vecchi dati.
	 * @param progModificata programmazione del film con i nuovi dati
	 * @return true modifica della programmazione del film avvenuto con successo,
	 * false altrimenti
	 */
	public boolean modificaProgrammazioneFilm(Programmazione progDaModificare, 
			Programmazione progModificata) {
		//salvo una copia della programmazione da modificare
		Programmazione temp = new Programmazione(progDaModificare.getIdProgrammazione(),
				progDaModificare.getGiorno(), progDaModificare.getOraInizio(),
				progDaModificare.getOraFine(), progDaModificare.getSalaProgrammazione(),
				progDaModificare.getFilm());
		temp.setPrenotazioni(progDaModificare.getPrenotazioni());
		//elimino la programmazione da modificare
		if(this.rimuoviProgrammazioneFilm(progDaModificare.getIdProgrammazione())) {
			//dopo l'eliminazione della vecchia inserisco quella aggiornata
			if(this.aggiungiProgrammazioneFilm(progModificata)){
				return true;
			//l'inserimento di quella aggiornata non va a buon fine
			}else{
				//ripristino la vecchia programmazione
				this.aggiungiProgrammazioneFilm(progDaModificare);
				return false;
			}
		//la programmazione da modificare non può essere eliminata
		}else{
			System.err.println("impossibile modificare la programmazione");
			return false;
		}
	}
	
	/**
	 * Rimuove programmazione esistente
	 * 
	 * @param id id della programmazione da rimuovere
	 * @return true se l'operazione è andata a buon fine, false altrimenti
	 */
	public boolean rimuoviProgrammazioneFilm(int id) {
		LocalDateTime giornoAttuale = LocalDateTime.now();
		int index = -1;
		for(Programmazione p : programmazione) {
			if(p.getIdProgrammazione() == id
					&& p.getFilm().getDataRimozione().isBefore(giornoAttuale)) {
				//il film a cui è associata la programmazione esiste e la data di
				// rimozione è superata
				index = programmazione.indexOf(p);
				programmazione.remove(index);
				return true;
			}
		}
		System.err.println("programmazione non trovata");
		return false;
	}
	
	/**
	 * Verifica che la nuova programmmazione inserita non generi sovrapposizione
	 * con la precedente
	 * 
	 * @param p nuova programmazione da inserire 
	 * @return true se l'operazione è andata a buon fine, false altrimenti
	 */
	public boolean controlloSovrapposizioni(Programmazione p) {
		for (Programmazione progCinema : programmazione) {
			if(p.getSalaProgrammazione().getIdSala() == progCinema.getSalaProgrammazione().getIdSala()
					&& p.getGiorno().equals(progCinema.getGiorno())
					&& p.getOraInizio().isBefore(progCinema.getOraFine())
					&& p.getOraFine().isAfter(progCinema.getOraInizio())) {
				System.err.println("Esiste un film programmato lo stesso giorno,"
						       + "nella stessa sala che si sovrappone come"
						       + " orario");
				return true;
			}
		}
		return false;
	}

}
