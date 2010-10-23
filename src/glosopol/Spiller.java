package glosopol;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/** Klasse for spillern.
*	Holder orden på spillern og hva som skjer med spillern.
* 
* @author      Mads Petter Røine
*/


public class Spiller {
	private String navn;
	private int formue;
	private ArrayList<Aksje> Aksjer;
	private int posisjon;
	private boolean iFengsel;
	private boolean konkurs;
	private int totalsum;
	private int betaleFengsel;
	private Color color;
	private static final int startformue = 20000;
	private Terning terning;

	/** 
	 * Kontstruktør som setter opp verdiene som trengs til ny spiller
	 *  
	 * @param navn navnet på spilleren
	 */

	
	
	public Spiller(String navn) {
		this.navn = navn;
		this.Aksjer = new ArrayList<Aksje>();
		this.konkurs = false;
		this.iFengsel = false;
		this.posisjon = 0;
		this.formue = startformue;
		this.color = getColor();
	}
	/** 
	 * Metode som endrer forumen til spillern.
	 * 
	 * 
	 * @param navn navnet på spilleren
	 * @return ny formue
	 */

	public int endrePenger(int verdi) {
		if (verdi > 0) {
			this.formue += verdi;
			return verdi;
		} else if (verdi < 0) {
			if ((this.formue + verdi) >= 0) {
				this.formue += verdi;
				return verdi;
			} else {
				return this.formue;
			}
		} else {
			return 0;
		}
	}

	/** 
	 * Metode som blir kalt hvis spillern vil kjøpe seg ut av fengsel.
	 * Hvis han ikke har penger til å kjøpe seg ut så kommer det melding om det.
	 * 
	 */
	public void kjopFri() {
		if (getBetaleFengsel() > this.getFormue()) {
			System.out.println("Du har ikke nok penger til å kjøpe deg fri");
		} else {
			endrePenger(-getBetaleFengsel());
			setFengslet(false);
		}
	}

	/**
	 * Metode for å kjøpe aksje.
	 * Hvis han ikke har nok penger vil det komme melding om det.
	 * 
	 */
	
	public void kjopAksje(Aksje aksje) {
		int aksjeprisen = aksje.getPris();
//		if (aksje.getSolgt()) 
//		{ System.out.println("Aksjen er allerede solgt til " +"aksje.getEier().getNavn()"); }
		if (!Aksjer.contains(aksje) && formue >= aksjeprisen) {
			Aksjer.add(aksje);
			aksje.setEier(this);
			endrePenger(-aksjeprisen);
		}
		else if (formue < aksjeprisen) { JOptionPane.showMessageDialog(null, "Du har ikke penger nok!");
		}
	}
	
	/**
	 * Metode som sjekker om han har penger nok.
	 * 
	 * 
	 */
	
	public boolean pengernok (Spiller spiller, Aksje aksje) {
		if (spiller.formue >= aksje.getPris()) {
			return true; 
		}
		return false;
	}
	/**
	 * Betaler leie på aksje hvis den er kjøpt og ikke pantsatt.
	 * 
	 */

	public void betaleLeie(Aksje aksje) {
		int leie = aksje.getLeie();
		if (!aksje.getPantsattstatus()) {
			endrePenger(-leie);
		}
		aksje.getEier().endrePenger(leie);
	}
	
	/**
	 * Metode som flytter på spillern.
	 * 
	 * 
	 */
	
	public void flytt() {
		posisjon = posisjon + terning.getVerdi1() + terning.getVerdi2();
	}
	
	/**
	 * Get og set metoder
	 * 
	 * 
	 */

	public boolean getIFengsel() {
		return iFengsel;
	}
	public boolean setFengslet(boolean u) {
		return iFengsel = u;
	}

	public String getNavn() {
		return this.navn;
	}

	public int getFormue() {
		return this.formue;
	}

	public int getBetaleFengsel() {
		return this.betaleFengsel;
	}

	public void setBetaleFengsel(int betaleFengsel) {
		this.betaleFengsel = betaleFengsel;
	}

	public int getPosisjon() {
		return posisjon;
	}

	public void setPosisjon(int posisjon) {
		this.posisjon = posisjon;
	}

	public int getTotalsum() {
		return this.totalsum;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean getKonkurs() {
		return konkurs;
	}
	public Spiller getAktivSpiller() {
		return this;
	}
}