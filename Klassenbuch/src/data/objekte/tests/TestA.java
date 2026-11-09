package data.objekte.tests;

public class TestA {

	public static void main(String[] args) {

		int tn = 10;
		String t;
		int an = 3;
		Aufgabe a;

		String[] themen = new String[tn];
		for (int i = 0; i < tn; i++) {
			t = "Thema " + i;
			themen[i] = t;

			for (int j = 0; j < an; j++) {
				a = new Aufgabe();
				a.setThema(t);
				a.setNummer(i * an + j);
				a.setText(j + " TextTextTextTextTextTextTextText");
				a.save();
			}
		}
	}

}