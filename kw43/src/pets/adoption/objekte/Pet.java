package pets.adoption.objekte;

import java.time.LocalDate;

import pets.Art;
import pets.Geschlecht;

public class Pet extends pets.Superpet {

    String name;
    String land;
    boolean hatPass;
    LocalDate geburtsdatum;

    //  In diesem Konstruktor werden die Attribute der Elternklasse mit super() aufgerufen,
    //  damit die Attribute der Elternklasse auch initialisiert werden 
    public Pet(Art art, Geschlecht geschlecht, String name, String land) {
        super(art, geschlecht);
        this.name = name;
        this.land = land;
    }

    //  Wenn eine Tochterklasse einen Standartkonstruktor hat, 
    // muss die Elternklasse auch einen Standartkonstruktor haben, 
    // sonst gibt es einen Fehler
    public Pet() {
        super();
    }

}
