package bbq.tests;

import bbq.objekte.Azubi;
import bbq.objekte.Fach;
import bbq.objekte.Gruppe;
import bbq.objekte.Modul;
import bbq.objekte.Spezialisierung;

/**
 * Schreiben Sie ein Test-Programm, mit dem Instanzen erzeugt werden: <br>
 * • für ein Fach <br>
 * • für dieses Fach zwei Spezialisierungen <br>
 * • für jede dieser Spezialisierung <br>
 * ▪ drei Module <br>
 * ▪ fünf Azubis, die <br>
 * o in einer Gruppe sind.
 */

public class Aufgabe4Dozent {

	public static void main(String[] args) {

		Fach f = createFach(1);

		Spezialisierung sp;
		f.addSpezialisierung(sp = createSpezialisierung(1, "Erste Spezialisierung"));
		f.addSpezialisierung(sp);

		sp.addModul(createModul(1, "Modul 1.1"));
		sp.addModul(createModul(2, "Modul 1.2"));
		sp.addModul(createModul(3, "Modul 1.3"));

		Gruppe gr = new Gruppe(10, "Gruppe 1");
		Azubi az;
		for (int i = 1; i < 5 + 1; i++) {
			gr.addTeilnehmer(az = new Azubi(i, "Name" + i, "Vorname" + i));
			sp.addAzubi(az);
		}

		f.addSpezialisierung(sp = createSpezialisierung(2, "Zweite Spezialisierung"));
		sp.addModul(createModul(1, "Modul 2.1"));
		sp.addModul(createModul(2, "Modul 2.2"));
		sp.addModul(createModul(3, "Modul 2.3"));

		IO.println(f);

		gr = new Gruppe(10, "Gruppe 1");
		for (int i = 10; i < 50 + 1; i++) {
			gr.addTeilnehmer(az = new Azubi(i, "Name" + i, "Vorname" + i));
			sp.addAzubi(az);
		}

	}


// Ein Modul
	private static Modul createModul(int id, String name) {
		return new Modul(id, name);
	}

// Eine Spezialisierung
	private static Spezialisierung createSpezialisierung(int id, String name) {
		return new Spezialisierung(id, name);
	}

//	Ein Fach
	private static Fach createFach(int id) {
		return new Fach(id, "FACH");
	}

}