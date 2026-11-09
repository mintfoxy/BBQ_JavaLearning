package pets;

//  diese Klasse hat alles, was alle Pets gemeinsam haben, also die Attribute und Methoden, die alle Pets haben

public class Superpet {

    Art art;
    Geschlecht geschlecht;

    public Superpet(Art art, Geschlecht geschlecht) {
        super();
        this.art = art;
        this.geschlecht = geschlecht;
    }

    //  Standartkonstruktor
    public Superpet(){

    }


}
