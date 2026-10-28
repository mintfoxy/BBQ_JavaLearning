package Aufgabe6.objekte;

public class Spieler extends Klub{

    private String name;
    private String vorname;
    private int nummer;
    private Spielerrolle rolle;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    
    public String getVorname() {
        return vorname;
    }


    public void setVorname(String vorname) {
        this.vorname = vorname;
    }


    
    public int getNummer() {
        return nummer;
    }


    public void setNummer(int nummer) {
        this.nummer = nummer;
    }


    public Spielerrolle getRolle() {
        return rolle;
    }


    public void setRolle(Spielerrolle rolle) {
        this.rolle = rolle;
    }

    public Spieler(int id, String name, String vorname, int nummer, Spielerrolle rolle) {
        super(id);
        this.name = name;
        this.vorname = vorname;
        this.nummer = nummer;
        this.rolle = rolle;
    }
}
