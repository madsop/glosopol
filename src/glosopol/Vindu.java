/**
 * Lager ramma til spillet, og gir knappene mening.
 * 
 * @author Mads Opheim
 * @version 21:42 01.05.08
 * @see Aksje
 * @see Brett
 * @see Brikke
 * @see Spiller
 */

package glosopol;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Vindu extends JFrame implements MouseListener {

	private static final int vindusbredde = 1024;
	private static final int vindushoyde = 768;
	private static final int bildetsbredde = 676;
	private static final int bildetshoyde = 677;
	private boolean spillferdig;
	private Brikke aktivbrikke;
	private static final int bgbredde = 635; /* liten hack for at det skal
											passe med størrelsen == bghoyde */
	// private static final int feltbredde = 50;
	// - brukes til å pirke på plass flyttinga skikkelig

	private static Brett brett;

	private static JButton StartSpill;
	private static JButton addSpiller;
	private static JButton aksjekjop;
	private static JButton pantsett;
	private static JButton kast;
	private static JButton giopp;
	private static JTextField terningkast1;
	private static JTextField terningkast2;
	private JTextField formuen = new JTextField("0");
	private JLabel navnet = new JLabel("");
	private JTextField feltetditt = new JTextField("");

	/**
	 * Denne metoden kjores ved start av spillet, vinduskonstruktøren.
	 * Lager vindus-ramma, knappene og henter inn brettet.
	 */
	public Vindu() {
		// Lager ramma
		super("Gløsopol");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		brett = new Brett();
		JPanel knapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 10));
		knapper.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		knapper.setPreferredSize(new Dimension(vindusbredde - bgbredde - 170,
				vindushoyde - 92));
		JLabel glosopol = new JLabel("GLØSOPOL");
		glosopol.setFont(new Font("Gløsopol", 3, 35));
		knapper.add(glosopol);

		JPanel spillpanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
		spillpanel.setPreferredSize(new Dimension(200, 100));
		spillpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel spillmeny = new JLabel("Spillmeny");
		spillmeny.setFont(new Font("Spillmeny", 3, 20));
		spillpanel.add(spillmeny);

		StartSpill = new JButton("Start spill");
		StartSpill.setPreferredSize(new Dimension(150, 20));
		spillpanel.add(StartSpill);
		StartSpill.addMouseListener(this);

		addSpiller = new JButton("Legg til spiller");
		addSpiller.setPreferredSize(new Dimension(150, 20));
		spillpanel.add(addSpiller);
		addSpiller.addMouseListener(this);

		// Spiller-panel
		JPanel spillerpanel = new JPanel(
				new FlowLayout(FlowLayout.LEFT, 15, 10));
		spillerpanel.setPreferredSize(new Dimension(200, 400));
		spillerpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel spillerlabel = new JLabel("Din tur, ");
		spillerlabel.setFont(new Font("Spillermeny", 3, 20));
		navnet.setFont(new Font("navnet", 3, 20));
		spillerpanel.add(spillerlabel);
		spillerpanel.add(navnet);

		// JTextField spillernavn = new JTextField(getSpillerNavn());
		// spillernavn.setEditable(false);
		// spillerpanel.add(spillernavn);

		aksjekjop = new JButton("Kjøp denne aksjen");
		aksjekjop.setPreferredSize(new Dimension(150, 20));
		spillerpanel.add(aksjekjop);
		aksjekjop.addMouseListener(this);

		pantsett = new JButton("Pantsett en aksje");
		pantsett.setPreferredSize(new Dimension(150, 20));
		spillerpanel.add(pantsett);
		pantsett.addMouseListener(this);

		JTextField beholdning = new JTextField("Du har: ");
		beholdning.setEditable(false);
		beholdning.setPreferredSize(new Dimension(90, 20));
		spillerpanel.add(beholdning);

		formuen.setEditable(false);
		formuen.setPreferredSize(new Dimension(50, 20));
		spillerpanel.add(formuen);

		kast = new JButton("Kast terningene");
		kast.setPreferredSize(new Dimension(150, 20));
		spillerpanel.add(kast);
		kast.addMouseListener(this);

		// JTextField terning = new JTextField (terning.getKast());
		// terning.setEditable(false);

		giopp = new JButton("Gi opp");
		giopp.setPreferredSize(new Dimension(150, 20));
		spillerpanel.add(giopp);
		giopp.addMouseListener(this);

		JLabel terning1 = new JLabel(" Terning én ble ");
		spillerpanel.add(terning1);
		terningkast1 = new JTextField("");
		terningkast1.setEditable(false);
		terningkast1.setPreferredSize(new Dimension(50, 20));
		spillerpanel.add(terningkast1);

		JLabel terning2 = new JLabel("Terning to ble ");
		spillerpanel.add(terning2);
		terningkast2 = new JTextField("");
		terningkast2.setEditable(false);
		terningkast2.setPreferredSize(new Dimension(50, 20));
		spillerpanel.add(terningkast2);

		JLabel aktivfelt = new JLabel("Du er på ");
		spillerpanel.add(aktivfelt);

		feltetditt = new JTextField("");
		feltetditt.setEditable(false);
		feltetditt.setPreferredSize(new Dimension(70, 20));
		spillerpanel.add(feltetditt);

		JLabel sistemelding = new JLabel("Siste melding noen fikk var: ");
		spillerpanel.add(sistemelding);

		JTextArea meldinger = brett.getMeldinger();
		meldinger.setEditable(false);
		meldinger.setPreferredSize(new Dimension(170, 60));
		meldinger.setLineWrap(true);
		spillerpanel.add(meldinger);

		knapper.add(spillpanel);
		knapper.add(spillerpanel);
		brett.setPreferredSize(new Dimension(bildetsbredde, bildetshoyde));
		this.add(brett, BorderLayout.WEST);
		this.add(knapper, BorderLayout.EAST);
		this.pack();

		repaint();
	}

	/**
	 * Avslutter et spill.
	 */
	public void avsluttspill() {
		spillferdig = true;
	}

	/**
	 * Avgjør hva som skjer når spilleren trykker på museknappen. Hvis trykket er på en knapp,
	 * vil spillet reagere etter knappens hensikt. Her oppdateres dessuten de fleste
	 * tekst-feltene i høyre spalte.
	 */
	public void mouseClicked(MouseEvent e) {
		Object klikk = e.getSource();

		if (klikk == StartSpill) {
			@SuppressWarnings("unused")
			Vindu vindu = new Vindu();
			this.dispose();
		}

		else if (klikk == giopp) {
			brett.removeBrett();
			avsluttspill();
			repaint();
			JOptionPane.showMessageDialog(null, "Du har nå gitt opp spillet");
		}

		else if (klikk == addSpiller) {
			if (brett.getBrikker().size() == 0) {
				while (brett.getBrikker().size() == 0) {
					brett.leggtilSpiller();
				}
				aktivbrikke = brett.getBrikker().get(0);
			} else {
				brett.leggtilSpiller();
			}
		}

		else if (klikk == aksjekjop) {
			if (brett.getAktivtFelt() instanceof Aksje) {
				Spiller aktivspiller = aktivbrikke.getSpiller();
				Aksje aktivaksje = brett.getAktivAksje();
				if (aktivspiller.pengernok(aktivspiller, aktivaksje)) {
					aktivspiller.kjopAksje(aktivaksje);
					brett.setMessage("Du kjøpte " + aktivaksje + " for " +aktivaksje.getPris() 
							+ " kroner og har " +aktivspiller.getFormue() +" kroner på konto");
				}
				else { brett.setMessage("Du hadde ikke penger nok til å kjøpe " +aktivaksje); }
			}
		}

		else if (klikk == pantsett) {
			brett.getAktivAksje().pantsett();
		}

		else if (klikk == kast) {
			if (aktivbrikke == null) {
				brett.setMessage("Ingen spiller å flytte");
				return;
			}

			if (spillferdig) {
				for (int i = 0; i < brett.getBrikker().size(); i++) {
					Spiller temp = brett.getBrikker().get(i).getSpiller();
					Highscore highscore = new Highscore(temp.getNavn(), temp.getTotalsum());
					highscore.leggtilscores(highscore);

					String fn = JOptionPane.showInputDialog(null,
							"Velg filnavn å skrive til");
					if (fn != null) {
						highscore.setfilnavn(fn);
						try {
							highscore.sorterogskriv();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						} // end catch
					} // end if notnull
				} // end for-highscoreadding
			} // end if spillferdig

			if (aktivbrikke.getSpiller().getIFengsel()) {
				JOptionPane.showMessageDialog(null, "Du er i fengsel");
				int kjopeut = JOptionPane.showConfirmDialog(null,
				"Vil du kjøpe deg ut?");
				if (kjopeut == -1) {
					aktivbrikke.getSpiller().kjopFri();
				}
				if (kjopeut == 0) {
					nesteaktive();
				}
				if (kjopeut == 1) {
					aktivbrikke.getSpiller().endrePenger(-1000);
					nesteaktive();
				}
			}
			brett.settFlyttetnokNei();
//			JOptionPane.showMessageDialog(null,"Denne boksen er en bug som er tilsynelatende "
//					+ "nødvendig for koden. Ignorer den.");

			if (aktivbrikke.getTerning() != null) {
				String kast1 = Integer.toString(aktivbrikke.getTerning().getVerdi1());
				terningkast1.setText(kast1);
				String kast2 = Integer.toString(aktivbrikke.getTerning().getVerdi2());
				terningkast2.setText(kast2);
			}

			if (aktivbrikke != null) {
				brett.flytting(aktivbrikke);
				if (brett.getAktivtFelt() != null) {
					feltetditt.setText(brett.getAktivtFelt().getFeltnavn());
				}
			}
			repaint();
			nesteaktive();
		}
	}

	/**
	 * Bytter aktiv spiller til den neste i lista.
	 */
	public void nesteaktive() {
		int aktivbrikkeI = 0;
		ArrayList<Brikke> brikkene = brett.getBrikker();
		int brikkenestr = brikkene.size();
		for (int i = 0; i < brikkenestr; i++) {
			if (brikkene.get(i) == aktivbrikke) {
				aktivbrikkeI = i;
			}
		}
		aktivbrikkeI = ((aktivbrikkeI + 1) % brikkenestr);
		aktivbrikke = brikkene.get(aktivbrikkeI);

		if (aktivbrikke.getSpiller().getKonkurs()) {
			brett.removeBrikke(aktivbrikke);
			nesteaktive();
		}

		if (!aktivbrikke.getSpiller().getNavn().equals("")) {

			String namn = aktivbrikke.getSpiller().getNavn();
			navnet.setText(namn);
			String formuen1 = Integer.toString(aktivbrikke.getSpiller().getFormue());
			formuen.setText(formuen1);
		}
	}

	/**
	 * Spillets main-metode. Starter spillet.
	 * @param args
	 */
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		KonstruerBrettet brettet = new KonstruerBrettet();
	}

	/**
	 * Disse må være med siden spillet implementerer MouseListener.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}