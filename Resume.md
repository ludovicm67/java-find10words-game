# Répartition des classes

J'ai d'abord réfléchis à comment répartir le code à l'aide de schémas. J'ai décidé de faire une classe Pot, Word, Joueur et Game. La classe Game consiste à lancer le jeu.

# Classe joueur

J'ai d'abord créer la classe joueurs constitués du nom du joueur, des mots qu'il a trouvé et d'un boolean permettant de dire si c'est un humain ou non (à cause des IA). Cette classe permet de gérer les joueurs, c'est-à-dire leur noms mais aussi de faire un rapport avec la classe mots. Cette classe permet aussi de gérer les IA, j'aurais pu faire une classe "IA" mais étant donné qu'elles agissent comme des joueurs j'ai fait le choix de les laisser dans la classe joueurs.

# Classe pot

La classe pot est consitituée d'une liste de caractères. Elle va permettre de pouvoir ajoutée des lettres, les supprimer, afficher le pot... En bref elle est très utile et les méthodes contenues dans cette classe sont très utilisées par la classe Game.

# Classe Word

Celle-ci va permettre de dire si une chaine de caractère est un mot ou pas. C'est cette classe qui va utilisé le fichier "dico.txt" et servir également à l'IA Glados.

# Classe Game

La classe game est la plus importante, elle va servir à lancer le jeu et créer les tours

# Les IA :

L'IA Glados fonctionne de cette manière : Elle crée une copie du pot, elle prend la première lettre de celui-ci et cherche dans le dico tous les mots qui commencent par cette lettre. Elle va ensuite prendre la deuxième lettre et dans ce cas deux cas sont possibles:

- Avec la méthode searchWordinDico elle va testé si le bout de mot est présent dans le dico, si c'est le cas elle va ajouter la deuxième lettre au mot puis passera à la troisième
- Si ce n'est pas le cas elle va supprimer cette lettre du mot et prendre la suivante

Si à la fin du parcours du pot elle n'a pas trouvé de mots elle va lui enlever la première lettre et refaire la même procedure jusqu'à ce que le pot soit vide. (la boucle s'arrête si un mot est trouvé)

Exemple d'utilisation :

On a le pot : X C Z H O L U M

Premier parcours : L'IA va chercher tous les mots commençant par X : elle en trouve. Ensuite tous les mots commençant par XS, elle n'en trouve pas donc le mot vaut toujours X, elle lui ajoute ensuite Z. Le mot XZ n'est pas non plus dans le pot, elle lui enlève donc cette lettre et fait le meme test jusqu'à la fin du pot.

Deuxième parcours : L'IA crée le mot "C", elle lui ajoute Z mais "CZ" n'est pas un mot, elle enlève donc la lettre Z. Elle lui ajoute ensuite un 'H', dans le dico des mots commencent par 'CH' du coup elle ajoute O et dans le dico des mots commencent par "CHO", elle ajoute ensuite L mais aucun mots ne commencent par "CHOL" donc elle enlève L puis ajoute U , "CHOU" est dans le dico du coup elle le renvoie.

Avantage : L'IA est assez rapide et peut trouver les mots de n'importe quelle longueur.
Désavantages : Il faut que le mot soit dans l'ordre, par exemple elle n'aurait pas trouvés le mot chou si le pot avait été :
X C Z O H L U M (O et H sont inversés)

IA R2D2 :

R2D2 copie l'état actuel du pot (comme Glados), elle va ensuite enlever les lettres de manière aléatoires jusqu'à ce qu'il ne reste plus que 5 lettres au maximum qui restent (pour une question de performance et éviter que l'IA ne passe 10 ans à trouver un mot). A partir de là, ça va appeler "r2d2Anagramme" qui va se charger de fournir une liste de toutes les combinaisons possibles des mots que l'on peut former avec x lettres (x <= 5), (la méthode en elle-même pourrait fonctionner avec un plus grand nombre d'éléments; mais on fait juste en sorte de l'appeler avec un nombre assez réduit de lettres pour éviter de le faire tourner en rond). Après on va aussi faire appel à "r2d2SubAnagramme" qui elle va se charger de faire la même chose que la méthode précédente, mais en enlevant à chaque fois une lettre, histoire d'avoir au final la liste de tous les mots possibles à former avec une lettre du pot, avec deux lettres du pot, avec trois lettres du pot, ..., avec x lettres du pot.

Donc là on se retrouve avec une belle liste de chaines de caractères (l'ensemble de toutes ces combinaisons) et on va tester tous ces mots un par un, et voir s'il existe dans le dico. S'il existe : on sort de la boucle, et on fait en sorte que l'IA joue ce mot, sinon : on continue de parcourir la liste, et si arrivé au bout on a toujours pas trouvé de mot, on fait en sorte que l'IA dit qu'elle n'a rien trouvé.

Les inconvéniants de l'IA : devient très lent avec un grand dictionnaire, ne se base uniquement sur les lettres du mot commun, et ne rejoue pas si elle trouve un mot (juste histoire de ne pas avoir un bot surpuissant; déjà que l'IA a droit au dico et qu'elle teste des centaines de combinaisons avant de proposer son mot..), et qu'elle peut proposer à nouveau un mot qu'elle a déjà proposé (permet de redonner espoir aux joueurs humains).

Les avantages : Elle est performante. (j'ai perdu pas mal de fois contre elle :( )
