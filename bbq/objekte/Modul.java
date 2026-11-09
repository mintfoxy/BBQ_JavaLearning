package bbq.objekte;

public class Modul extends BBQObject {

    private String bezeichnung; // speichert den Namen bzw. die Bezeichnung des Moduls.
    private Spezialisierung spezialisierung; // verweist auf die zugeordnete Spezialisierung; anfangs null.

    public Modul(int id, String bezeichnung) { // Konstruktor mit ID und Modulbezeichnung.
        super(id); // Übergibt die ID an den Konstruktor von BBQObject.
        this.bezeichnung = bezeichnung; // Speichert die Modulbezeichnung.
    }

    @Override // implementiert die abstrakte Methode getFileName.
    protected String getFileName() { // Liefert den Dateinamen für Module.
        return "module.csv"; // gibt den Dateinamen zurück.
    }

    @Override // implementiert die abstrakte Methode getData.
    protected String getData() { // Erstellt eine Datenzeile für dieses Modul.
        String spezialisierungId = spezialisierung == null // Prüft, ob keine Spezialisierung zugeordnet ist.
                ? "" // Ohne Spezialisierung wird ein leerer String verwendet.
                : String.valueOf(spezialisierung.getId()); // andernfalls wird die ID der Spezialisierung als String verwendet.

        return id + ";" + bezeichnung + ";" + spezialisierungId; // Gibt ID, Bezeichnung und Spezialisierungs-ID zurück.
    }

    public String getBezeichnung() { // Getter für die Modulbezeichnung.
        return bezeichnung; // Gibt die gespeicherte Bezeichnung zurück.
    }

    public void setBezeichnung(String bezeichnung) { // Setter für die Modulbezeichnung.
        this.bezeichnung = bezeichnung; // Speichert die neue Bezeichnung.
    }

    public Spezialisierung getSpezialisierung() { // Getter für die zugeordnete Spezialisierung.
        return spezialisierung; // Gibt die Referenz auf die Spezialisierung zurück.
    }

    public void setSpezialisierung(Spezialisierung spezialisierung) { // Setter für die Spezialisierung.
        this.spezialisierung = spezialisierung; // Speichert die übergebene Objektreferenz.
    }

    @Override // Überschreibt die Methode toString aus Object.
    public String toString() { // Erstellt eine lesbare Darstellung des Moduls.
        String spezialisierungName = spezialisierung == null // Prüft, ob eine Spezialisierung fehlt.
                ? "nicht zugeordnet" // Verwendet ohne Spezialisierung diesen Ersatztext.
                : spezialisierung.getBezeichnung(); // Liest andernfalls die Bezeichnung der Spezialisierung aus.

        return bezeichnung // Beginnt die Darstellung mit der Modulbezeichnung.
                + " id " + id // Ergänzt die ID des Moduls.
                + " Spezialisierung: " + spezialisierungName; // Ergänzt die Spezialisierung oder den Ersatztext.
    }
}