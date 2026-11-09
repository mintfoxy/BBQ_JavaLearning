package bbq.objekte;

import java.util.ArrayList;

import bbq.BBQObjekt;

public class Gruppe extends BBQObjekt{

    private String bezeichnung;
    private ArrayList<Azubi> teilnehmer;

    public Gruppe(int id, String bezeichnung){
        super(id);
        this.bezeichnung = bezeichnung;
    }

    @Override

    public String toString(){
        return "Instanz von Gruppe\n" + bezeichnung + " mit id " + id;
    }

    public void aufnehmeInDieGruppe(Azubi person){

        if (teilnehmer == null){
            teilnehmer = new ArrayList<Azubi>();

        }

        teilnehmer.add(person);
    }

}
