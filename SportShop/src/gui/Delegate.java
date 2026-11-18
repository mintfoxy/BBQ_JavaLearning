package gui;

import data.objekte.Article;

/**
 * beschreibt die Ereignisse, die die Panelle an die ArticleFormPanel melden
 */
public interface Delegate {

    void articleSelected(Article article); // meldet den ausgewählten Artikel oder eine aufgehobene Auswahl

    void saveRequested(Article article); // übergibt die Eingaben zum Speichern

    void deleteRequested(Article article); // übergibt den Artikel zum Löschen

    void resetRequested(); // fordert das Zurücksetzen beider Panelle an
}