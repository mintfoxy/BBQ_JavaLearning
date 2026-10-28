package Aufgabe6.objekte;

public class Klub {


    protected int id;   //  alle Klassen haben das Feld id
    protected String bezeichnung;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Klub(int id) {   //  Konstruktor für id
        this.id = id;
    }
    
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {    //  Konstruktor für bezeichnung
        this.bezeichnung = bezeichnung;
    }
    

    

}
