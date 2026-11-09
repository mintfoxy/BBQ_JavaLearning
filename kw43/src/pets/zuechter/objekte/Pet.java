package pets.zuechter.objekte;

import java.time.LocalDate;

import pets.Art;
import pets.Geschlecht;

public class Pet extends pets.Superpet {

    Geschlecht geschlecht;

    Art art;

    boolean hatStammbaum;

    boolean hatPass;

    LocalDate geburtsdatum;

        public Pet(Art art, Geschlecht geschlecht) {
        super(art, geschlecht);
    }

}
