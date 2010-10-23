/**
 * Legger til brikke, styrer dem, og holder styr på samhandlinga brikke-spiller.
 * 
 * @author Mads Opheim
 * @version 20:34 01.05.08
 * @see Brett
 * @see Spiller
 */
 
package glosopol;
import java.awt.*;
@SuppressWarnings("serial")
public class Brikke {
	//Flytting
	
	private Terning terning;
	private boolean flyttetnok = false;
	private int antallflyttet = 0;
	private int terningkastet;
	private Spiller spiller;
	
	private static final int brikkestr = 35; // hvor store brikkene skal vaere
	private static final int startfeltX = 560; // start-feltet, x
	private static final int startfeltY = 622; // start-feltet, y
	private static final int flyttekonstantX = 65;
	private static final int flyttekonstantY = 63;
	private int brikke_dx; // farten brikken flytter seg med, x
	private int brikke_dy; // farten brikken flytter seg med, y
	private int brikke_X; // brikkens posisjon, x
	private int brikke_Y; // brikkens posisjon, y
	// Brikke-konstanter
	private Color brikkefarge;
	private int posisjon = 0;
	private static final int flyttefart = 1; // hvor raskt brikken flytter seg
	private static int feltPerSide = 9; // this.getFeltperside();
	private int modtall = (((feltPerSide) *4));
	private int forskyvning;
	private static int tilfeldigfaktor = 25;
	
	/**
	 * 
	 * @return Returnerer brikkens X-posisjon.
	 */
	public int getX () { return brikke_X; }
	/**
	 * 
	 * @return Returnerer brikkens Y-posisjon.
	 */
	public int getY () { return brikke_Y; }
	/**
	 * 
	 * @return Returnerer hvor mange piksler brikken skal være i både X- og Y-retning.
	 */
	public int getBrikkestr() { return brikkestr; }
	/**
	 * 
	 * @return Brikkens farge.
	 */
	public Color getBrikkefarge() { return brikkefarge; }

	/**
	 * Konstruktøren, som lager en brikke med en gitt farge og et gitt navn.
	 * Dessuten setter den spillerens startposisjon, litt forskjellig for hver spiller.
	 * @param color
	 * @param navn
	 */
	public Brikke (Color color, String navn) {
		brikkefarge = color;
		Spiller nyspiller = new Spiller(navn);
		spiller = nyspiller;
		forskyvning = (int) (Math.random() * tilfeldigfaktor);
		brikke_X = startfeltX + forskyvning;
		brikke_Y = startfeltY - forskyvning;
	}
	
	/**
	 * toString-metode. Kjekt å ha.
	 */
	public String toString() {
		return spiller.getNavn() + " farge: " +brikkefarge + " " +" X: " +brikke_X +" Y: " +brikke_Y;
	}

	/**
	 * Tegner brikken.
	 * @param g
	 */
	public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(brikkefarge);
		g2d.fillOval(brikke_X, brikke_Y, brikkestr, brikkestr); // tegn brikken
	}
	
	/**
	 * 
	 * @return hvilken spiller brikken hører til.
	 */
	public Spiller getSpiller() {
		return spiller;
	}
	
	/**
	 * Sjekker hvilken status flyttetnok-feltet har. Dette avgjør om flytt-metoden skal kalles
	 * igjen eller ikke.
	 * @return om brikken skal stoppe eller ikke.
	 */
	public boolean getFlyttetnok() {
		return flyttetnok;
	}
	/**
	 * Setter flyttetnok-variabelen.
	 * @param nok
	 */
	public void setFlyttetnok (boolean nok) {
		flyttetnok = nok;
	}
	
	/**
	 * Oppretter en terning, og kaster den én gang. Lagrer verdien.
	 */
	public void kastTerning() { 
		terning = new Terning();
		terningkastet = (terning.getVerdi1() + terning.getVerdi2() );
	}
	/**
	 * 
	 * @return Antall øyne på de to terningene til sammen.
	 */
	public int getTerningkastet() {
		return terningkastet;
	}
	/**
	 * @return henter ut terningene som ble kastet
	 */
	public Terning getTerning () {
		return terning;
	}

	
	/**
	 * Flytter brikken ett felt i riktig retning, og sjekker så om den har flyttet nok.
	 */
	public void flytting()	{
		posisjon += 1;
		posisjon = posisjon % modtall;
		if (posisjon < feltPerSide) {
			brikke_dy = 0;
			brikke_dx = -flyttefart;
		}

		else if ((posisjon >= feltPerSide && posisjon < 2*feltPerSide)) {
			brikke_dx = 0;
			brikke_dy = -flyttefart;
		}

		else if ((posisjon >= 2*feltPerSide && posisjon < 3*feltPerSide) || posisjon==4*feltPerSide) {
			brikke_dy = 0;
			brikke_dx = flyttefart;
		}
		else if ((posisjon >= 3*feltPerSide && posisjon < 4*feltPerSide)) {
			brikke_dx = 0;
			brikke_dy = flyttefart;
		}		
		
		brikke_X = brikke_X + (brikke_dx*flyttekonstantX);
		brikke_Y = brikke_Y + (brikke_dy*flyttekonstantY);

		antallflyttet += 1;
		if (antallflyttet == terningkastet) { flyttetnok = true; antallflyttet = 0;}
	}
}