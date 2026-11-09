package guiconsole;

import java.util.List;

import data.dataHandling.FileDataManager;
import data.objekte.AObjectFactory;
import data.objekte.Aufgabe;

/**
 * Startet das Programm
 */
public class Main {

	public static final String HALLO = "Beginn eines neuen Eintrags";
	private static final Integer NUMMER = 1;
	private static final Integer THEMA = 2;
	private static final Integer TEXT = 3;

	private static final String NUMMER_LABEL = "Nummer der Aufgabe: \n";
	private static final String THEMA_LABEL = "Thema: \n";
	private static final String TEXT_LABEL = "Text: \n";

	private static final String CHOICE = "1 - Eingabe, alles andere - Liste \n";
	private static final String MORE = "Noch? (q - ja, alles andere - nein) \n";

	public Main() {
		start();
	}

	private void start() {
		IO.println(HALLO);

		if (IO.readln(CHOICE).equals("1"))
			ausgabe();
		else
			eingabe();

	}

	private void ausgabe() {
		
		List<Aufgabe> aufgaben = FileDataManager.ladeAlleAufgaben();
		for (Aufgabe aufgabe : aufgaben) {
			IO.println(aufgabe.toString());
		}
		
	}

	private void eingabe() {
		String[] eingabe = new String[4];
		boolean more = true;

		while (more) {

			eingabe[NUMMER] = IO.readln(NUMMER_LABEL);
			eingabe[THEMA] = IO.readln(THEMA_LABEL);
			eingabe[TEXT] = IO.readln(TEXT_LABEL);

			Aufgabe a = AObjectFactory.aufgabeFromData(eingabe);
			IO.print(a.save());

			more = IO.readln(MORE).equals("q");
		}

	}

	public static void main(String[] args) {

		new Main();
	}

}