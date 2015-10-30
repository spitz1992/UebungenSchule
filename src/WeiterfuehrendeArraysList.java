import java.util.ArrayList;

import javax.swing.JOptionPane;

public class WeiterfuehrendeArraysList {

	public static void main(String[] args) {
		
		ArrayList<String> schnittstellen = new ArrayList<String>();
			schnittstellen.add("USB 1.0");
			schnittstellen.add("USB 2.0");
			schnittstellen.add("USB 3.0");
			schnittstellen.add("SATA I");
			schnittstellen.add("SATA II");
			schnittstellen.add("SATA III");
			schnittstellen.add("eSATA");
			
		
		ArrayList<String> datenraten = new ArrayList<String>();
			datenraten.add("12 MBit/s");
			datenraten.add("480 MBit/s");
			datenraten.add("4000 MBit/s");
			datenraten.add("1200 MBit/s");
			datenraten.add("2400 MBit/s");
			datenraten.add("4800 MBit/s");
			datenraten.add("2400 MBit/s");
		
		String sMenue = JOptionPane.showInputDialog("Wollen Sie: \n"
													+ " (A) eine Bandbreite abfragen \n"
													+ " (L) eine Liste der verfügbaren Schnittstellen anzeigen \n"
													+ " (N) eine neue Schnittstelle hinzufügen \n"
													+ " (E) eine Schnittstelle aus der Liste entfernen \n"
													+ " (B) das Programm beenden");
		
		switch(sMenue.toUpperCase()) {
			case "A":
				abfrage (schnittstellen, datenraten);
				break;
			case "L":
				liste (schnittstellen, datenraten);
				break;
			case "N":
				hinzufuegen (schnittstellen, datenraten);
				break;
			case "E":
				entfernen (schnittstellen, datenraten);
				break;
			case "B":
				System.exit(0);
				break;
		}
		
	}
	//Datenraten abfragen
	public static void abfrage (ArrayList<String> schnittstellen, ArrayList<String>datenraten) {
		String sSchnittstelle = "";
		String sUserEingabe = JOptionPane.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
		for (int iZahl = 0; iZahl < schnittstellen.size(); iZahl ++) {
			if (sUserEingabe.equals(schnittstellen.get(iZahl))) {
				//JOptionPane.showMessageDialog(null, datenraten.get(iZahl));
				sSchnittstelle = datenraten.get(iZahl);
			} else {
				//JOptionPane.showMessageDialog(null, "Diese Schnittstelle ist dem Program nicht bekannt.");
			}	
		}
		JOptionPane.showMessageDialog(null, sSchnittstelle);
		
		
	}
	// Liste aller Schnittstellen anzeigen
	public static void liste (ArrayList<String> schnittstellen, ArrayList<String>datenraten) {
		
		String a = "";
		for (int iZahl = 0; iZahl < schnittstellen.size(); iZahl ++) {
			a = a + schnittstellen.get(iZahl) + " = " + datenraten.get(iZahl) + "\n";
		}
		JOptionPane.showMessageDialog(null, a);
	}
	// Neue Schnittstelle hinzufuegen
	public static void hinzufuegen (ArrayList<String> schnittstellen, ArrayList<String>datenraten) {
		
		String addSchnittstelle = JOptionPane.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
		String addDatenrate = "";
		//Prüfe ob Schnittstelle bereits vorhanden
		for (int iZahl = 0; iZahl < schnittstellen.size(); iZahl ++) {
			if (addSchnittstelle.equals(schnittstellen.get(iZahl))) {
				JOptionPane.showMessageDialog(null, "Diese Schnittstelle existeirt bereits");
			} else {
				addDatenrate = JOptionPane.showInputDialog("Geben Sie bitte die Bandbreite in MBit/s ein:");
			}
		}
		schnittstellen.add(addSchnittstelle);
		datenraten.add(addDatenrate);
	}
	
	public static void entfernen (ArrayList<String> schnittstellen, ArrayList<String>datenraten) {
		String sUserEingabe = JOptionPane.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
		String sAbfrage = "";
		int iZahl = 0;
		for (iZahl = 0; iZahl < schnittstellen.size(); iZahl ++) {
			if (!sUserEingabe.equals(schnittstellen.get(iZahl))) {
				JOptionPane.showMessageDialog(null,"Diese Schnittstelle existiert nicht.");
			} else { 
				sAbfrage = JOptionPane.showInputDialog("Soll Schnittstelle" + sUserEingabe + " tatsächlich gelöscht werden (J/N)?");
			}
		}
		if (sAbfrage == "J") {
				schnittstellen.remove(iZahl);
				datenraten.remove(iZahl);
			} else {
				System.exit(0);
			}
					
	}
}
