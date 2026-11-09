package pets.tierpraxis.objekte;

import java.time.LocalDate;

import pets.Art;
import pets.Geschlecht;

public class Pet extends pets.Superpet {

    String name;

    Geschlecht geschlecht;
    Art art;

    LocalDate geburtsdatum;

    boolean istGeimpft;

    double gewicht;

    boolean versichert;

        public Pet(Art art, Geschlecht geschlecht) {
        super(art, geschlecht);
    }

}
