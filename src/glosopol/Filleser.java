/**
 * Leser inn en fil.
 * 
 * @author Mads Opheim
 * @version 21:10 01.05.08
 * @see Filskriver
 * @see Highscore
 */

package glosopol;

import java.io.*;
public class Filleser {
	@SuppressWarnings("unused")
	private String filnavn;
	private BufferedReader br;
	private boolean eof;
	
	/**
	 * Lager en filleser.
	 * @param filnavn
	 * @throws IOException
	 */
	public Filleser (String filnavn) throws IOException {
		this.filnavn = filnavn;
		br = new BufferedReader (new FileReader(filnavn));
	}
	
	/**
	 * Leser inn en tekstlinje fra fila.
	 * @return en tekstlinje fra fila.
	 * @throws IOException
	 */
	public String lestekst () throws IOException {
		String tekstlinje = br.readLine();
		if (tekstlinje == null) { eof = true; br.close();}
		return tekstlinje;
	}
	/**
	 * 
	 * @return om fila er lest ferdig eller ikke.
	 */
	public boolean ferdig() {
		return eof;
	}
	/**
	 * Avslutter lesinga
	 * @throws IOException
	 */
	public void close() throws IOException {
		br.close();
	}
}
