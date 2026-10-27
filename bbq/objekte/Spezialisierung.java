package bbq.objekte;

import java.util.ArrayList;

public class Spezialisierung {

    private int id;
    private String bezeichnung;
    private Fach fach;  //  Foreign Key in unserer Tabelle, 1:1, eine Spezialisierung gehört zu einem Fach      

    private ArrayList<Modul> module;
    
    public String addModul(Modul m){ //  diese Methode fügt das Objekt Azubi zu der Liste

        if (module == null)  //  prüfe, die Liste noch leer ist
            module = new ArrayList<Modul>();   //  erzeuge eine Liste

        if (module.contains(m)){   //  prüfe, ob der Eintrag bereits vorhanden ist
            return "Das Objekt ist bereits in der Liste";
        }

        module.add(m);  //  adde ihn zur Liste

        return null;    //  gibt nichts/null zurück, wenn alles ok war

    }

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
