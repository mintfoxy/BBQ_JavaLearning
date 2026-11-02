package bbq.objekte;

import bbq.utils.FileIO;

public abstract class BBQObject { // abstrakte Mutterklasse: von ihr können keine direkten Objekte erzeugt werden.

    protected static final String SEPARATOR = ";";
	protected static final String FOLDER = "./data/";
	protected static final String FILE_EXTENTION = ".csv";

    protected int id; // Speichert die ID; Unterklassen können direkt auf dieses Feld zugreifen.

    public BBQObject(int id) { // Konstruktor: wird von einer Unterklasse mit super(id) aufgerufen.
        this.id = id; // Speichert den übergebenen Parameter id im Feld id dieses Objekts.
    }

    protected abstract String getFileName(); // Jede konkrete Unterklasse muss ihren Dateinamen zurückgeben.

    protected abstract String getData(); // Jede konkrete Unterklasse muss ihre Daten als String zurückgeben.

    public void save() { // Methode zum Speichern; wird von allen Unterklassen geerbt.
        String msg = FileIO.writeIntoFile(getFileName(), getData()); // Ruft die Methoden der konkreten Unterklasse auf, 
                                                                    // schreibt die Daten und speichert die Rückmeldung.
        IO.print(msg);
    }

    public int getId() { // Getter: ermöglicht das Auslesen der ID.
        return id; // Gibt die ID dieses Objekts zurück.
    }

    public void setId(int id) { // Setter: ermöglicht das Ändern der ID.
        this.id = id; // Weist dem Feld id den neuen Parameterwert zu.
    }
}
