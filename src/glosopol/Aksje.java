/**
 * Klasse for å simulere aksjefeltene.
 * 
 * @author Mads Opheim
 * @version 19:50 01.05.08
 * @see Felt
 * @see Spiller
 */

package glosopol;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

public class Aksje extends Felt implements Comparator<Spiller>{
	private Spiller eier;
	private Color aksjefarge;
	private int leie;
	private int pris;
	private int pantsettingsverdi;
	private boolean pantsatt = false;
	private boolean eieralle = false;
	private boolean solgt = false;
	private ArrayList<Spiller> spillere;
	private static ArrayList<Aksje> aksjene = new ArrayList<Aksje>();


	/**
	 * Standard konstruktør smo lager en aksje
	 * 
	 * @param feltnavn
	 * @param pris
	 * @param leie
	 * @param aksjefarge
	 * @see Felt
	 */
	public Aksje (String feltnavn, int pris, int leie, Color aksjefarge) {
		super(feltnavn);
		this.pris = pris;
		this.leie = leie;
		this.aksjefarge = aksjefarge;
		this.pantsettingsverdi = pris/2;
		this.solgt = true;
		aksjene.add(this);
	}

	/**
	 * Metode som henter ut alle aksjene som er lagd
	 * @return Alle aksjene som er lagt til i spillet.
	 */
	public ArrayList<Aksje> getAksjene () {
		return aksjene;
	}
	
	/**
	 * Metode som returnerer en aksjes eier
	 * @return Aksjens eier
	 * @see Spiller
	 */
	public Spiller getEier () {
		return eier;
	}
	/**
	 * Metode som setter en aksjes eier.
	 * @param eier
	 * @see Spiller
	 */
	public void setEier (Spiller eier) {
		this.eier = eier;
	}
	/** 
	 * Metode som returnerer hvor mye det koster å parkere på aksjefeltet.
	 * @return Prisen en motspiller som havner på aksjen må betale til aksjens eier.
	 */
	public int getLeie () {
		return leie;
	}
	/**
	 * Sjekker om aksjen er pantsatt eller ikke.
	 * @return Aksjens pantsettingsstatus.
	 */
	public boolean getPantsattstatus () {
		return pantsatt;
	}
	/**
	 * Henter ut aksjens kjøpskostnad.
	 * @return Hvor mye aksjen koster.
	 */
	public int getPris () {
		return pris;
	}
	/**
	 * Sjekker om en aksje er solgt eller ledig.
	 * @return Hvorvidt aksjen er solgt til en spiller eller ikke.
	 */
	public boolean getSolgt () {
		return solgt;
	}
	/** 
	 * Metode som sjekker om to spillere egentlig er den samme spilleren.
	 * Brukes i alleisammefarge-metoden.
	 * @return Null hvis det er samme spiller, ellers ikke.
	 */
	@Override
	public int compare(Spiller o1, Spiller o2) {
		if (o1.equals(o2)) {
			return 0;
		}
		return 1;
	}

	public void pantsett () {
		this.pantsatt = true;
		if (eier != null) {eier.endrePenger(this.pantsettingsverdi); }
	}

	/**
	 * Sjekker om én spiller eier alle aksjene i en gitt farge.
	 * Dessverre ikke implementert per nå.
	 * @param aksjefarge
	 * @return True hvis en spiller eier alle aksjene i fargen, ellers false.
	 */
	public boolean alleisamme (Color aksjefarge) { 
		spillere = new ArrayList<Spiller>();
		for (int i = 0; i < aksjene.size(); i++) {
			Aksje j = aksjene.get(i);
			if (j.aksjefarge.equals(aksjefarge)) {
				if (j.eier != null) {
					spillere.add(j.eier); 
				}
			}
		}
		if (spillere.size() != 0) {
			for (int i = 0; i < spillere.size()-1; i++) {
				if (compare(spillere.get(i),spillere.get(i+1)) != 0) {
					return false;
				}
			}
			return true; 
		}
		return false;
	}
}