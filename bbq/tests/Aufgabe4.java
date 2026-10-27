package bbq.tests;

import bbq.objekte.Azubi;
import bbq.objekte.Fach;
import bbq.objekte.Gruppe;
import bbq.objekte.Modul;
import bbq.objekte.Spezialisierung;

public class Aufgabe4 {

    public static void main(String[] args) {
        
Fach fach1 = new Fach(1, "IT");

Spezialisierung spezi1 = new Spezialisierung(1, "AE", fach1);
String msg = fach1.addSpezialisierung(spezi1);
Spezialisierung spezi2 = new Spezialisierung(2, "SI", fach1);
msg = fach1.addSpezialisierung(spezi2);
Spezialisierung spezi3 = new Spezialisierung(3, "DA", fach1);
msg = fach1.addSpezialisierung(spezi3);
IO.print(msg);

Modul modul1 = new Modul(1, "Java", spezi1);
Modul modul2 = new Modul(2, "Super Java", spezi1);
Modul modul3 = new Modul(3, "Ultra Java", spezi1);

Modul modul4 = new Modul(4, "Admin", spezi2);
Modul modul5 = new Modul(5, "Super Admin", spezi2);
Modul modul6 = new Modul(6, "Ultra Admin", spezi2);

Modul modul7 = new Modul(7, "Python", spezi3);
Modul modul8 = new Modul(8, "Super Python", spezi3);
Modul modul9 = new Modul(9, "Ultra Python", spezi3);

Gruppe gruppe1 = new Gruppe(1, "U30-1");
Gruppe gruppe2 = new Gruppe(2, "U30-2");

Azubi azubi20 = new Azubi(20, "Durov", "Pavel", spezi1, gruppe1);
Azubi azubi21 = new Azubi(21, "Potter", "Harry", spezi1, gruppe1);
Azubi azubi22 = new Azubi(22, "Altman", "Sam", spezi1, gruppe1);
Azubi azubi23 = new Azubi(23, "Trump", "Donald", spezi1, gruppe1);
Azubi azubi24 = new Azubi(24, "Gaultier", "Paul", spezi1, gruppe1);

Azubi azubi25 = new Azubi(25, "Doe", "John", spezi2, gruppe2);
Azubi azubi26 = new Azubi(26, "Pitt", "Brad", spezi2, gruppe2);
Azubi azubi27 = new Azubi(27, "Sparrow", "Jack", spezi2, gruppe2);
Azubi azubi28 = new Azubi(28, "Clinton", "Bill", spezi2, gruppe2);
Azubi azubi29 = new Azubi(29, "Merkel", "Angela", spezi2, gruppe2);


    }

}
