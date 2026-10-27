package bbq.objekte;

import java.util.ArrayList;

public class Gruppe {

    private int id;
    private String gruppe_name;

    private ArrayList<Azubi> teilnehmer;

    public String addAzubi(Azubi tn){ //  diese Methode fügt das Objekt Azubi zu der Liste

        if (teilnehmer == null)  //  prüfe, die Liste noch leer ist
            teilnehmer = new ArrayList<Azubi>();   //  erzeuge eine Liste

        if (teilnehmer.contains(tn)){   //  prüfe, ob der Eintrag bereits vorhanden ist
            return "Das Objekt ist bereits in der Liste";
        }

        teilnehmer.add(tn);  //  adde ihn zur Liste

        return null;    //  gibt nichts/null zurück, wenn alles ok war

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getGruppe_name(){
        return gruppe_name;

    }

    public void setGruppe_Name(String gruppe_name){
        this.gruppe_name = gruppe_name;
    }

    public Gruppe(){

    }

    public Gruppe(int id, String gruppe_name){
        super();
        this.id = id;
        this.gruppe_name = gruppe_name;
    }

    @Override
    public String toString(){
        String str = "Gruppe " + gruppe_name + " id " + id;
        return str;
    }

}
