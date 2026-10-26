package bbq.tests;

import bbq.objekte.Fach;
import bbq.objekte.Modul;
import bbq.objekte.Spezialisierung;

public class TestObjekte {

    public static void main(String[] args) {



        Fach fach1; //  Datentyp: Klasse Fach, Name der Variable: fach1
        fach1 = new Fach(); //  neue Instanz
        fach1.setId(1);
        fach1.setFach_Name("IT");

        Spezialisierung spezi1;
        spezi1 = new Spezialisierung();
        spezi1.setId(1);
        spezi1.setBezeichnung("AE");
        spezi1.setFach(fach1);  //  FK in unserer Tabelle in der Datenbank

        //ArrayList<Spezialisierung> liste = fach1.getSpezialisierungen();    //  wir rufen die Liste mit Spezialisierungen
                                                                            // aus der Klasse Fach auf
        //liste.add(spezi1);  //  wir adden die erzeugte Spezialisierungen zu der Liste mit Spezialisierungen
        //so ist richtig, weil die Klasse (Fach) die Kontrolle über eigene Elemente behalten muss:
        
        String msg = fach1.addSpezialisierung(spezi1);   //  die Methode aus der Klasse Fach, addet Spezialisierung zur Liste

        if (msg != null)    //  falls die Methode aus der Klasse Fach etwas außer null zurückgibt, dann wird die Meldung gezeigt
                            //  was genau nicht stimmt
            IO.print(msg);

        IO.println(spezi1);

        Spezialisierung spezi2 = new Spezialisierung(2, "SI", fach1);    //  Konstruktor aus der Klasse Spezialisierung
        Spezialisierung spezi3 = new Spezialisierung(3, "DA", fach1);

        Modul modul1 = new Modul(1, "WISO", spezi1.getId());



        Fach fach2;
        fach2 = new Fach(2, "MEDI");


        
    }

}