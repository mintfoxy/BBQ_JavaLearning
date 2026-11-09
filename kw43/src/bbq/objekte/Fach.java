package bbq.objekte;

import bbq.BBQObjekt;

public class Fach extends BBQObjekt {

    String fach_name;

    public Fach(int id, String fach_name){

        super(id);
        this.fach_name = fach_name;

    }

}
