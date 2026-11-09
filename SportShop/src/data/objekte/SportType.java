package data.objekte;

public enum SportType { // enthält die verfügbaren Sportarten

    TENNIS("Tennis"),
    LAUFWN("Laufen"),
    BADMINTON("Badminton"),
    SCHWIMMEN("Schwimmen"),
    FUßBALL("Fußball");

    private final String label; // speichert den lesbaren Namen der Sportart

    private SportType(String label) { // übernimmt den Namen der jeweiligen Konstante
        this.label = label; // speichert den übergebenen Namen
    }

    public String getLabel() { // ermöglicht das Lesen des Namens
        return label; // gibt den Namen zurück
    }

    @Override // ersetzt die Standarddarstellung einer enum Konstante
    public String toString() { // liefert einen lesbaren Text für die Konsole
        return label; // gibt zum Beispiel „Tennis“ zurück
    }
}
