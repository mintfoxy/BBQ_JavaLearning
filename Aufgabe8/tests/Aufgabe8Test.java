package Aufgabe8.tests;

import Aufgabe8.objekte.Key;
import Aufgabe8.objekte.Menu;
import Aufgabe8.objekte.Scope;

public class Aufgabe8Test {

    public static void main(String[] args) {

        Key kategorie = inputKategorie();
        Scope eintrag = inputEintrag(kategorie);

        showEintrag(eintrag);

        eintrag.save();

    }

    public static Key inputKategorie() {
    String bezeichnung = IO.readln("Kategorie eingeben: ");
    return createKategorie(bezeichnung);
    }

    public static Scope inputEintrag(Key kategorie) {
    String beschreibung = IO.readln("Beschreibung eingeben: ");
    return createEintrag(kategorie, beschreibung);
    }

    public static Key createKategorie(String bezeichnung) {
        return new Key(bezeichnung);
    }

    public static Scope createEintrag(Key kategorie, String beschreibung) {
        return new Scope(kategorie, beschreibung);
    }

    public static void showKategorie(Key kategorie) {
        IO.println(kategorie.getId() + ": " + kategorie.getKategorie());
    }

    public static void showEintrag(Scope eintrag) {
        IO.println(eintrag.getId() + ": " + eintrag.getKategorie().getKategorie() + " - " + eintrag.getBeschreibung());
    }

    public static void saveObjekt(Menu objekt) {
        objekt.save();
    }
}