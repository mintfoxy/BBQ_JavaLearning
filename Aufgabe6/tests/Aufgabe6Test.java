package Aufgabe6.tests;

import Aufgabe6.objekte.Spieler;
import Aufgabe6.objekte.Spielerrolle;
import Aufgabe6.objekte.Mannschaft;
import Aufgabe6.objekte.Klub;

public class Aufgabe6Test {

    public static void main(String[] args) {

        Klub k = createKlub(1);

        Mannschaft m = createMannschaft(1, "TestMannschaft");

        Spieler spieler1 = new Spieler(1, "Potter", "Harry", 10, Spielerrolle.MITTELFELD);

        Spieler spieler2 = new Spieler(2, "Picasso", "Pablo", 33, Spielerrolle.TORWART);

        Spieler spieler3 = new Spieler(3, "Benz", "Mercedes", 7, Spielerrolle.MITTELFELD);

        m.addSpieler(spieler1); //  fügt den Spieler der Mannschaft hinzu
        m.addSpieler(spieler2);
        m.addSpieler(spieler3);

        for (Spieler splr : m.getPlayer()) {

            if (splr.getRolle() == Spielerrolle.MITTELFELD) {

                IO.println(splr.getVorname() + " " + splr.getName());
            }
        }
    }

    public static Klub createKlub(int id) {

        return new Klub(id);
    }

    public static Mannschaft createMannschaft(int id, String bezeichnung) {

        return new Mannschaft(id, bezeichnung);
    }
}