package it.cinema.multisala;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Classe <code>Programmazione</code> contiene informazioni inerenti la
 * programmazione dei film nei cinema.
 * 
 * @version 1.00 05 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Programmazione {
	
	/** Id della programmazione. */
	private int idProgrammazione;
	
	/** Giorno di proiezione del film. */
	private LocalDateTime giorno;
	
	/** Ora di proiezione del film. */
	private LocalTime oraInizio; 
	
	/** Ora fine proiezione del film. */
	private LocalTime oraFine; 
		
	/** Prenotazioni attive per lo spettacolo. */
	private ArrayList<Prenotazione> prenotazioni;
	
	/** Sala di proiezione. */
	private Sala salaProgrammazione;
	
	/** Film proiettato. */
	private Film film;

	/**
	 * Costruttore di programmazione.
	 * 
	 * @param idProgrammazione id della programmazione
	 * @param giorno giorno di proiezione del film
	 * @param oraInizio ora di proiezione del film
	 * @param oraFine ora fine proiezione del film
	 * @param salaProgrammazione prenotazioni attive per lo spettacolo
	 * @param film film proiettato
	 */
	public Programmazione(int idProgrammazione, LocalDateTime giorno, LocalTime oraInizio, LocalTime oraFine,
						 Sala salaProgrammazione, Film film) {
		super();
		this.idProgrammazione = idProgrammazione;
		this.giorno = giorno;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.prenotazioni = new ArrayList<Prenotazione>();
		this.salaProgrammazione = salaProgrammazione;
		this.film = film;
	}

	/**
	 * Get id della programmazione.
	 * 
	 * @return id della programmazione
	 */
	public int getIdProgrammazione() {
		return idProgrammazione;
	}

	/**
	 * Set id della programmazione.
	 * 
	 * @param idProgrammazione id della programmazione
	 */
	public void setIdProgrammazione(int idProgrammazione) {
		this.idProgrammazione = idProgrammazione;
	}

	/**
	 * Get giorno di proiezione del film.
	 * 
	 * @return giorno di proiezione del film
	 */
	public LocalDateTime getGiorno() {
		return giorno;
	}

	/**
	 * Set giorno di proiezione del film.
	 * 
	 * @param giorno giorno di proiezione del film
	 */
	public void setGiorno(LocalDateTime giorno) {
		this.giorno = giorno;
	}

	/**
	 * ora di proiezione del film.
	 * 
	 * @return ora di proiezione del film
	 */
	public LocalTime getOraInizio() {
		return oraInizio;
	}

	/**
	 * Set ora di proiezione del film.
	 * 
	 * @param oraInizio ora di proiezione del film
	 */
	public void setOraInizio(LocalTime oraInizio) {
		this.oraInizio = oraInizio;
	}

	/**
	 * Get ora fine proiezione del film.
	 * 
	 * @return ora fine proiezione del film
	 */
	public LocalTime getOraFine() {
		return oraFine;
	}

	/**
	 * Set ora fine proiezione del film.
	 * 
	 * @param oraFine ora fine proiezione del film
	 */
	public void setOraFine(LocalTime oraFine) {
		this.oraFine = oraFine;
	}

	/**
	 * Get prenotazioni attive per lo spettacolo.
	 * 
	 * @return prenotazioni attive per lo spettacolo
	 */
	public ArrayList<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	/**
	 * Set prenotazioni attive per lo spettacolo.
	 * 
	 * @param prenotazioni prenotazioni attive per lo spettacolo
	 */
	public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	/**
	 * Get sala di proiezione.
	 * 
	 * @return sala di proiezione
	 */
	public Sala getSalaProgrammazione() {
		return salaProgrammazione;
	}

	/**
	 * Set sala di proiezione.
	 * 
	 * @param salaProgrammazione sala di proiezione.
	 */
	public void setSalaProgrammazione(Sala salaProgrammazione) {
		this.salaProgrammazione = salaProgrammazione;
	}

	/**
	 * Get film proiettato.
	 * 
	 * @return film proiettato
	 */
	public Film getFilm() {
		return film;
	}

	/**
	 * Set film proiettato.
	 * 
	 * @param film film proiettato
	 */
	public void setFilm(Film film) {
		this.film = film;
	}
	
	/**
	 * Aggiunge una prenotazione alla programazione del film.
	 * 
	 * @param p prenotazione da aggiungere
	 * @return true posti liberi sufficienti e prenotazione aggiunta, false altrimenti
	 */
	public boolean aggiungiPrenotazione(Prenotazione p) {
		if(salaProgrammazione.prenotaPosti(p.getFilaPosti(), p.getColonnaPosti())) {
			prenotazioni.add(p);
			return true;
		}else{
			System.err.println("Impossibile prenotare.");
			return false;
		}
	}
	
	/**
	 * Cancella una prenotazione (sequence diagram UC8).
	 * 
	 * @param idPrenotazione id della prenotazione da cancellare
	 * @return true prenotazione cancellata, false altrimenti
	 */
	public boolean cancellaPrenotazione(int idPrenotazione){
		for(Prenotazione p : prenotazioni) {
			if(p.getIdPrenotazione() == idPrenotazione) {
				//posso eliminare la prenotazione se la cancellazione avviene con almeno
				//un'ora di anticipo dall'inizio della proiezione
				if(verificaOraCancellazione()) {
						salaProgrammazione.liberaPosti(p.getFilaPosti(), p.getColonnaPosti());
						int index = prenotazioni.indexOf(p);
						this.prenotazioni.remove(index);
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica se è possibile eliminare la prenotazione (possibili fino ad
	 * un ora prima dell inizio della proiezione del film).
	 * 
	 * @return true prenotazione eliminabile, false altrimenti
	 */
	public boolean verificaOraCancellazione() {
		LocalTime oraAttuale = LocalTime.now();
		LocalDateTime giornoAttuale = LocalDateTime.now();
		int ore = oraInizio.getHour() - oraAttuale.getHour();
		if(giornoAttuale.toLocalDate().equals(giorno.toLocalDate())) {
			if(ore > 0) {
				return true;
			}else{
				System.err.println("superata l'ora a disposizione per la "
						 			+ " cancellazione della prenotazione");
				return false;				
			}
		//giorno della proiezione antecedente alla data della cancellazione
		// della prenotazione, posso sempre eliminarla.
		}else if(giornoAttuale.compareTo(giorno) < 0) {
			return true;
		}else{
			//spettacolo già avvenuto.
			System.err.println("impossibile rimuovere prenotazione");
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Programmazione))return false;
	    Programmazione p= (Programmazione)other;
	    //id univoco
	    return this.getIdProgrammazione() == p.getIdProgrammazione();
	}
	
}
