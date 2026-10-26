package bbq.objekte;

public class Spezialisierung {

    private int id;
    private String bezeichnung;
    private Fach fach;  //  Foreign Key in unserer Tabelle, 1:1, eine Spezialisierung gehört zu einem Fach      

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getBezeichnung(){
        return bezeichnung;

    }

    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

    public Spezialisierung(){

    }

    public Fach getFach(){
        return fach;
    }

    public void setFach(Fach fach){
        this.fach = fach;
    }

    public Spezialisierung(int id, String bezeichnung, Fach fach){
        super();
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.fach = fach;
    }

    @Override
    public String toString(){
        return bezeichnung + " id " + id + " fach " + fach.getId();
    }

}
