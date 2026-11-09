/**
 * @author: VadimEvelson
 */

/**
 * Die Klasse repräsentiert das Objekt Azubi und
 * entspricht der Tabelle Azubi in der Datenbank.
 */

package bbq.objekte;

import bbq.BBQObjekt;

public class Azubi extends BBQObjekt {

    String name;    // Name des Azubis

    Spezialisierung spezialisierung;    // Spezialisierung des Azubis

    public Azubi(int id, String name, Spezialisierung spezialisierung) {
        super(id);  // Aufruf des Konstruktors der Mutterklasse BBQObjekt
        this.name = name;
        this.spezialisierung = spezialisierung;
    }

    @Override 

    public String toString() {
        
        String info = super.toString() + "\n"; // Aufruf der toString() Methode der Mutterklasse BBQObjekt

        info = info + "Info aus der Klasse Azubi\n"; // Beschreibung der Klasse Azubi

        info = info + "Name: " + name + "\n";  // Beschreibung des Namens des Azubis

        info = info + spezialisierung.toString() + "\n"; // Aufruf der toString() Methode der Klasse Spezialisierung

        return info;

    }

}
