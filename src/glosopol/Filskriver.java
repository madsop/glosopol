/**
 * Skriver til en fil
 * 
 * @author Mads Opheim
 * @version 21:15 01.05.08
 * @see Filleser
 * @see Highscore
 */

package glosopol;

import java.io.*;
public class Filskriver {
	@SuppressWarnings("unused")
	private String filnavn;
	private BufferedWriter bw;
	
	/**
	 * Lager en filskriver til en fil filnavn.
	 * @param filnavn
	 * @throws IOException
	 */
	public Filskriver (String filnavn) throws IOException {
		this.filnavn = filnavn;
		bw = new BufferedWriter (new FileWriter(filnavn));
	}
	
	/**
	 * Skriver en tekstlinje.
	 * @param tekst
	 * @throws IOException
	 */
	public void skrivtekst (String tekst) throws IOException {
		bw.write(tekst);
		bw.newLine();
	}
	/**
	 * Lukker filskriveren.
	 * @throws IOException
	 */
	public void close() throws IOException {
		bw.close();
	}
}
