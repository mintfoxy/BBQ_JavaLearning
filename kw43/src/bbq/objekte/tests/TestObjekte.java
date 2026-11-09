package bbq.objekte.tests;

import bbq.objekte.Azubi;
import bbq.objekte.Gruppe;
import bbq.objekte.Spezialisierung;

public class TestObjekte {

    public static void main(String[] args) {

        // Aus der DB geladen und als Objekte instanziert
        Spezialisierung si = new Spezialisierung(1, "SI");
        Spezialisierung ae = new Spezialisierung(2, "AE");
        Spezialisierung da = new Spezialisierung(3, "DA");

        Gruppe u30 = new Gruppe(1, "U30");
        Gruppe u31 = new Gruppe(2, "U31");
        

        int id = 0;
        
        while (IO.readln("Der Nächste").equals("ja")){

        String name = IO.readln("Ihr Name?\n");
        String spez = IO.readln("Was wollen Sie werden?\n" + "a = SI, b = AE, c = DA\n");

        Spezialisierung wahl;

        if (spez.equals("a")) {
            wahl = si;
        } else if (spez.equals("b")) {
            wahl = ae;
        } else if (spez.equals("c")) {
            wahl = da;
        } else {
            System.out.println("Falsche Eingabe");
            return;
        }

        // die Instanz Azubi wird erzeugt und in der Datenbank gespeichert
        // eine Instanz = ein Datensatz in der Datenbank
        Azubi azubi = new Azubi(++id, name, wahl);

        u30.aufnehmeInDieGruppe(azubi);

        IO.println("Eingetragen.\n" + azubi.toString());

        }

        IO.print("Ende");

    }

}
