package aufgabe3;

//  Schreibe eine Klasse Buch, die ein Buch beschreibt
//  Autor, Titel, ISBN

public class buch {

    Autor autor;
    String titel;
    int isbn;

public buch(int isbn){

    this.isbn = isbn;

}

public buch(String titel){
    
    this.titel = titel;

}

public buch(Autor autor){

    this.autor = autor;

}

public buch(boolean neu){

}

}
