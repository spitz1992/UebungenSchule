import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class WeiterfuehrendeArraysList {
	
	static String sMenue = "";

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
		
		menue();
		/*String sMenue = JOptionPane.showInputDialog("Wollen Sie: \n"
													+ " (A) eine Bandbreite abfragen \n"
													+ " (L) eine Liste der verfügbaren Schnittstellen anzeigen \n"
													+ " (N) eine neue Schnittstelle hinzufügen \n"
													+ " (E) eine Schnittstelle aus der Liste entfernen \n"
													+ " (B) das Programm beenden");
		*/
		String abfrage= "";
		boolean b = true;
		while (b) {
			
		switch(sMenue.toUpperCase()) {
			case "A":
				abfrage (schnittstellen, datenraten);
				abfrage = JOptionPane.showInputDialog("Wollen Sie fortfahren? (J/N)");
				if (abfrage.equalsIgnoreCase("j"))
					menue();
				else
					b = false;
				break;
			case "L":
				liste (schnittstellen, datenraten);
				abfrage = JOptionPane.showInputDialog("Wollen Sie fortfahren? (J/N)");
				if (abfrage.equalsIgnoreCase("j"))
					menue();
				else
					b = false;
				break;
			case "N":
				hinzufuegen (schnittstellen, datenraten);
				abfrage = JOptionPane.showInputDialog("Wollen Sie fortfahren? (J/N)");
				if (abfrage.equalsIgnoreCase("j"))
					menue();
				else
					b = false;
				break;
			case "E":
				entfernen (schnittstellen, datenraten);
				abfrage = JOptionPane.showInputDialog("Wollen Sie fortfahren? (J/N)");
				if (abfrage.equalsIgnoreCase("j"))
					menue();
				else
					b = false;
				break;
			case "B":
				System.exit(0);
				break;
		}
	}
		schreiben(schnittstellen, datenraten);
	}
	
	//Menue aufruf
	public static void menue () {
		sMenue = JOptionPane.showInputDialog("Wollen Sie: \n"
				+ " (A) eine Bandbreite abfragen \n"
				+ " (L) eine Liste der verfügbaren Schnittstellen anzeigen \n"
				+ " (N) eine neue Schnittstelle hinzufügen \n"
				+ " (E) eine Schnittstelle aus der Liste entfernen \n"
				+ " (B) das Programm beenden");
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
				JOptionPane.showMessageDialog(null, "Diese Schnittstelle existiert bereits");
				System.exit(0);
			} else {
				addDatenrate = JOptionPane.showInputDialog("Geben Sie bitte die Bandbreite in MBit/s ein:");
				break;
			}
		}
			
		schnittstellen.add(addSchnittstelle);
		datenraten.add(addDatenrate);
	}
	
	//Schnittstelle entfernen
	public static void entfernen (ArrayList<String> schnittstellen, ArrayList<String>datenraten) {
		String sUserEingabe = JOptionPane.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
		String sAbfrage = "";
		int iZahl = 0;
		for (iZahl = 0; iZahl < schnittstellen.size(); iZahl ++) {
			if (sUserEingabe.equalsIgnoreCase(schnittstellen.get(iZahl))) {
				sAbfrage = JOptionPane.showInputDialog("Soll Schnittstelle " + sUserEingabe + " tatsächlich gelöscht werden (J/N)?");
				break;
			} else { 
				JOptionPane.showMessageDialog(null,"Diese Schnittstelle existiert nicht.");
				break;
			}
		}
		if (sAbfrage.equals("J")) {
				schnittstellen.remove(iZahl);
				datenraten.remove(iZahl);
			} else {
				System.exit(0);
			}
	}
	
	//Textfile erzeugen
	public static void schreiben (ArrayList<String> schnittstellen, ArrayList<String>datenraten) {
		
		BufferedWriter brWriter = null;
		OutputStreamWriter oswWriter = null;
		FileOutputStream fosWriter = null;
		
		try {
			fosWriter = new FileOutputStream("C:/Users/" + System.getenv("USERNAME") + "/Documents/schnittstellen.txt");
			oswWriter = new OutputStreamWriter(fosWriter);
			brWriter = new BufferedWriter(oswWriter);
			brWriter.flush();
			
			
			fosWriter.close();
			oswWriter.close();
			brWriter.close();
			} 
		catch (IOException e) {
			System.out.println("Achtung Fehler: " + e);
			}
	}
}
