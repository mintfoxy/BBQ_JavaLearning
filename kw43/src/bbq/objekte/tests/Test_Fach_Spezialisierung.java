package bbq.objekte.tests;

import bbq.objekte.Fach;
import bbq.objekte.Spezialisierung;

public class Test_Fach_Spezialisierung {

    public static void main(String[] args) {
        
        //  Eine Instanz der Klasse Fach
        //  entspricht einem Datensatz in der Tabelle Fach

        Fach it = new Fach(12, "IT");    //  Datentyp Variable = neues Objekt der Klasse(Parametern)

        //  Mehrere Instanzen der Klasse Spezialisierung
        //  entsprechen den Datensätzen der Tabelle Spezialisierung
        //  Kardinalität 1:n (ein Fach kann mehrere Spezialisierungen haben)

        Spezialisierung ae = new Spezialisierung(1, it.id, "AE");   //  .id kommt aus BBQObject, das ist Fremdschlüssel
        Spezialisierung si = new Spezialisierung(2, it.id, "SI");   
        Spezialisierung da = new Spezialisierung(3, it.id, "DA");


    }

}
