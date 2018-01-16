package it.cinema.multisala;

/**
 * Classe <code>Abbonamento</code> contiene tutte le informazioni
 * relative agli abbonamenti.
 * 
 * @version 1.00 08 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Abbonamento {

	/** Tipo di abbonamento */
	private String tipo;
	
	/** Biglietti disponibili */
	private int nBigliettiRimanenti;
	
	/** Costo abbonamento */
	private double costo;

	/**
	 * Costruttore abbonamento.
	 * 
	 * @param tipo tipo abbonamento
	 * @param nBigliettiRimanenti dimensione dell abbonamento
	 * @param costo costo abbonamento
	 */
	public Abbonamento(String tipo, int nBigliettiRimanenti, double costo) {
		super();
		this.tipo = tipo;
		this.nBigliettiRimanenti = nBigliettiRimanenti;
		this.costo = costo;
	}

	/**
	 * Get tipo di abbonamento.
	 * 
	 * @return tipo abbonamento
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Set tipo di abbonamento.
	 * 
	 * @param tipo tipo abbonamento
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Get numero dei biglietti disponibili.
	 * 
	 * @return numero biglietti disponibili
	 */
	public int getnBigliettiRimanenti() {
		return nBigliettiRimanenti;
	}

	/**
	 * Set numero dei biglietti disponibili.
	 * 
	 * @param nBigliettiRimanenti numero dei biglietti disponibili
	 */
	public void setnBigliettiRimanenti(int nBigliettiRimanenti) {
		this.nBigliettiRimanenti = nBigliettiRimanenti;
	}

	/**
	 * Get costo dell abbonamento.
	 * 
	 * @return costo abbonamento
	 */
	public double getCosto() {
		return costo;
	}

	/**
	 * Set costo abbonamento.
	 * 
	 * @param costo costo abbonamento
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	/**
	 * Sottrae da quelli disponibili i biglietti utilizzati.
	 * 
	 * @param nBiglietti biglietti usati
	 * @return true operazione andata a buon fine, false altrimenti
	 */
	public boolean aggiornaBiglietti(int nBiglietti) {
		if(this.nBigliettiRimanenti - nBiglietti >= 0) {
			this.nBigliettiRimanenti = this.nBigliettiRimanenti - nBiglietti;
			return true;
		}else{
			System.err.println("Biglietti disponibili insufficienti");
			return false;
		}
	}
	
	/**
	 * Restituisce i biglietti rimborsato.
	 * 
	 * @param nBiglietti biglietti rimborsati
	 */
	public void rimborsaBiglietti(int nBiglietti) {
		this.nBigliettiRimanenti = this.nBigliettiRimanenti + nBiglietti;	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Abbonamento))return false;
	    Abbonamento a = (Abbonamento)other;
	    return this.getTipo().equals(a.getTipo())
	    		&& this.getCosto() == a.getCosto()
	    		&& this.getnBigliettiRimanenti() == a.getnBigliettiRimanenti();
	}
}
