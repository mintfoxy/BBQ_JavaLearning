package bbq.objekte;

import java.util.ArrayList;

public class Spezialisierung extends BBQObject{

    private String bezeichnung;
    private Fach fach;  //  Foreign Key in unserer Tabelle, 1:1, eine Spezialisierung gehört zu einem Fach      

    private ArrayList<Modul> module;
    private ArrayList<Azubi> azubis;
    
    public String addModul(Modul m){ //  diese Methode fügt ein Modul Objekt zu der Liste hinzu

        if (module == null)  //  prüfe, die Liste noch leer ist
            module = new ArrayList<Modul>();   //  erzeuge eine Liste

        if (module.contains(m)){   //  prüfe, ob der Eintrag bereits vorhanden ist
            return "Das Objekt ist bereits in der Liste";
        }

        m.setSpezialisierung(this); //  das ist deine Spezialisierung

        module.add(m);  //  adde ihn zur Liste

        return null;    //  gibt nichts/null zurück, wenn alles ok war

    }

    public String addAzubi(Azubi azb){  //  diese Methode fügt ein Azubi Objekt zu der Liste hinzu

        if (azubis == null)  //  prüfe, die Liste noch leer ist
            azubis = new ArrayList<Azubi>();   //  erzeuge eine Liste

        if (azubis.contains(azb)){   //  prüfe, ob der Eintrag bereits vorhanden ist
            return "Das Objekt ist bereits in der Liste";
        }

        azb.setSpezialisierung(this); //  das ist deine Spezialisierung

        azubis.add(azb);  //  adde ihn zur Liste

        return null;    //  gibt nichts/null zurück, wenn alles ok war
    }

    public String getBezeichnung(){
        return bezeichnung;

    }

    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

    public Fach getFach(){
        return fach;
    }

    public void setFach(Fach fach){
        this.fach = fach;
    }

    public Spezialisierung(int id, String bezeichnung){
        super(id);
        this.bezeichnung = bezeichnung;
    }

    public ArrayList<Modul> getModule(){
        return module;
    }

    @Override
    public String toString(){
        return bezeichnung + " id " + id + " fach " + fach.getId() + " Module: " + module;
    }

}
