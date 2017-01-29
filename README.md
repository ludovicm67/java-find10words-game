# Comment compiler et lancer le projet ?

Pour compiler et lancer ce projet, vous devez avoir au minimum Java 7 sur votre machine (+ javac).

Il vous faudra aussi avoir Maven (installable sur Ubuntu avec un simple `sudo apt install maven`).

Placez-vous dans le dossier principal, et lancez :
  * `mvn -q compile` pour compiler le programme (le -q permet de rendre Maven un peu moins bavard).
  * `mvn -q exec:java` pour éxécuter le programme.
  * `mvn -q compile && mvn -q exec:java` pour lancer la compilation et l'éxécution en une ligne de commande
  * `mvn -q test` pour lancer l'ensemble des tests unitaires


# Règles du jeu

  * Objectif du jeu :
    * Le premier joueur ayant 10 mots gagne la partie

  * Déroulement du jeu :
    * Chacun des joueurs tire une lettre aléatoire d'un sac, et les mettent au milieu dans le pot commun
    * **Le joueur qui a tiré la lettre la plus petite dans l'alphabet commence**
    * Chaque fois que c'est le début du tour d'un joueur, il tire deux lettres aléatoires qu'il rajoute au pot commun
    * Chaque fois qu'un joueur fait un mot, il tire une lettre aléatoire qu'il rajoute au pot commun
    * **Quand le joueur ne trouve plus de mots, il passe et le joueur suivant commence son tour (par tirer 2 lettres qu'il rajoute au pot commun)**

  * Comment faire un mot ?
    * En utilisant uniquement les lettres du pot commun
    * En prennant un mot de ces adversaires (toutes les lettres du mot) et en lui rajoutant des lettres du pot commun
    * **En rallongeant un de ses mots avec des lettres du pot commun ou en utilisant un autre mot (toutes les lettres)**
    * Attention seul les noms communs sont autorisés

  * Pour faciliter :
    * les lettres possibles sont uniquement les 26 lettres de l'alphabet (ex : é <-> e)
    * les mots composés sont considérés comme deux mots

# Objectifs du TP

  * Une première étape consiste à pouvoir jouer à plusieurs autours d'un même écran.
  * Une interface en ligne de commande est suffisante.
  * Nous attendons aussi a minima une de ces deux extensions (ou les deux pour les plus courageux) :
    * *Une architecture client/serveur, chaque joueur utilisant une instance d'un client pour jouer.*
    * **Une intelligence artificielle permettant de jouer contre l'ordinateur**
  * **Nous attendons aussi une description de votre architecture (quelle responsabilité à chaque package,...)**
  * **A répondre dans le README.md : illustrer 3 principes SOLID ou design pattern en quelques paragraphes seulement en utilisant vos propres classes, en espliquant pourquoi avoir utilisé ce design pattern/principe, ce que cela vous a apporté et comment l'avez-vous appliqué**

# Technologies à utiliser

  * Le TP devra être rendu sur GitHub et donc être géré via git
    * plusieurs commits par personne sont attendus
  * Le projet doit être rendu en Java
  * Le projet devra pouvoir être compilé et lancé en ligne de commande (sans IDE) :
    * L'utilisation de `Maven` ou `Gradle` est recommandé
    * cela ne sert à rien de commiter une jar. Nous n'exécuterons que du code compilé par nous-même.
  * Le projet doit contenir des tests unitaires
    * Utilisation de `JUnit` ou `Test-ng`
    * L'utilisation des librairies comme `assertJ` et `Mockito` est recommandé.