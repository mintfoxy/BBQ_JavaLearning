package pets.adoption;

import pets.Geschlecht;
import pets.adoption.objekte.Pet;

public class Adoption {

    public static void main(String[] args) {

        //  Wir erzeugen eine Instanz der Klasse Pet, die von der Klasse Superpet erbt.
        Pet ilu = new Pet(
                pets.Art.HUND,
                Geschlecht.MÄNNCHEN,
                "Ilu",
                "Deutschland"
        );

        System.out.println(ilu.toString() + " ist fertig");

    }

}