package Aufgabe6.objekte;

import java.util.ArrayList;

public class Mannschaft extends Klub{

    private String name_mannschaft;

    private ArrayList<Spieler> player;  //  in diesem Array dürfen nur die Objekte der Klasse Spieler gespeichert werden
    private ArrayList<Trainer> trainer; //  in diesem Array dürfen nur die Objekte der Klasse Trainer gespeichert werden

    public Mannschaft(int id, String bezeichnung) {
        super(id);
        this.name_mannschaft = bezeichnung;
    }

    public String addSpieler(Spieler splr){ //  diese Methode fügt ein Objekt Spieler zu der Liste hinzu

        if (player == null)  //  prüfe, die Liste noch leer ist
            player = new ArrayList<Spieler>();   //  erzeuge eine Liste

        if (player.contains(splr)){   //  prüfe, ob der Eintrag bereits vorhanden ist
            return "Das Objekt ist bereits in der Liste";
        }

        player.add(splr);  //  adde ihn zur Liste

        return null;    //  gibt nichts/null zurück, wenn alles ok war

    }

    public String addTrainer(Trainer trnr){ //  diese Methode fügt ein Objekt Spieler zu der Liste hinzu

        if (trainer == null)  //  prüfe, die Liste noch leer ist
            trainer = new ArrayList<Trainer>();   //  erzeuge eine Liste

        if (trainer.contains(trnr)){   //  prüfe, ob der Eintrag bereits vorhanden ist
            return "Das Objekt ist bereits in der Liste";
        }

        trainer.add(trnr);  //  adde ihn zur Liste

        return null;    //  gibt nichts/null zurück, wenn alles ok war

    }

    public String getName_mannschaft() {
        return name_mannschaft;
    }

    public void setName_mannschaft(String name_mannschaft) {
        this.name_mannschaft = name_mannschaft;
    }

}
