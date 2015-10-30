import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class WeiterfuehrendeArrays {

	static Hashtable<String, String> schnittstellen = new Hashtable<String, String>();
	static String sUserEingabe;
	
	public static void main(String[] args) {

		if (!einlesen ()) {
		schnittstellen.put("USB 1.0", "12 MBit/s");
		schnittstellen.put("USB 2.0", "480 MBit/s");
		schnittstellen.put("USB 3.0", "4000 MBit/s");
		schnittstellen.put("SATA I", "1200 MBit/s");
		schnittstellen.put("SATA II", "2400 MBit/s");
		schnittstellen.put("SATA III", "4800 MBit/s");
		schnittstellen.put("eSATA", "2400 MBit/s");
		}

		// System.out.println(schnittstellen.get(0));
		// System.out.println(datenraten.get(0));

		/*
		String sUserEingabe = JOptionPane.showInputDialog("Wollen Sie:\n "
				+ "(A) eine Bandbreite abfragen \n "
				+ "(L) eine Liste der verfügbaren Schnittstellen anzeigen \n"
				+ "(N) eine neue Schnittstelle hinzufügen \n"
				+ "(E) eine Schnittstelle aus der Liste entfernen \n"
				+ "(B) das Programm beenden ");
		 */
		
		abfrage ();
		
		boolean b = true;
		while (b) {
		switch (sUserEingabe.toUpperCase()) {
		
		// Bandbreite abfragen
		case "A":
			
			sUserEingabe = JOptionPane.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
			JOptionPane.showMessageDialog(null, schnittstellen.get(sUserEingabe));
			
			sUserEingabe = JOptionPane.showInputDialog("Wollen Sie eine weitere Abfrage durchführen? (J/N)");
			if (sUserEingabe.equalsIgnoreCase("j"))
				abfrage();
			else
				b = false;
			
			break;
			
		// Liste aller Schnittstellen anzeigen
		case "L":

			String a = "";
			for (Entry entry : schnittstellen.entrySet()) {
				//JOptionPane.showMessageDialog(null, entry.getKey() + " " + entry.getValue()); 
				//System.out.println((entry.getKey() + " " + entry.getValue()));
				a = a+entry.getKey() + " = " + entry.getValue() + "\n";
		
			}
			JOptionPane.showMessageDialog(null, a); 
			sUserEingabe = JOptionPane.showInputDialog("Wollen Sie eine weitere Abfrage durchführen? (J/N)");
			if (sUserEingabe.equalsIgnoreCase("j"))
				abfrage();
			else
				b = false;

			/*
			 * Set set = schnittstellen.entrySet(); Iterator it =
			 * set.iterator(); while (it.hasNext()) { Map.Entry entry =
			 * (Map.Entry) it.next(); System.out.println(entry.getKey() + " : "
			 * + entry.getValue());
			 */
			break;
			
		// Neue Schnittstelle hinzufügen
		case "N":
			String bezeichnung = JOptionPane
					.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
			String speed = null;
			if (schnittstellen.containsKey(bezeichnung)) {
				System.out.println(sUserEingabe);
				JOptionPane.showMessageDialog(null,
						"Diese Schnittstelle existiert bereits");
			} else {
				speed = JOptionPane
						.showInputDialog("Geben Sie bitte die Bandbreite in MBit/s ein: ");
				schnittstellen.put(bezeichnung, speed);
			}
			sUserEingabe = JOptionPane.showInputDialog("Wollen Sie eine weitere Abfrage durchführen? (J/N)");
			if (sUserEingabe.equalsIgnoreCase("j"))
				abfrage();
			else
				b = false;
			break;
			
		// Schnittstelle aus Liste entfernen
		case "E":
			bezeichnung = JOptionPane
					.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
			if (!schnittstellen.containsKey(bezeichnung)) {
				System.out.println(sUserEingabe);
				JOptionPane.showMessageDialog(null,
						"Diese Schnittstelle existiert nicht");
			} else {
				String bestaetigung = JOptionPane
						.showInputDialog("Soll Schnittstelle " + bezeichnung
								+ " tatsächlich gelöscht werden (J/N)");
				if (bestaetigung.equalsIgnoreCase("J"))
					schnittstellen.remove(bezeichnung);
			}
			sUserEingabe = JOptionPane.showInputDialog("Wollen Sie eine weitere Abfrage durchführen? (J/N)");
			if (sUserEingabe.equalsIgnoreCase("j"))
				abfrage();
			else
				b = false;
			break;
		// Programm beenden
		case "B":
			b = false;
			break;
			}
		}
		/*for (Entry entry : schnittstellen.entrySet()) {
		System.out.println(entry.getKey() + " " + entry.getValue());

		}*/

		schreiben();
		//System.exit(0);

	}

	public static void schreiben() {

		BufferedWriter writer = null;
		OutputStreamWriter outputwriter = null;
		FileOutputStream filewriter = null;

		try {
			filewriter = new FileOutputStream("C:/Users/" + System.getenv("USERNAME") + "/Documents/test.txt");
			outputwriter = new OutputStreamWriter(filewriter);
			writer = new BufferedWriter(outputwriter);

			for (Entry entry : schnittstellen.entrySet()) {
				writer.append(entry.getKey() + "=" + entry.getValue());
				writer.newLine();
			}
			//writer.newLine();
			writer.flush();
			writer.close();
			outputwriter.close();
			filewriter.close();
		} catch (Exception e) {
			System.out.println("Fehler " + e);
		}
	}
	
	public static void abfrage () {
		sUserEingabe = JOptionPane.showInputDialog("Wollen Sie:\n "
				+ "(A) eine Bandbreite abfragen \n "
				+ "(L) eine Liste der verfügbaren Schnittstellen anzeigen \n"
				+ "(N) eine neue Schnittstelle hinzufügen \n"
				+ "(E) eine Schnittstelle aus der Liste entfernen \n"
				+ "(B) das Programm beenden ");
	}
	
	public static boolean einlesen () {
		
		//BufferedReader bureader = null;
		
		try {
			File f = new File("C:/Users/" + System.getenv("USERNAME") + "/Documents/test.txt");
			Scanner sc = new Scanner(f);
			
			while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] zeile = line.split("=");
                schnittstellen.put(zeile[0],zeile[1]);
            }
			//Einlesen über BufferedReader --> Habe ich nicht hinbekommen, er hat immer nur den Speichercode (Adresse) ausgegeben.
			/*
			String a = bureader.readLine();
			String [] zeile = a.split("=");
			inputStreamReader.
			schnittstellen.put(zeile[0],zeile[1]);
			
			while (zeile != null)
			{
				System.out.println(zeile);
				zeile = bureader.readLine().split("=");
				schnittstellen.put(zeile[0],zeile[1]);
			}
			
			bureader.close();
			reader.close();
			*/
		} catch (Exception e) {
			System.out.println("Fehler " + e);
			return false;
		} 
		return true;
	}
}
