import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

public class WeiterfuehrendeArrays {

	public static void main(String[] args) {

		Hashtable<String, String> schnittstellen = new Hashtable<String, String>();

		schnittstellen.put("USB 1.0", "12 MBit/s");
		schnittstellen.put("USB 2.0", "480 MBit/s");
		schnittstellen.put("USB 3.0", "4000 MBit/s");
		schnittstellen.put("SATA I", "1200 MBit/s");
		schnittstellen.put("SATA II", "2400 MBit/s");
		schnittstellen.put("SATA III", "4800 MBit/s");
		schnittstellen.put("eSATA", "2400 MBit/s");

		// System.out.println(schnittstellen.get(0));
		// System.out.println(datenraten.get(0));

		String sUserEingabe = JOptionPane.showInputDialog("Wollen Sie:\n "
				+ "(A) eine Bandbreite abfragen \n "
				+ "(L) eine Liste der verfügbaren Schnittstellen anzeigen \n"
				+ "(N) eine neue Schnittstelle hinzufügen \n"
				+ "(E) eine Schnittstelle aus der Liste entfernen \n"
				+ "(B) das Programm beenden ");

		switch (sUserEingabe.toUpperCase()) {
		case "A":
			sUserEingabe = JOptionPane
					.showInputDialog("Geben Sie bitte die Schnittstellenbezeichnung ein: ");
			System.out.println(schnittstellen.get(sUserEingabe));
			break;
		case "L":
			for (Entry entry : schnittstellen.entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}

			/*
			 * Set set = schnittstellen.entrySet(); Iterator it =
			 * set.iterator(); while (it.hasNext()) { Map.Entry entry =
			 * (Map.Entry) it.next(); System.out.println(entry.getKey() + " : "
			 * + entry.getValue());
			 */
			break;
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
			break;
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
			break;
		case "B":
			System.exit(0);
		}
		for (Entry entry : schnittstellen.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
