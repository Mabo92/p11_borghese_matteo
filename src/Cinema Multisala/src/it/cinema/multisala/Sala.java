package it.cinema.multisala;

/**
 * Classe <code>Sala</code> contiene informazioni inerenti la sala 
 * ed il numero di posti.
 * 
 * @version 1.00 05 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Sala {
	
	/** id della sala. */
	private int idSala;
	
	/** nome della sala. */
	private String nomeSala;
	
	/** numero delle file della sala. */
	private int numeroFile;
	
	/** numero dei posti per ogni fila. */
	private int postiPerFila;
	
	/**
	 * Indica quali posti della sala suddivisi per
	 * fila e posto sono disponibili od occupati.
	 */
	private boolean posti[][];
	
	/** posti totali della sala. */
	private int postiTotali;
	
	/** posti non prenotati. */
	private int postiLiberi;
	
	/**
	 * costruttore di sala.
	 *
	 * @param idSala  id sala
	 * @param nomeSala  nome della sala
	 * @param numeroFile  numero file
	 * @param postiPerFila  numero posti per fila
	 */
	public Sala(int idSala, String nomeSala, int numeroFile, 
				int postiPerFila) { 
		this.idSala = idSala;
		this.nomeSala = nomeSala;
		this.numeroFile = numeroFile;
		this.postiPerFila = postiPerFila;
		this.postiTotali = numeroFile * postiPerFila;
		this.posti = new boolean[numeroFile][postiPerFila];
		this.postiLiberi = postiTotali;
		for(int i = 0; i < numeroFile; i++) {
			for(int j = 0; j < postiPerFila ; j++) {
				posti[i][j] = false;
			}
		}
	}
		
	/**
	 * Get id della sala.
	 * 
	 * @return id dela sala
	 */
	public int getIdSala() {
		return idSala;
	}

	/**
	 * Set id della sala.
	 * 
	 * @param idSala id della sala
	 */
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	/**
	 * Get nome della sala.
	 * 
	 * @return nome della sala
	 */
	public String getNomeSala() {
		return nomeSala;
	}

	/**
	 * Set nome della sala.
	 * 
	 * @param nomeSala nome della sala
	 */
	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	/**
	 * Get numero file totali nella sala.
	 * 
	 * @return numero delle file totali della sala
	 */
	public int getNumeroFile() {
		return numeroFile;
	}

	/**
	 * Set numero file totali nella sala.
	 * 
	 * @param numeroFile numero file totali della sala
	 */
	public void setNumeroFile(int numeroFile) {
		this.numeroFile = numeroFile;
	}

	/**
	 * Get posti per ogni fila della sala.
	 * 
	 * @return posti per ogni fila della sala
	 */
	public int getPostiPerFila() {
		return postiPerFila;
	}

	/**
	 * Set posti per ogni fila della sala.
	 * 
	 * @param postiPerFila posti per ogni fila della sala
	 */
	public void setPostiPerFila(int postiPerFila) {
		this.postiPerFila = postiPerFila;
	}

	/**
	 * Get posti liberi e prenotati.
	 * 
	 * @return tutti i posti della sala
	 */
	public boolean[][] getPosti() {
		return posti;
	}

	/**
	 * Set posti liberi e prenotati.
	 * 
	 * @param posti tutti i posti della sala
	 */
	public void setPosti(boolean[][] posti) {
		this.posti = posti;
	}

	/**
	 * Get numero dei posti totali della sala.
	 * 
	 * @return numero dei posti totali
	 */
	public int getPostiTotali() {
		return postiTotali;
	}

	/**
	 * Set numero dei posti totali della sala.
	 * 
	 * @param postiTotali numero dei posti totali
	 */
	public void setPostiTotali(int postiTotali) {
		this.postiTotali = postiTotali;
	}

	/**
	 * Get numero dei posti liberi disponibili.
	 * 
	 * @return numero posti liberi disponibili
	 */
	public int getPostiLiberi() {
		return postiLiberi;
	}

	/**
	 * Set numero dei posti liberi disponibili.
	 * 
	 * @param postiLiberi numero dei posti liberi disponibili
	 */
	public void setPostiLiberi(int postiLiberi) {
		this.postiLiberi = postiLiberi;
	}

	/**
	 * Indica se un posto è prenotato.
	 * 
	 * @param fila numero della fila
	 * @param colonna numero della colonna
	 * @return true se prenotato false altrimenti
	 */
	public boolean isPrenotato(int fila, int colonna) {
		return posti[fila][colonna];
	}

	/**
	 * Prenota i posti richiesti se disponibili
	 * 
	 * @param fila file corrispondenti ai posti da prenotare una per ogni posto
	 * @param colonna numero posto da prenotare associato ad ogni fila
	 * @return true se posti prenotati correttamente, false altrimenti
	 */
	public boolean prenotaPosti(int[] fila, int[] colonna) {
		if(fila.length != colonna.length) {
			System.err.println("Errore nell'inserimento dei dati");
			return false; //errore nella formattazione dei dati
		}else if (this.postiLiberi < fila.length) {
			System.err.println("non ci sono posti suffucienti");
			return false; //non ci sono posti suffucienti
		}else{
			for(int i=0; i < fila.length; i++){
				if(isPrenotato(fila[i],colonna[i]) == false) {
					this.posti[fila[i]][colonna[i]] = true;
					this.postiLiberi--; //prenoto il posto
				}else{
					System.err.println("il posto fila: " + fila[i] + " numero "
										+ colonna[i] + " risulta già prenotato.");
					return false; //posto già prenotato
				}
			}
			return true; //tutti i posti prenotati correttamente
		}
	}
	
	/**
	 * Cancello la prenotazione dei posti indicati.
	 * 
	 * @param fila file corrispondenti ai posti da liberare una per ogni posto
	 * @param colonna numero posto da liberare associato ad ogni fila
	 * @return true se posti liberati correttamente, false altrimenti
	 */
	public boolean liberaPosti(int[] fila, int[] colonna) {
		if(fila.length != colonna.length) {
			System.err.println("Errore nell'inserimento dei dati");
			return false; //errore nella formattazione dei dati
		}else{
			for(int i=0; i < fila.length; i++){
				//libero i posti che ho prenotato in precedenza
				this.posti[fila[i]][colonna[i]] = false;
				this.postiLiberi++; //libero il posto				
			}
			return true; //tutti i posti liberati correttamente
		}
	}

}
