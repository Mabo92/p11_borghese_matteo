package it.cinema.multisala;

/**
 * Classe <code>Recensione</code> contiene tutte le informazioni
 * relative alle recensioni dei film realizzate dei clienti.
 * 
 * @version 1.00 08 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Recensione {
	
	/** Id della recensione */
	private int idRecensione;
	
	/** Voto del film */
	private int voto;
	
	/** Commento del film */
	private String commento;
	
	/** Nome utente del recensore */
	private String username;
	
	/** Parole non lecite */
	private final String[] BLACK_LIST = {"parolaccia", "insulto"};

	/**
	 * Costruttore di recensione.
	 * 
	 * @param idRecensione id della recensione
	 * @param voto voto del film
	 * @param commento commento del film
	 * @param username ome utente del recensore
	 */
	public Recensione(int idRecensione, int voto, String commento, String username) {
		super();
		this.setIdRecensione(idRecensione);;
		this.setVoto(voto);;
		this.setCommento(commento);;
		this.setUsername(username);;
	}

	/**
	 * Get id della recensione.
	 * 
	 * @return id della recensione
	 */
	public int getIdRecensione() {
		return idRecensione;
	}

	/**
	 * Set id della recensione.
	 * 
	 * @param idRecensione id della recensione
	 */
	public void setIdRecensione(int idRecensione) {
		this.idRecensione = idRecensione;
	}

	/**
	 * Get voto del film.
	 * 
	 * @return voto del film
	 */
	public int getVoto() {
		return voto;
	}

	/**
	 * Set voto del film.
	 * 
	 * @param voto voto del film
	 */
	public void setVoto(int voto) {
		this.voto = voto;
	}

	/**
	 * Get commento del film.
	 * 
	 * @return commento del film
	 */
	public String getCommento() {
		return commento;
	}

	/**
	 * Set commento del film.
	 * 
	 * @param commento commento del film
	 */
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	/**
	 * Get nome utente del recensore.
	 * 
	 * @return nome utente del recensore
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set nome utente del recensore.
	 * 
	 * @param username nome utente del recensore
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Valida la recensione.
	 * 
	 * @param voto voto del film
	 * @param commento commento al film
	 * @return true se la recensione è conforme, false altrimenti
	 */
	public boolean verificaRecensione(int voto,String commento) {
		if(voto == 0) {
			System.err.println("inserire un voto");
			return false; //qualora l'utente non inserisca il voto esso è 0 val default
		}
		
		if(commento == null || commento.equals("")) {
			System.err.println("inserire un commento");
			return false; //nessun commento inserito
		}else{
			for (String badWord : BLACK_LIST) {
				//rimuovo dal commento tutte le parole indesiderate
		        commento = commento.replaceAll("(?i)\\b[^\\w -]*" + badWord 
		        								+ "[^\\w -]*\\b", "****");
		    }
			this.setCommento(commento);
			return true;
		}
		
	}

}
