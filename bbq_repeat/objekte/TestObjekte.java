package bbq_repeat.objekte;

public class TestObjekte {

    public static void main(String[] args) {



        Fach fach1; //  Datentyp: Klasse Fach, Name der Variable: fach1
        fach1 = new Fach(); //  neue Instanz
        fach1.setId(1);
        fach1.setFach_Name("IT");

        Spezialisierung spezi1;
        spezi1 = new Spezialisierung();
        spezi1.setId(1);
        spezi1.setBezeichnung("AE");
        spezi1.setId(fach1.getId());  //  FK in unserer Tabelle in der Datenbank

        Spezialisierung spezi2 = new Spezialisierung(2, "SI", fach1.getId());    //  Konstruktor aus der Klasse Spezialisierung
        Spezialisierung spezi3 = new Spezialisierung(3, "DA", fach1.getId());

        Modul modul1 = new Modul(1, "WISO", fach1.getId(), spezi1.getId());



        Fach fach2;
        fach2 = new Fach(2, "MEDI");


        
    }

}