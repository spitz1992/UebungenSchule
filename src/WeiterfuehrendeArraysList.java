import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class WeiterfuehrendeArraysList {
	
	static String sMenue = "";
	static ArrayList<String> schnittstellen = new ArrayList<String>();
	static ArrayList<String> datenraten = new ArrayList<String>();
	public static void main(String[] args) {
		if (!einlesen ()) {
			
				schnittstellen.add("USB 1.0");
				schnittstellen.add("USB 2.0");
				schnittstellen.add("USB 3.0");
				schnittstellen.add("SATA I");
				schnittstellen.add("SATA II");
				schnittstellen.add("SATA III");
				schnittstellen.add("eSATA");

				datenraten.add("12 MBit/s");
				datenraten.add("480 MBit/s");
				datenraten.add("4000 MBit/s");
				datenraten.add("1200 MBit/s");
				datenraten.add("2400 MBit/s");
				datenraten.add("4800 MBit/s");
				datenraten.add("2400 MBit/s");
			}
		
		
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
				abfrage ();
				abfrage = JOptionPane.showInputDialog("Wollen Sie fortfahren? (J/N)");
				if (abfrage.equalsIgnoreCase("j"))
					menue();
				else
					b = false;
				break;
			case "L":
				liste ();
				abfrage = JOptionPane.showInputDialog("Wollen Sie fortfahren? (J/N)");
				if (abfrage.equalsIgnoreCase("j"))
					menue();
				else
					b = false;
				break;
			case "N":
				hinzufuegen ();
				abfrage = JOptionPane.showInputDialog("Wollen Sie fortfahren? (J/N)");
				if (abfrage.equalsIgnoreCase("j"))
					menue();
				else
					b = false;
				break;
			case "E":
				entfernen ();
				abfrage = JOptionPane.showInputDialog("Wollen Sie fortfahren? (J/N)");
				if (abfrage.equalsIgnoreCase("j"))
					menue();
				else
					b = false;
				break;
			case "B":
				schreiben();
				System.exit(0);
				break;
		}
	}
		schreiben();
		
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
	public static void abfrage () {
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
	public static void liste () {
		
		String a = "";
		for (int iZahl = 0; iZahl < schnittstellen.size(); iZahl ++) {
			a = a + schnittstellen.get(iZahl) + " = " + datenraten.get(iZahl) + "\n";
		}
		JOptionPane.showMessageDialog(null, a);
	}
	
	// Neue Schnittstelle hinzufuegen
	public static void hinzufuegen () {
		
		String addSchnittstelle = JOptionPane.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
		String addDatenrate = "";
		
		//Prüfe ob Schnittstelle bereits vorhanden
		boolean b = false;
		for (int iZahl = 0; iZahl < schnittstellen.size(); iZahl ++) {
			if (addSchnittstelle.equals(schnittstellen.get(iZahl))) {
				b = true;
				break;
				//JOptionPane.showMessageDialog(null, "Diese Schnittstelle existiert bereits");
			}
		} 
		if (b) {
			JOptionPane.showMessageDialog(null, "Diese Schnittstelle existiert bereits");
		} else {
			addDatenrate = JOptionPane.showInputDialog("Geben Sie bitte die Bandbreite in MBit/s ein:");
			schnittstellen.add(addSchnittstelle);
			datenraten.add(addDatenrate);
		}
	
	}
	
	//Schnittstelle entfernen
	public static void entfernen () {
		String sUserEingabe = JOptionPane.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
		String sAbfrage = "";
		int iZahl;
		boolean b = false;
		for (iZahl = 0; iZahl < schnittstellen.size(); iZahl++) {
			if (sUserEingabe.equalsIgnoreCase(schnittstellen.get(iZahl))) {
				b = true;
				break;
			}
		}
		if (b) {
			sAbfrage = JOptionPane.showInputDialog("Soll Schnittstelle " + sUserEingabe + " tatsächlich gelöscht werden (J/N)?");
			if (sAbfrage.equalsIgnoreCase("J")) {
				schnittstellen.remove(iZahl);
				datenraten.remove(iZahl);
			}
		} else {
			JOptionPane.showMessageDialog(null,"Diese Schnittstelle existiert nicht.");
		}
	}
	
	//Textfile erzeugen
	public static void schreiben () {
		
		BufferedWriter brWriter = null;
		OutputStreamWriter oswWriter = null;
		FileOutputStream fosWriter = null;
		
		try {
			fosWriter = new FileOutputStream("C:/Users/" + System.getenv("USERNAME") + "/Documents/schnittstellen.txt");
			oswWriter = new OutputStreamWriter(fosWriter);
			brWriter = new BufferedWriter(oswWriter);
			
			for (int iZahl = 0; iZahl < schnittstellen.size(); iZahl++) {
				brWriter.append(schnittstellen.get(iZahl) + "=" +  datenraten.get(iZahl));
				brWriter.newLine();
			}
			
			brWriter.flush();
			
			fosWriter.close();
			oswWriter.close();
			brWriter.close();
			} 
		catch (IOException e) {
			System.out.println("Achtung Fehler: " + e);
			}
	}
	
	//Textfile einlesen
	public static boolean einlesen () {
		File frMyReader = new File("C:/Users/" + System.getenv("USERNAME") + "/Documents/schnittstellen.txt");;
		Scanner sc = null;
		
		try{
			sc = new Scanner (frMyReader);
			
			while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] zeile = line.split("=");
                schnittstellen.add(zeile[0]);
                datenraten.add (zeile[1]);
                
            } 
		} catch (Exception a) {
			System.out.println(a);
			return false;
		}
			return true;
	}
}
