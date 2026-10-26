package bbq_repeat.objekte;

/**
 * Entspricht der Tabelle Fach in unserer Datenbank
 */

public class Fach {

    private int id;
    private String fach_name;

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

    @Override   //  wir überschreiben die Methode toString der Klasse Object, sodass wir die Darstellung an uns anpassen
    public String toString(){

        String str = "Fach " + fach_name + " id " + id;
        return str;
    }

}
