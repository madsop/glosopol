/**
 * Lager highscoreliste
 * 
 * @author Mads Opheim
 * @version 21:16 01.05.08
 * @see Filleser
 * @see Filskriver
 */
package glosopol;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
public class Highscore {
	private String spillernavn;
	private int spillersum;
	private String filnavn;
	private Filskriver filskriver;
	private Filleser filleser;
	@SuppressWarnings("unchecked")
	private ArrayList scores = new ArrayList();
	
	/**
	 * Konstruktør.
	 * @param spillernavn
	 * @param spillersum
	 */
	public Highscore (String spillernavn, int spillersum) {
		this.spillernavn = spillernavn;
		this.spillersum = spillersum;
	}

	/**
	 * Legger til en ny highscore i scores-lista.
	 * @param hs
	 */
	@SuppressWarnings("unchecked")
	public void leggtilscores (Object hs) {
		scores.add(hs);
	}
	/**
	 * Setter navn på fila det skal leses fra / skrives til.
	 * @param filnavn
	 */
	public void setfilnavn (String filnavn) {
		this.filnavn = filnavn;
	}
	
	/**
	 * 
	 * @return alle highscorene som er lagt til i scores
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getScores () {
		return scores;
	}
	/**
	 * toString. Kjekt.
	 */
	public String toString() {
		return spillersum +" " +spillernavn;
	}

	/**
	 * Tømmer scores-lista.
	 */
	public void renskscores () { scores.clear(); }

	/**
	 * Metode som skriver highscores til fil.
	 * @throws IOException
	 */
	public void skrivtilfil () throws IOException {
		filskriver = new Filskriver(filnavn);
		String tekst;
		for (int i = 0; i < scores.size(); i++) {
			tekst = scores.get(i).toString();
			filskriver.skrivtekst(tekst);
		}
		filskriver.close();
	}

	/**
	 * Sorterer og skriver til fil.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void sorterogskriv () throws IOException {
		filskriver = new Filskriver(filnavn);
		Collections.sort(scores);
		skrivtilfil();
		renskscores();
	}


	/**
	 * Leser inn scores fra en fil.
	 * @param filnavn
	 * @throws IOException
	 */
	// Foreløpig ikke i bruk
	@SuppressWarnings("unchecked")
	public void lesfrafil (String filnavn) throws IOException {
		scores = new ArrayList();
		filleser = new Filleser(filnavn);
		String tekst = filleser.lestekst();
		while (!filleser.ferdig()) {
			leggtilscores(tekst);
			tekst = filleser.lestekst();
		}
	}
}