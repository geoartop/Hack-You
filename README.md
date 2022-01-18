[<img alt="GitHub contributors" src="https://img.shields.io/github/contributors/panos-span/Hack-you">](https://github.com/panos-span/Hack-you/graphs/contributors)   [<img alt="GitHub release (latest SemVer)" src="https://img.shields.io/github/v/release/panos-span/Hack-you">](https://github.com/panos-span/Hack-you/releases/tag/v1.0) ![GitHub commit activity](https://img.shields.io/github/commit-activity/m/panos-span/Hack-you?color=orange&style=flat-square)

## Οδηγίες μεταγλώττισης του προγράμματος : 
* `cd Maven` και στη συνέχεια 
* `mvn compile`

## Οδηγίες εκτέλεσης του προγράμματος : 
* `java -jar Amazeing-1.0.jar` ή 
* κάνοντας διπλό κλικ στο `Amazeing-1.0.jar`

## Οδηγίες χρήσης του προγράμματος : 
Μόλις ανοίξετε το jar εισάγετε το username σας.  
Στη συνέχεια μπορείτε να διαβάσετε τις οδηγίες του παιχνιδιού πατώντας το κουμπί How to play
Την πρώτη φορά που θα εκτελεστεί το jar θα δημιουργηθεί ένα read only αρχείο HighScore.txt μέσα στον φάκελο που βρίσκεται το jar.

Για να δημιουργησετέ την τεκμηρίωση του κώδικα του παιχνιδιού εκτελέστε την εντολή `mvn javadoc:javadoc` όταν βρίσκεστε στο directory Maven.   
Τα αποτελέσματα θα εμφανιστούν στο `Maven/target/site/apidocs` με τη μορφή αρχείων html.

## Περιεχόμενα του αποθετηρίου :
**_Στον φάκελο Maven βρίσκεται το java project μας χτισμένο μέσω του Maven_**
* `pom.xml` αρχείο xml για τη διαμόρφωση του project
* [src/main/java](Maven/src/main/java) περιέχει 2 πακέτα τα οποία περιέχουν τα αρχεία java για την υλοποίηση του παιχνιδιού
* [src/main/resources](Maven/src/main/resources) περιέχει εξωτερικά αρχεία απαραίτητα για τη λειτουργία του παιχνιδιού   
  (αρχεία .txt, .png, .wav κλπ)
* [src/test/java](Maven/src/test/java) περιέχει τους ελέγχους μονάδας (JUnit tests) του παιχνιδιού
* Φακελοί `.idea` αρχεία για τη διαμόρφωση του project στο Intellij

## Trailer for game :   
[A[MAZE]ING Trailer](https://www.youtube.com/watch?v=eTh9oq79dy0)
