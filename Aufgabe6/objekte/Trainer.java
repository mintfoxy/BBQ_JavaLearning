package Aufgabe6.objekte;

public class Trainer extends Klub{

    private String name;
    private String vorname;
    private Trainerrolle rolle;
    
    public Trainer(int id, String name, String vorname, Trainerrolle rolle) {
        super(id);
        this.name = name;
        this.vorname = vorname;
        this.rolle = rolle;
    }
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
    public Trainerrolle getRolle() {
        return rolle;
    }
    public void setRolle(Trainerrolle rolle) {
        this.rolle = rolle;
    }





}
