package glosopol;

import javax.swing.JOptionPane;

/** Klasse for spesialfelt.
 *	Holder orden på hva som skjer når man lander på et spesialfelt.
 * 
 * @author      Mads Petter Røine
 */


public class Spesialfelt extends Felt {
	String[] prøvlykken = new String[10];
	String[] sjansekortene = new String[6];
	private int semesterAvgift = 600;
	private Spiller spiller;
	private static final int lukssuskatt = 700;
	

	
	/** 
	 * Lager konstruktør for spesialfelt.
	 * @param feltnavn.
	 * 
	 */
	
	public Spesialfelt (String feltnavn) {
		super(feltnavn);
	}

	/**
	 * Metode som lager prøvlykken-	tabellen.
	 * 
	 * 
	 */

	public void lagPrøvlykken(){
		prøvlykken[0] = "Betal avgift på 200.";
		prøvlykken[1] = "Kjøp deg lunch. Det koster 500 kroner.";
		prøvlykken[2] = "Gå til Illinois Avenue.";
		prøvlykken[3] = "Gå 3 steg tilbake.";
		prøvlykken[4] = "Du vant på loddtrekning. Motta 500 kr.";
		prøvlykken[5] = "Gå på trappene på Board Walk.";
		prøvlykken[6] = "Gå til St. Charles Place.";
		prøvlykken[7] = "Kjøp nytt kompendie, betal 50.";
		prøvlykken[8] = "Gå til start.";
		prøvlykken[9] = "Gå direkte til nattforelesning.";
	}
	
	/**
	 * Metode som lager sjansekort-tabellen.
	 * 
	 * 
	 */
	public void lagSjanseKort(){
		sjansekortene[0] = "Gå tilbake til Baltic Avenue .";
		sjansekortene[1] = "Gå til betal semesteravgift.";
		sjansekortene[2] = "Betal forsikring på 250 kroner.";
		sjansekortene[3] = "Betal bot på 100 kroner.";
		sjansekortene[4] = "Gå til New York Avenue.";
		sjansekortene[5] = "Du har bursdag, motta 200 kroner fra studassen din.";
	}
	/**
	 * Metode prøvlykken som trekker et tilfeldig prøvlykkenkort.
	 * 
	 * 
	 */

	public void prøvLykken(){
		kort = ( int )( Math.random() * prøvlykken.length - 1 );
		if (kort==0){
			spiller.endrePenger(-200);
		}
		if (kort==1){
			spiller.endrePenger(-500);
		}
		if(kort==2){		
			spiller.setPosisjon(2*feltperside + 4);
		}
		if(kort==3){
			int temp = spiller.getPosisjon();
			spiller.setPosisjon(temp-3);
		}
		if (kort==4){
			spiller.endrePenger(500);
		}
		if (kort==5){
			spiller.setPosisjon(3*feltperside + 9);
		}
		if (kort==6){
			spiller.setPosisjon(feltperside + 1);
		}
		if (kort==7){
			spiller.endrePenger(50);
		}
		if (kort==8){
			spiller.setPosisjon(0);
		}
		if (kort==9){
			spiller.setPosisjon(feltperside);
			spiller.setFengslet(true);
		}
	}
	/**
	 * Metode sjansekort som trekker et tilfeldig sjansekort.
	 * 
	 * 
	 */
	private int kort;

	public void sjanseKort(){
		kort = ( int )( Math.random() * sjansekortene.length -1 );
		if (kort==0){
			spiller.setPosisjon(3);
		if (kort==1){
			spiller.setPosisjon(4);
		}
		if(kort==2){
			spiller.endrePenger(-250);
		}
		if(kort==3){
			spiller.endrePenger(-100);
		}
		if (kort==4){
			spiller.setPosisjon(feltperside+9);
		}
		if (kort==5){
			spiller.endrePenger(200);
			}
		}
	}
	
	/**
	 * Metode som betaler semesteravgift.
	 * Blir kalt om han ender på betal semesteravgift.
	 * 
	 */
	public void betaleSemesterAvgift(){
		if( spiller.getFormue()> semesterAvgift){
			spiller.endrePenger(-semesterAvgift);
			JOptionPane.showMessageDialog(null, "Betalt semesteravgift på " + semesterAvgift);
		}
	}
	/**
	 * Metode som betaler luxury tax.
	 * Blir kalt om han ender på luxury tax.
	 * 
	 */
	public void luxTax(){
		spiller.endrePenger(-lukssuskatt);
	}
	/**
	 * Metode som setter spillern i fengsel.
	 * 
	 */
	
	public void gåIFengsel(){
		spiller.setPosisjon(10);
		spiller.setFengslet(true);
	}
	/**
	 * Metode som gir spillern penger.
	 * Blir kalt når spillern kommer på startfeltet.
	 * 
	 */
	public void startFelt(){
		spiller.endrePenger(2000);
	}
	/**
	 * Get og set metoder
	 * 
	 * 
	 */
	
	public void setSpiller (Spiller spiller) {
		this.spiller = spiller;
	}
	public int getLuksusskatt () {
		return lukssuskatt; 
	}
	public int getSemesteravgift() { 
		return semesterAvgift; 
	}
	public String getSjansemelding () { 
		return sjansekortene[kort]; 
	}
	public String getProvLykken () { 
		return prøvlykken[kort]; 
	}
}