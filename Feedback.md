# Feedback
##Dokumentation
Die Dokumentation hat Mängel in der Rechtschreibung und beinhaltet die Keys für Twitter. Dies sollte vermieden werden, Passwörter etc dürfen nicht im Repository eingecheckt werden, schon gar nicht in einem öffentlich lesbaren.

##Git- und Github-Nutzung
Zunächst fällt auf, dass das Projekt einen Unterordner mit gleichem Namen besitzt. Dies verwirrt. Das Projekt als solches Bildet den Namen, bei einem cloneing des Projekts liegen alle Dateien also bereits im richtigen Ordner vor.
Weiterhin wurden wenige Commits verwendet. Was jedoch sehr seltsam ist, wie umgeht man das Zusammenarbeiten bei Github? Ihr habt gemeinsam das gleiche Projekt abgegeben und das Syncing ohne Git sondern scheinbar per Hand gemacht.
Es wäre sehr einfach gewesen als Kollaborator an einem Projekt zu arbeiten.

##Funktionalität
Das Starten und followen funktioniert problemlos und die Menüführung ist gut.

Leider stört ein permanenter Strom aus Debugmeldungen die Bedienung. Diese sind außerdem keineswegs Hilfreich für den Benutzer.

    Erfolgreich updated zu (@TestObjekt1 The person you have talked is temporarily not available! (129))
    Konnte nicht updaten!

Auch die Antwort mit Erwähnung des Fragestellers wurde nicht korrekt umgesetzt, sondern durch eine direkte Nachricht. 


##Code
Das Programm ist sehr übersichtlich gestaltet und extrem simple gehalten. Das ist sehr gut, etwas mehr Kreativität wäre für die Antworten sicher möglich gewesen.
Diese sollten auch nicht im Code definiert werden, sondern in einer externen Datenquelle (DB, .properties-Datei, etc.)
Leider wird der Check für bereits beantwortete Messages zur Laufzeit wachsen. Es wäre ausreichend die letzte beantwortete Nachricht pro Nutzer zu speichern.
















