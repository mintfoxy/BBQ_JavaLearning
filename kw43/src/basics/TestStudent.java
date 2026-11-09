package basics;

import tagebuch.TagebuchWriter;

public class TestStudent {

	public static void main(String[] args) {

//		Thomas Müller, geboren am 18.10.2000
		
		Student student = new Student("Thomas", "Müller");
		// student.name = "Müller";
		// student.vorname = "Thomas";
		student.geschlecht = Student.MANNLICH;
		
		Datum datum = new Datum(); //
		
		datum.jahr = 2000;
		datum.tag = 18;
		datum.monat = 10;
		student.gebdat = datum;
		
		TagebuchWriter.addZuTagebuch(student.name);
		
		InstanzPrinter.printStudent(student);
		
//		Sahra Rosenberg, geboren am 7.7.2003.
		
		student = new Student();
		student.name = "Rosenberg";
		student.geschlecht = Student.WEIBLICH;
		datum = new Datum();
		datum.jahr = 2003;
		datum.tag = 7;
		datum.monat = 7;
		student.gebdat = datum;

		InstanzPrinter.printStudent(student);
		TagebuchWriter.addZuTagebuch(student.name);
	}

}
