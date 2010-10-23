/**
 * Klasse for å simulere det som skjer på brettet.
 * 
 * @author Mads Opheim
 * @version 20:04 01.05.08
 * @see Brikke
 * @see Vindu
 */package glosopol;

 import java.awt.*;
 import java.util.ArrayList;

 import javax.swing.JOptionPane;
 import javax.swing.JPanel;
 import javax.swing.JTextArea;

 @SuppressWarnings("serial")
 public class Brett extends JPanel {
	 // Instansierer "faste" variabler
	 private final int vindusbredde;
	 private final int vindushoyde;
	 private final Image bakgrunnsbildet;
	 private ArrayList<Brikke> brikker = new ArrayList<Brikke>();
	 private Felt aktivtfelt;
	 private JTextArea meldinger = new JTextArea("");

	 /**
	  * Standard konstruktør som lager et JPanel, setter bredden og høyden på det
	  * og setter et bakgrunnsbilde
	  */
	 public Brett() {
		 super();
		 vindusbredde = 1024;
		 vindushoyde = 768;
		 bakgrunnsbildet = Toolkit.getDefaultToolkit().getImage(
				 getClass().getResource("monopol.jpg")); // bakgrunnsbildet
		 repaint();
	 }

	 /** 
	  * Henter ut den nyeste meldingen en spiller fikk i meldingsboksen på spillebrettet.
	  * F.eks. om en spiller har havnet på et prøv lykken-felt, vil det vises her.
	  * @return Den siste meldingen som ble gitt.
	  */
	 public JTextArea getMeldinger() {
		 return meldinger;
	 }

	 /**
	  * Henter ut lista over brikker som er med i spillet
	  * @return Brikkene som er med i spillet.
	  */
	 public ArrayList<Brikke> getBrikker() {
		 return brikker;
	 }

	 /** 
	  * Setter hvilket felt en spiller landet på.
	  * @param felt
	  */
	 public void setAktivtFelt(Felt felt) {
		 aktivtfelt = felt;
	 }

	 /**
	  * Henter ut hvilket felt en spiller landet på.
	  * @return Hvilket felt spilleren som sist kastet står på.
	  */
	 public Felt getAktivtFelt() {
		 return aktivtfelt;
	 }

	 /**
	  * Metode som sjekker om det aktive feltet er en aksje, og som i så fall henter ut aksjen.
	  * @return Det aktive feltet hvis det er et aksjefelt, ellers null.
	  */
	 public Aksje getAktivAksje() {
		 if (aktivtfelt instanceof Aksje) {
			 Aksje aktivaksje = (Aksje) aktivtfelt;
			 return aktivaksje;
		 }
		 return null;
	 }
	 /**
	  * Setter en gitt tekst i meldingsboksen spilleren har i nedre høyre hjørne.
	  * Brukes bl.a. til prøv lykken-feltet og ved betaling av semesteravgift.
	  * @param message
	  */
	 public void setMessage (String message) {
		 meldinger.setText(message);
	 }
	 /**
	  * Metode som fjerner en brikke fra spillet.
	  * @param brikke
	  */
	 public void removeBrikke(Brikke brikke) {
		 brikker.remove(brikke);
	 }

	 /**
	  * Metode som fjerner alle brikkene fra spillet. Kalles ved trykk på "gi opp"-knappen.
	  */
	 public void removeBrett() {
		 brikker = null;
	 }
	 /**
	  * Metode som kalles ved trykk på kast som setter brikken i stand til å flytte seg igjen.
	  */
	 public void settFlyttetnokNei() {
		 for (int i = 0; i < brikker.size(); i++) {
			 brikker.get(i).setFlyttetnok(false);
		 }
	 }

	 /**
	  * Legger til en brikke på brettet.
	  * Egentlig en hjelpemetode til leggtilSpiller.
	  * @param brikke
	  */
	 public void addBrikke(Brikke brikke) {
		 if (brikke == null) {
			 setMessage("Det er ingen brikke du prøver å legge til");
		 } else {
			 brikker.add(brikke);
		 }
	 }

	 /**
	  * Tegner bakgrunnsbildet og spillernes brikker.
	  */
	 @Override
	 public void paint(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g;
		 g2d.setColor(Color.LIGHT_GRAY);
		 g2d.fillRect(0, 0, (vindusbredde - 150), vindushoyde); // farger hele
		 // vinduet i
		 // bakgrunnsfargen
		 try {
			 g2d.drawImage(bakgrunnsbildet, 0, 0, null);
		 }
		 catch (NullPointerException npe) {
			 npe.printStackTrace();
		 } // hvis bildet ikke fins
		 if (brikker != null) {
			 for (int i = 0; i < brikker.size(); i++) {
				 Brikke brikken = brikker.get(i);
				 g2d.setColor(brikken.getBrikkefarge());
				 g2d.fillOval(brikken.getX(), brikken.getY(), 
						 brikken.getBrikkestr(), brikken.getBrikkestr());
			 }
		 }
	 }

	 /**
	  * Legger til en ny spiller. Gir spilleren mulighet til å velge farge på brikken sin 
	  * blant en rekke gitte farger, sjekker om spilleren har skrevet inn navn og legger
	  * i så fall til spilleren og gir henne en brikke.
	  */
	 public void leggtilSpiller() {
		 String navn = JOptionPane.showInputDialog("Skriv inn navnet ditt");
		 String fargenavn = JOptionPane.showInputDialog
		 ("Velg farge - rød, grønn, gul, magenta, oransje, cyan, hvit, svart, grå eller rosa. " +
		 "Velger du ingen av dem blir brikken blå.").toLowerCase();
		 Color cc = Color.BLUE;
		 if (fargenavn.equals("rød")) {
			 cc = Color.RED;
		 } else if (fargenavn.equals("grønn")) {
			 cc = Color.GREEN;
		 } else if (fargenavn.equals("gul")) {
			 cc = Color.YELLOW;
		 } else if (fargenavn.equals("magenta")) {
			 cc = Color.MAGENTA;
		 } else if (fargenavn.equals("oransje")) {
			 cc = Color.ORANGE;
		 } else if (fargenavn.equals("cyan")) {
			 cc = Color.CYAN;
		 } else if (fargenavn.equals("hvit")) {
			 cc = Color.WHITE;
		 } else if (fargenavn.equals("svart")) {
			 cc = Color.BLACK;
		 } else if (fargenavn.equals("grå")) {
			 cc = Color.GRAY;
		 } else if (fargenavn.equals("rosa")) {
			 cc = Color.PINK;
		 }

		 if (navn != null && !navn.equals("")) {
			 Brikke brikke1 = new Brikke(cc, navn);
			 this.addBrikke(brikke1);
			 repaint();
		 } else {
			 JOptionPane.showMessageDialog(null, "Skriv inn navnet ditt!");
			 return;
		 }

	 }

	 /**
	  * Metoden som flytter en spiller. Kaller på flytt-metoden i Brikke, og gjentar denne
	  * til spilleren har flyttet like mange felt som terningen angir.
	  * Deretter sjekkes hvilket felt spilleren nå havnet på, og hvilke "reaksjoner"
	  * dette skal få.
	  * @param brikke
	  */
	 public void flytting(Brikke brikke) {
		 Spiller spilleren = null;
		 if (brikker != null && brikker.size() > 0) {
			 brikke.kastTerning();
			 spilleren = brikke.getSpiller();
			 while (!brikke.getFlyttetnok()) {
				 brikke.flytting();
			 } // end while
			 spilleren.setPosisjon( (spilleren.getPosisjon() + brikke.getTerningkastet()) % Felt.antallfelt);
		 }

		 int pos = spilleren.getPosisjon();

		 if (Felt.feltliste != null && Felt.feltliste.size() != 0) {
			 aktivtfelt = Felt.feltliste.get(pos);
			 if (aktivtfelt instanceof Aksje) {
			 } else {
				 Spesialfelt spesfelt = (Spesialfelt) aktivtfelt;
				 spesfelt.setSpiller(spilleren);
				 if (pos == 0) {
					 spesfelt.startFelt();
					 setMessage("Du passerer nå start");
				 } else if (spesfelt.getFeltnavn().equals("ProvLykken")) {
					 spesfelt.lagPrøvlykken();
					 spesfelt.prøvLykken();
					 setMessage(spesfelt.getProvLykken());
				 } else if (spesfelt.getFeltnavn().equals("Sjansefelt")) {
					 spesfelt.lagSjanseKort();
					 spesfelt.sjanseKort();
					 setMessage(spesfelt.getSjansemelding());
				 } else if (spesfelt.getFeltnavn().equals("Luxery tax")) {
					 spesfelt.luxTax();
					 setMessage("Betal luksusskatt på " +spesfelt.getLuksusskatt() +" kr.");
				 } else if (spesfelt.getFeltnavn().equals("BetalSemesterAvgift")) {
					 spesfelt.betaleSemesterAvgift();
					 setMessage("Betal semesteravgift på " +spesfelt.getSemesteravgift() +" kr.");
				 } else if (spesfelt.getFeltnavn().equals("Gå til fengsel")) {
					 spesfelt.gåIFengsel();
					 setMessage("Nattforelesning hver dag den neste uka. Synd det");
				 } else if (spesfelt.getFeltnavn().equals("Fengsel")) {
					 setMessage("Kos deg på nattforelesning. Hils fra oss.");
				 } // end else if
			 } // end if spesialfelt
		 } // end if notnull
	 } // end flytting
 }