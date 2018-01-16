package it.cinema.multisala;

/**
 * Classe <code>CartaDiCredito</code> contiene tutte le informazioni
 * relative alla carta di credito del cliente.
 * 
 * @version 1.00 08 Gen 2018
 * @author Matteo Borghese
 *
 */
public class CartaDiCredito {
	
	/** Numero carta di credito */
	private String numeroCarta;
	
	/** Saldo della carta */
	private double saldo;

	/**
	 * Costruttore di carta di credito.
	 * 
	 * @param numeroCarta numero della carta di credito
	 * @param saldo saldo attuale della carta di credito
	 */
	public CartaDiCredito(String numeroCarta, double saldo) {
		super();
		this.numeroCarta = numeroCarta;
		this.saldo = saldo;
	}

	/**
	 * Get numero della carta di credito.
	 * 
	 * @return numero della carta di credito
	 */
	public String getNumeroCarta() {
		return numeroCarta;
	}

	/**
	 * Set numero della carta di credito.
	 * 
	 * @param numeroCarta numero della carta di credito
	 */
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	/**
	 * Get saldo attuale della carta di credito.
	 * 
	 * @return saldo attuale della carta di credito
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Set saldo della carta di credito.
	 * 
	 * @param saldo saldo della carta
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	/**
	 * Esegue un pagamento di un dato importo.
	 * 
	 * @param importo cifra da pagare
	 * @return true se pagamento avvenuto con successo, false altrimenti
	 */
	public boolean pagamento(double importo) {
		if(saldo - importo >= 0) {
			saldo = saldo -importo;
			return true;
		}else{
			System.err.println("Denaro insufficiente");
			return false;
		}
		
	}
	
	/**
	 * Aggiunge al saldo della carta il valore indicato.
	 * 
	 * @param importo ammontare del rimborso
	 */
	public void rimborso(double importo) {
		saldo = saldo + importo;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof CartaDiCredito))return false;
	    CartaDiCredito c = (CartaDiCredito)other;
	    //numero carta univoco
	    return this.getNumeroCarta().equals(c.getNumeroCarta());
	}
}
