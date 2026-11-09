/**
 * @author: VadimEvelson
 */

/**
 * Die Klasse repräsentiert das Objekt Spezialisierung und
 * entspricht der Tabelle Spezialisierung in der Datenbank.
 */

package bbq.objekte;

import bbq.BBQObjekt;

public class Spezialisierung extends BBQObjekt {

    public int fach_id;    // FK = PK aus Fach

    /**
     * Beschreibung der Spezialisierung
     */

    String bezeichnung;

    public Spezialisierung(int id, int fach_id, String bezeichnung) {
        super(id);
        this.fach_id = fach_id;
        this.bezeichnung = bezeichnung;
    }

    @Override 
    public String toString(){

        return "Instanz von Spezialisierung\n" + bezeichnung + " mit id " + id;

    }

}
