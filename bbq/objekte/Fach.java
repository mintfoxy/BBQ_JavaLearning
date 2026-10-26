package bbq.objekte;

import java.util.ArrayList;

/**
 * Entspricht der Tabelle Fach in unserer Datenbank
 */

public class Fach {

    private int id;
    private String fach_name;
    //  zu einem Fach gehören mehrere Spezialisierungen
    //  mehrere bedeutet []

    private ArrayList<Spezialisierung> spezialisierungen;   //  Liste mit Spezialisierungen eines Faches

    public String addSpezialisierung(Spezialisierung sp){ //  diese Methode fügt das Objekt Spezialisierung zu der Liste

        if (spezialisierungen == null)  //  prüfe, die Liste noch leer ist
            spezialisierungen = new ArrayList<Spezialisierung>();   //  erzeuge eine Liste

        if (spezialisierungen.contains(sp)){   //  prüfe, ob der Eintrag bereits vorhanden ist
            return "Das Objekt ist bereits in der Liste";
        }

        spezialisierungen.add(sp);  //  adde ihn zur Liste

        return null;    //  gibt nichts/null zurück, wenn alles ok war

    }


    public Fach(){  //  Standardkonstruktor ohne Parameter

    }

    public Fach(int id, String bezeichnung){    //  Konstruktor mit Parametern; erzeugt eine Instanz der Klasse Fach
        this.id = id;
        this.fach_name = bezeichnung;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFach_Name(){
        return fach_name;

    }

    public void setFach_Name(String fach_name){
        this.fach_name = fach_name;
    }

    public ArrayList<Spezialisierung> getSpezialisierungen(){
        return spezialisierungen;
    }

    @Override   //  wir überschreiben die Methode toString der Klasse Object, sodass wir die Darstellung an uns anpassen
    public String toString(){

        String str = "Fach " + fach_name + " id " + id;
        return str;
    }

}
