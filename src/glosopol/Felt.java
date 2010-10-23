/**
 * En abstrakt klasse som er superklassen til Aksje og Spesialfelt.
 * Holder oversikt over alle feltene på brettet.
 * 
 * @author Mads Opheim
 * @version 21:11 01.05.08
 * @see Aksje
 * @see Spesialfelt
 */
package glosopol;

import java.util.ArrayList;

public abstract class Felt {
	
	protected static int antallfelt;
	/**
	 * Denne variabelen kalles direkte noen ganger i flere andre klasser.
	 */
	protected static int feltperside;
	protected static ArrayList<Felt> feltliste = new ArrayList<Felt>();
	protected String feltnavn;
	
	/**
	 * Konstruktør som oppretter et nytt felt og legger det til i lista over felt.
	 * @param feltnavn
	 */
	public Felt (String feltnavn) {
		antallfelt +=1;
		this.feltnavn = feltnavn;
		if (antallfelt%4 == 0) { feltperside = antallfelt%4; } 
		feltliste.add(this);
	}
	
	/**
	 * 
	 * @return Antall felt per side.
	 */
	public int getFeltperside () {
		return feltperside;
	}
	/**
	 * 
	 * @return Lista over felt som er lagt til.
	 */
	public ArrayList<Felt> getFeltliste () {
		return feltliste;
	}
	/**
	 * toString. Kjekt å ha.
	 */
	public String toString () {
		return feltnavn;
	}
	/**
	 * 
	 * @return Akkurat det samme som toStringen. Egentlig litt unødvendig.
	 */
	public String getFeltnavn() {
		return feltnavn;
	}
}