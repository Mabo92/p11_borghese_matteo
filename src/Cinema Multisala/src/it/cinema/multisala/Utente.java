package it.cinema.multisala;

/**
 * Classe <code>Utente</code> contiene informazioni inerenti l utente 
 * 
 * @version 1.00 06 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Utente {

	/** Id utente*/
	private int id;
	
	/** Nome utente */
	private String nome;
	
	/** Cognome utente */
	private String cognome;
	
	/** Sesso utente */
	private String sesso;
	
	/** Email utente */
	private String email;
	
	/** Utente loggato */
	private boolean loggato;
	
	/** Password utente*/
	private String password;
	
	/** Password per verificare corretta digitalizzazione*/
	private String passwordVerifica;
	
	/** Username */
	private String nomeUtente;
	
	/** Frase segreta*/
	private String fraseSegreta;
	
	/**
	 * Costruttore della classe utente.
	 * 
	 * @param id id utente
	 * @param nome nome utente
	 * @param cognome cognome utente
	 * @param sesso sesso sesso utente
	 * @param email email utente
	 * @param loggato stato utente
	 * @param password password utente
	 * @param passwordVerifica ridigita password
	 * @param nomeUtente username
	 * @param fraseSegreta frase segreta
	 */
	public Utente(int id, String nome, String cognome, String sesso, String email, boolean loggato, 
			String password, String passwordVerifica, String nomeUtente, String fraseSegreta) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.email = email;
		this.loggato = loggato;
		this.password = password;
		this.passwordVerifica = passwordVerifica;
		this.nomeUtente = nomeUtente;
		this.fraseSegreta = fraseSegreta;
	}

	/**
	 * Get id.
	 * 
	 * @return id utente
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set id.
	 * 
	 * @param id id utente
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get nome utente.
	 * 
	 * @return nome utente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Set nome utente.
	 * 
	 * @param nome nome utente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Get cognome.
	 * 
	 * @return cognome utente
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Set cognome utente.
	 * 
	 * @param cognome cognome utente
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Get Sesso.
	 * 
	 * @return sesso dell utente
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * Set sesso.
	 * 
	 * @param sesso sesso dell utente
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	/**
	 * Get email.
	 * 
	 * @return email utente
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email.
	 * 
	 * @param email email utente
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set stato utente.
	 * 
	 * @param loggato stato utente
	 */ 
	public void setLoggato(boolean loggato) {
		this.loggato = loggato;
	}
		
	/**
	 * Get password.
	 * 
	 * @return password utente
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password.
	 * 
	 * @param password password utente
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get password di verifica.
	 * 
	 * @return password di verifica
	 */
	public String getPasswordVerifica() {
		return passwordVerifica;
	}

	/**
	 * Set password di verifica
	 * 
	 * @param passwordVerifica password di verifica
	 */
	public void setPasswordVerifica(String passwordVerifica) {
		this.passwordVerifica = passwordVerifica;
	}

	/**
	 * Get username.
	 * 
	 * @return username
	 */
	public String getNomeUtente() {
		return nomeUtente;
	}

	/**
	 * Set username.
	 * 
	 * @param nomeUtente username
	 */
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
		
	/**
	 * Get frase segreta
	 * 
	 * @return frase segreta
	 */
	public String getFraseSegreta() {
		return fraseSegreta;
	}

	/**
	 * Set frase segreta
	 * 
	 * @param fraseSegreta frase segreta
	 */
	public void setFraseSegreta(String fraseSegreta) {
		this.fraseSegreta = fraseSegreta;
	}

	/**
	 * Ritorna stato dell utente.
	 * 
	 * @return stato utente
	 */
	public boolean isLoggato() {
		return loggato;
	}
}
