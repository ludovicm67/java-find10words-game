Find10words Game
================

> Un jeu en Java où il faut être le premier à trouver 10 mots.


## Règles du jeu

  * Objectif du jeu :
    * Le premier joueur ayant 10 mots gagne la partie

  * Déroulement du jeu :
    * Chacun des joueurs tire une lettre aléatoire d'un sac, et les mettent au milieu dans le pot commun
    * Le joueur qui a tiré la lettre la plus petite dans l'alphabet commence
    * Chaque fois que c'est le début du tour d'un joueur, il tire deux lettres aléatoires qu'il rajoute au pot commun
    * Chaque fois qu'un joueur fait un mot, il tire une lettre aléatoire qu'il rajoute au pot commun
    * Quand le joueur ne trouve plus de mots, il passe et le joueur suivant commence son tour (par tirer 2 lettres qu'il rajoute au pot commun)

  * Comment faire un mot ?
    * En utilisant uniquement les lettres du pot commun
    * En prennant un mot de ces adversaires (toutes les lettres du mot) et en lui rajoutant des lettres du pot commun
    * En rallongeant un de ses mots avec des lettres du pot commun ou en utilisant un autre mot (toutes les lettres)
    * Attention seul les noms communs sont autorisés

  * Pour faciliter :
    * les lettres possibles sont uniquement les 26 lettres de l'alphabet (ex : é <-> e)
    * les mots composés sont considérés comme deux mots


## Description de l'architecture

Nous avons utilisé `Maven` pour la gestion du projet.
Nous travaillons avec un seul package, `com.game`, mais l'ensemble du projet est découpé en différentes classes :
  * `App` : Permet d'initialiser le jeu
  * `Game` : Tout ce qui concerne les mécaniques de jeu, l'évolution de la partie : la classe centrale
  * `Player` : pour définir chaque joueur (son nom, les mots qu'il a trouvé, ...)
  * `Pot` : pour gérer le pot commun (ajouter / retirer des lettres du pot, piocher une lettre aléatoire, etc...)
  * `Word` : pour définir un mot, voir s'il est valide, etc...


## Comment compiler et lancer le projet ?

Pour compiler et lancer ce projet, vous devez avoir au minimum Java 8 sur votre machine (+ `javac`).

Il vous faudra aussi avoir Maven (installable sur Ubuntu avec un simple `sudo apt install maven`).

Placez-vous dans le dossier principal, et lancez :
  * `mvn -q compile` pour compiler le programme (le -q permet de rendre Maven un peu moins bavard).
  * `mvn -q exec:java` pour éxécuter le programme.
  * `mvn -q compile && mvn -q exec:java` pour lancer la compilation et l'éxécution en une ligne de commande
  * `mvn -q test` pour lancer l'ensemble des tests unitaires
  * `mvn -q jxr:jxr && mvn -q pmd:check || xdg-open ./target/site/pmd.html` pour générer le rapport PMD, avec les références aux lignes, et ouvre le rapport dans le navigateur (seulement dans le cas où il y a une ou plusieurs erreurs). Si aucun retour dans le terminal et que le navigateur ne s'ouvre pas, c'est qu'il n'y a pas de soucis.


## Technologies utilisées

  * `git`
  * Java 8
  * Maven
  * JUnit
  * assertJ
