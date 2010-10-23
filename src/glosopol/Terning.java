/**
 * En digital terning.
 * 
 * @author Magnus Røed
 * 
 */

package glosopol;

import java.util.Random;

public class Terning {

	private int verdi1;
	private int verdi2;

	private Random generator = new Random();

	/**
	 * Konstruktør som lager en terning og kaster den.
	 */
	public Terning() {
		kast();
	}

	/**
	 * Kaster terningen.
	 */
	public void kast() {
		verdi1 = 1 + generator.nextInt(6);
		verdi2 = 1 + generator.nextInt(6);

		if (dobbel(verdi1, verdi2)) {
			kast();
		}
	}

	/**
	 * 
	 * @return Verdien av terning 1.
	 */
	public int getVerdi1() {
		return verdi1;
	}

	/**
	 * 
	 * @return Verdien av terning 2.
	 */
	public int getVerdi2() {
		return verdi2;
	}

	/**
	 * Sjekker om begge terningene ble seks. I så fall får spilleren ekstrakast.
	 * @param verdi1
	 * @param verdi2
	 * @return true hvis terningene ble seks, ellers false
	 */
	public boolean dobbel(int verdi1, int verdi2) {
		if (this.verdi1 == 6 && this.verdi2 == 6) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Kaster hvis begge terningene ble like, ellers ikke. Brukes for å komme ut av fengsel.
	 */
	public void dkast() {
		if (verdi1 == verdi2) {
			kast();
		} else {

		}
	}
}