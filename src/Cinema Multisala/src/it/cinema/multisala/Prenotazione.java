package it.cinema.multisala;


/**
 * Classe <code>Prenotazione</code> contiene informazioni inerenti le 
 * prenotazioni del cliente.
 * 
 * @version 1.00 05 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Prenotazione {
	
	/** Id della prenotazione. */
	private int idPrenotazione;
	
	/** Numero dei biglietti associati alla prenotazione. */
	private int numeroDiBiglietti;
	
	/** Fila posti prenotati. */
	private int[] filaPosti;
	
	/** Colonna posti prenotato. */
	private int[] colonnaPosti;
	
	/** Costo singolo biglietto. */
	private double costoSingolo;
	
	/** Programmazione spettacolo prenotato. */
	private Programmazione programmazione;

	/**
	 * Costruttore di prenotazione.
	 * 
	 * @param idPrenotazione id della prenotazione
	 * @param numeroDiBiglietti numero dei biglietti
	 * @param costoSingolo costo singolo biglietto
	 * @param programmazione programmazione dello spettacolo prenotato
	 */
	public Prenotazione(int idPrenotazione, int numeroDiBiglietti,
			double costoSingolo, Programmazione programmazione) {
		super();
		this.idPrenotazione = idPrenotazione;
		this.numeroDiBiglietti = numeroDiBiglietti;
		this.filaPosti = new int[numeroDiBiglietti];
		this.colonnaPosti = new int[numeroDiBiglietti];
		this.costoSingolo = costoSingolo;
		this.programmazione = programmazione;
	}

	/**
	 * Get id delle prenotazione.
	 * 
	 * @return id delle prenotazione
	 */
	public int getIdPrenotazione() {
		return idPrenotazione;
	}

	/**
	 * Set id delle prenotazione.
	 * 
	 * @param idPrenotazione id delle prenotazione
	 */
	public void setIdPrenotazione(int idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}

	/**
	 * Get numero dei biglietti da prenotare.
	 * 
	 * @return numero dei biglietti da prenotare
	 */
	public int getNumeroDiBiglietti() {
		return numeroDiBiglietti;
	}

	/**
	 * Set numero dei biglietti da prenotare.
	 * 
	 * @param numeroDiBiglietti numero dei biglietti da prenotare
	 */
	public void setNumeroDiBiglietti(int numeroDiBiglietti) {
		this.numeroDiBiglietti = numeroDiBiglietti;
	}

	/**
	 * Get fila dei posti.
	 * 
	 * @return fila posti
	 */
	public int[] getFilaPosti() {
		return filaPosti.clone();
	}

	/**
	 * Set fila dei posti.
	 * 
	 * @param filaPosti fila posti
	 */
	public void setFilaPosti(int[] filaPosti) {
		this.filaPosti = filaPosti.clone();
	}

	/**
	 * Get colonna posti.
	 * 
	 * @return colonna posti
	 */
	public int[] getColonnaPosti() {
		return colonnaPosti.clone();
	}

	/**
	 * Set colonna posti.
	 * 
	 * @param colonnaPosti colonna dei posti
	 */
	public void setColonnaPosti(int[] colonnaPosti) {
		this.colonnaPosti = colonnaPosti.clone();
	}

	/**
	 * Get costo singolo biglietto.
	 * 
	 * @return costo del singolo biglietto
	 */
	public double getCostoSingolo() {
		return costoSingolo;
	}

	/**
	 * Set costo del singolo biglietto.
	 * 
	 * @param costoSingolo costo del singolo biglietto
	 */
	public void setCostoSingolo(double costoSingolo) {
		this.costoSingolo = costoSingolo;
	}
		
	/**
	 * Get programmazione dello spettacolo prenotato.
	 * 
	 * @return programmazione dello spettacolo prenotato
	 */
	public Programmazione getProgrammazione() {
		return programmazione;
	}

	/**
	 * Set programmazione dello spettacolo prenotato.
	 * 
	 * @param programmazione programmazione dello spettacolo prenotato
	 */
	public void setProgrammazione(Programmazione programmazione) {
		this.programmazione = programmazione;
	}

	/**
	 * Calcola il costo totale.
	 * 
	 * @return costo totale
	 */
	public double calcolaTotale() {
		return this.costoSingolo * this.numeroDiBiglietti;
	}

}
