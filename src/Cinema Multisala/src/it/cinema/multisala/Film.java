package it.cinema.multisala;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Classe <code>Film</code> contiene tutte le informazioni
 * relative ai film.
 * 
 * @version 1.00 08 Gen 2018
 * @author Matteo Borghese
 *
 */
public class Film {

	/** Id del film */
	private int idFilm;
	
	/** Titolo del film */
	private String titolo;
	
	/** Genere del film */
	private String genere;
	
	/** Trama del film */
	private String trama;
	
	/** Voto medio associato al film */
	private double votoMedio;
	
	/** Prezzo del film */
	private double prezzo;
	
	/** Durata del film */
	private LocalTime durata;
	
	/** Data uscita del film */
	private LocalDateTime dataUscita;
	
	/** Data da cui è possibile rimuoverlo 
	 * dalla programmazione */
	private LocalDateTime dataRimozione;
	
	/** Recensioni associate al film */
	private ArrayList<Recensione> recensione;
	
	/**
	 * Costruttore di film.
	 * 
	 * @param idFilm id del film
	 * @param titolo titolo del film
	 * @param genere genere del film
	 * @param trama trama del film
	 * @param votoMedio voto medio del film
	 * @param prezzo prezzo del film
	 * @param durata durata del film
	 * @param dataUscita data uscita film
	 * @param dataRimozione data da cui è possibile rimuoverlo
	 */
	public Film(int idFilm, String titolo, String genere, String trama, double votoMedio, 
			double prezzo, LocalTime durata,LocalDateTime dataUscita, 
			LocalDateTime dataRimozione) {
		super();
		this.setIdFilm(idFilm);;
		this.setTitolo(titolo);;
		this.setGenere(genere);;
		this.setTrama(trama);;
		this.setVotoMedio(votoMedio);;
		this.setPrezzo(prezzo);
		this.setDurata(durata);
		this.setDataUscita(dataUscita);
		this.setDataRimozione(dataRimozione);
		this.recensione = new ArrayList<Recensione>();;
	}

	/**
	 * Get id del film.
	 * 
	 * @return id del film
	 */
	public int getIdFilm() {
		return idFilm;
	}

	/**
	 * Set id del film.
	 * 
	 * @param idFilm id del film
	 */
	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	/**
	 * Get titolo del film.
	 * 
	 * @return titolo del film
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Set titolo del film.
	 * 
	 * @param titolo titolo del film
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Get genere del film.
	 * 
	 * @return genere del film
	 */
	public String getGenere() {
		return genere;
	}

	/**
	 * Set genere del film.
	 * 
	 * @param genere genere del film
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * Get trama del film.
	 * 
	 * @return trama del film
	 */
	public String getTrama() {
		return trama;
	}

	/**
	 * Set trama del film.
	 * 
	 * @param trama trama del film
	 */
	public void setTrama(String trama) {
		this.trama = trama;
	}

	/**
	 * Get voto medio associato al film.
	 * 
	 * @return voto medio associato al film
	 */
	public double getVotoMedio() {
		return votoMedio;
	}

	/**
	 * Set voto medio associato al film.
	 * 
	 * @param votoMedio voto medio associato al film
	 */
	public void setVotoMedio(double votoMedio) {
		this.votoMedio = votoMedio;
	}

	/**
	 * Get prezzo del film.
	 * 
	 * @return prezzo del film
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Set prezzo del film.
	 * 
	 * @param prezzo prezzo del film
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Get durata del film.
	 * 
	 * @return durata del film
	 */
	public LocalTime getDurata() {
		return durata;
	}

	/**
	 * Set durata del film.
	 * 
	 * @param durata durata del film
	 */
	public void setDurata(LocalTime durata) {
		this.durata = durata;
	}

	/**
	 * Get data uscita del film.
	 * 
	 * @return data uscita del film
	 */
	public LocalDateTime getDataUscita() {
		return dataUscita;
	}

	/**
	 * Set data uscita del film.
	 * 
	 * @param dataUscita data uscita del film
	 */
	public void setDataUscita(LocalDateTime dataUscita) {
		this.dataUscita = dataUscita;
	}

	/**
	 * Get data da cui è possibile rimuoverlo 
	 * dalla programmazione.
	 * 
	 * @return data da cui è possibile rimuoverlo 
	 */
	public LocalDateTime getDataRimozione() {
		return dataRimozione;
	}

	/**
	 * Set data da cui è possibile rimuoverlo 
	 * dalla programmazione.
	 * 
	 * @param dataRimozione data da cui è possibile rimuoverlo 
	 */
	public void setDataRimozione(LocalDateTime dataRimozione) {
		this.dataRimozione = dataRimozione;
	}

	/**
	 * Get recensioni associate al film.
	 * 
	 * @return recensioni associate al film
	 */
	public ArrayList<Recensione> getRecensione() {
		return recensione;
	}

	/**
	 * Set recensioni associate al film.
	 * 
	 * @param recensione recensioni associate al film
	 */
	public void setRecensione(ArrayList<Recensione> recensione) {
		this.recensione = recensione;
	}
	
	/**
	 * Restitutisce tutte le informazioni associate al film e 
	 * alle recensioni (sequence diagrams uc5).
	 * 
	 * @return informazioni film e recensioni
	 */
	public String[] ottieniInfoFilm() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		ArrayList<String> info = new ArrayList<String>();
		info.add("titolo:" + titolo);
		info.add("genere:" + genere);
		info.add("trama:" + trama);
		info.add("voto medio:" + String.valueOf(votoMedio));
		info.add("prezzo:" + String.valueOf(prezzo));
		info.add("data uscita:" + dataUscita.format(formatter));
		info.add("data rimozione:" + dataRimozione.format(formatter));
		info.add("durata:" + durata.format(dtf) + "\n");
		for(Recensione r : recensione)//fai test in cui non ci sono recensioni dentro
		{
			info.add("username:" + r.getUsername());
			info.add("commento:" + r.getCommento());
			info.add("voto:" + String.valueOf(r.getVoto()) + "\n");
		}
		String[] arr = new String[info.size()];
		arr = info.toArray(arr);
		return arr;
	}
	
	/**
	 * Aggiunge una recensione al film.
	 * 
	 * @param r recensione da aggiungere
	 */
	public void aggiungiRecensione(Recensione r) {
		if(r.verificaRecensione(r.getVoto(), r.getCommento())) {
			recensione.add(r);
			double votoTotale = 0;
			for(Recensione rece : this.recensione){
				votoTotale = votoTotale + rece.getVoto();
			}
			this.setVotoMedio(votoTotale/this.recensione.size());
		}	
	}
	
	/**
	 * Rimuove una recensione associata al film.
	 * 
	 * @param recensione recensione da rimuovere.
	 * @return true rimozione andata a buon fine, false altrimenti
	 */
	public boolean rimuoviRecensione(Recensione recensione) {
		if(!this.recensione.contains(recensione))
		{
			System.err.println("La recensione selezionata non esiste");
			return false;
		}else{
			int index = this.recensione.indexOf(recensione);
			votoMedio = (votoMedio + recensione.getVoto()) / 
						(this.recensione.size() - 1) ;
			this.recensione.remove(index);
			double votoTotale = 0;
			for(Recensione rece : this.recensione){
				votoTotale = votoTotale + rece.getVoto();
			}
			this.setVotoMedio(votoTotale/this.recensione.size());
			return true;
		}
	}
	
	/**
	 * Stampa a schermo le informazioni assaciate al film.
	 */
	public void printInfoFIlm() {
		String[] info = ottieniInfoFilm();
		for(String s : info) {
			System.out.print(s + " ");
		}
	}
}
