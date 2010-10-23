package glosopol;

import java.awt.Color;

/** Lager alle feltene som skal være i spillet.
* 
* @author      Mads Petter Røine
*/
public class KonstruerBrettet {

	public KonstruerBrettet () {
	@SuppressWarnings("unused")
	Vindu vindu = new Vindu();
	Spesialfelt startfelt = new Spesialfelt("Startfelt");
	Aksje aksje1 = new Aksje("Aksje1", 1000, 50, Color.WHITE);
	Spesialfelt ProvLykken1 = new Spesialfelt("ProvLykken");
	Aksje aksje2 = new Aksje("Aksje2", 1200, 60, Color.WHITE);
	Spesialfelt BetalSemesterAvgift = new Spesialfelt("BetalSemesterAvgift");
	Aksje aksje3 = new Aksje("Aksje3", 2000, 125, Color.BLACK);
	Aksje aksje4 = new Aksje("Aksje4", 1400, 80, Color.LIGHT_GRAY);
	Spesialfelt Sjansefelt1 = new Spesialfelt("Sjansefelt");
	Aksje aksje5 = new Aksje("Aksje5", 1400, 80, Color.LIGHT_GRAY);
	Aksje aksje6 = new Aksje("Aksje6", 1600, 90, Color.LIGHT_GRAY);
	Spesialfelt Fengsel = new Spesialfelt("Fengsel");
	Aksje aksje7 = new Aksje("Aksje7", 1800, 100, Color.PINK);
	Aksje aksje8 = new Aksje("Aksje8", 1700, 130, Color.CYAN);
	Aksje aksje9 = new Aksje("Aksje9", 1800, 100, Color.PINK);
	Aksje aksje10 = new Aksje("Aksje10", 2000, 110, Color.PINK);
	Aksje aksje11 = new Aksje("Aksje11", 2000, 125, Color.BLACK);
	Aksje aksje12 = new Aksje("Aksje12", 2200, 120, Color.ORANGE);
	Spesialfelt ProvLykken2 = new Spesialfelt("ProvLykken");
	Aksje aksje13 = new Aksje("Aksje13", 2200, 120, Color.ORANGE);
	Aksje aksje14 = new Aksje("Aksje14", 2400, 130, Color.ORANGE);
	Spesialfelt FriParkering = new Spesialfelt("Fri Parkering");
	Aksje aksje15 = new Aksje("Aksje15", 2600, 140, Color.RED);
	Spesialfelt Sjansefelt2 = new Spesialfelt("Sjansefelt");
	Aksje aksje16 = new Aksje("Aksje16", 2600, 140, Color.RED);
	Aksje aksje17 = new Aksje("Aksje17", 2800, 150, Color.RED);
	Aksje aksje18 = new Aksje("Aksje18", 2000, 125, Color.BLACK);
	Aksje aksje19 = new Aksje("Aksje19", 3000, 160, Color.YELLOW);
	Aksje aksje20 = new Aksje("Aksje20", 3000, 160, Color.YELLOW);
	Aksje aksje21 = new Aksje("Aksje21", 1700, 130, Color.CYAN);
	Aksje aksje22 = new Aksje("Aksje22", 3200, 180, Color.YELLOW);
	Spesialfelt GaTilFengsel = new Spesialfelt("Gå til fengsel");
	Aksje aksje23 = new Aksje("Aksje23", 3400, 190, Color.GREEN);
	Aksje aksje24 = new Aksje("Aksje24", 3400, 190, Color.GREEN);
	Spesialfelt ProvLykken3 = new Spesialfelt("ProvLykken");
	Aksje aksje26 = new Aksje("Aksje26", 3600, 200, Color.GREEN);
	Aksje aksje25 = new Aksje("Aksje25", 2000, 125, Color.BLACK);
	Spesialfelt Sjansefelt3 = new Spesialfelt("Sjansefelt");
	Aksje aksje27 = new Aksje("Aksje27", 3800, 210, Color.BLUE);
	Spesialfelt LuxTax = new Spesialfelt("Luxery Tax");
	Aksje aksje28 = new Aksje("Aksje28", 4000, 220, Color.BLUE);
	}
}