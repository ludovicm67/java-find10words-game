package com.game;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

// Public class which describe the game
public class Game {

  private List<Player> players = new ArrayList<Player>();
  private Pot pot = new Pot();

  // Initialize a new game
  public void start() {
    this.setUp();
    char cMin='Z';
    List<Player> players2 = new ArrayList<Player>();
    for (Player p : this.players) {
      char c = this.pot.pickChar();
      System.out.println(p.getName() + " has picked the letter " + c);
      if (c < cMin) {
        cMin = c;
        players2.add(0, p);
      } else players2.add(p);
    }
    this.players=players2;
    boolean hasEnded = false;
    while(!hasEnded) {
      for (Player p : this.players) {
        this.play(p);
        this.printCurrentState();
        if(this.hasSomeoneWon()) {
          hasEnded = true;
          break;
        }
      }
    }
  }

  // Set up a new game (ask for required parameters)
  public void setUp() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Do you want to play with Glados, an IA ? (yes/no) : ");
    String answer = sc.next();
    if (answer.equals("yes")) this.addGlados();
    System.out.print("Do you want to play with R2-D2, an IA ? (yes/no) : ");
    answer = sc.next();
    if (answer.equals("yes")) this.addR2d2();
    System.out.print("How many (human) players ? ");
    int nbPlayers = sc.nextInt();
    for (int i = 0; i < nbPlayers; i++) {
      System.out.print("Enter player " + (i+1) + "'s name : ");
      this.addPlayer(new Player(sc.next()));
    }
    System.out.println();
  }

  // Add Glados IA
  public void addGlados() {
    Player glados = new Player("Glados [IA]");
    glados.setIA();
    this.addPlayer(glados);
  }

  // Add R2D2 IA
  public void addR2d2() {
    Player glados = new Player("R2-D2 [IA]");
    glados.setIA();
    this.addPlayer(glados);
  }

  // Verify the word of player
  public boolean verify(Word word, Player p) {
    if (word.isWord()) {
      if (this.verifyFindWord(word, p)) System.out.println("Sorry, you already found this word.");
      else if (this.wordInWord(word, p)) this.verifyStolenWord(word, p);
      else if (!this.verifyLetters(word.getWord())) {
        System.out.println("Sorry, some letters aren't in the pot.");
      } else if (this.charInString('-', word.getWord())) this.verifyFoundDoubleWords(word, p);
      else this.verifyFoundWord(word, p);
      return true;
    } else {
      System.out.println("Sorry, isn't a word.");
      return false;
    }
  }

  public void verifyFoundDoubleWords(Word word, Player p) {
    System.out.println("Good job ! You found two words at once !");
    this.pot.remove(word.doubleWords()[0].getWord());
    this.pot.remove(word.doubleWords()[1].getWord());
    Word word1 = word.doubleWords()[0];
    Word word2 = word.doubleWords()[1];
    p.addWord(word1);
    p.addWord(word2);
    this.pot.pickChar();
  }

  public void verifyStolenWord(Word word, Player p) {
    System.out.println("Good job ! You found a word and you stole a word from a player !");
    this.pot.remove(word.getWord());
    p.addWord(word);
    this.pot.pickChar();
  }

  public void verifyFoundWord(Word word, Player p) {
    System.out.println("Good job !");
    this.pot.remove(word.getWord());
    p.addWord(word);
    this.pot.pickChar();
  }

  // Verify if letters of word are in the pot
  public boolean verifyLetters(String chain) {
    char c;
    for (int i = 0; i < chain.length(); i++) {
      if (chain.charAt(i) != '-') {
        int nb = (int) (chain.charAt(i));
        c = (char) (nb - 32);
      } else c = '-';
      if(!this.pot.contains(c)) return false;
    }
    return true;
  }

  // Verify if the word has been already found
  public boolean verifyFindWord(Word word, Player p) {
    for (Word str : p.getWords()) {
      if (word.getWord().equals(str.getWord())) return true;
    }
    return false;
  }

  // Allow to one player to play
  public void play(Player p) {
    System.out.println("\n\n\033[0;1mIt's " + p.getName() + "'s turn.\033[0m");
    this.pot.pickChar(2);
    this.pot.print();
    if (p.isIA() && p.getName().equals("R2-D2 [IA]")) this.playr2d2IA(p);
    else if (p.isIA() && p.getName().equals("Glados [IA]")) this.playGladosIA(p);
    else this.playHuman(p);
  }

  public void playHuman(Player p) {
    Scanner rep = new Scanner(System.in);
    System.out.println("Do you want to do a word ? (yes/no)");
    String answer = rep.next();
    if (answer.equals("yes")) {
      System.out.print("Please write the word : ");
      String w = rep.next();
      w = w.toLowerCase();
      Word word = new Word(w);
      boolean testVerif;
      testVerif = this.verify(word, p);
      if (testVerif) {
        this.pot.print();
        this.playHuman(p);
      }
    } else System.out.println("Next turn.");
  }

  public void playr2d2IA(Player p) {
    System.out.println("I'm thinking...");

    ArrayList<String> po = new ArrayList<String >();
    ArrayList<String> pan = new ArrayList<String >();

    Pot potIA = new Pot();
    potIA.addAll(this.pot.getContent());

    while (potIA.size() > 5) {
      Random r = new Random();
      int indiceToRemove = 0 + r.nextInt(potIA.size());
      potIA.remove("" + potIA.getContent().get(indiceToRemove));
    }

    // Tranforme les lettres du pot commun en String en minuscule
    for (Character letter : potIA.getContent()) {
      po.add("" + Character.toLowerCase(letter));
    }

    pan = this.r2d2Anagramme(po);
    if (po.size() > 0) pan.addAll(this.r2d2SubAnagramme(po));

    // Permet de virer tous les doublons
    Object[] st = pan.toArray();
    for (Object s : st) {
      if (pan.indexOf(s) != pan.lastIndexOf(s)) {
          pan.remove(pan.lastIndexOf(s));
       }
    }

    String res = "";
    for (int i = 0; i < pan.size(); i++) {
      Word test = new Word(pan.get(i));
      if (test.isWord()) {
        res = test.getWord();
        break;
      }
    }
    if (res.equals("")) {
      System.out.println("I have nothing to propose...");
    } else {
      System.out.println("I propose : " + res);
      Word word = new Word(res);
      this.verify(word, p);
    }
    System.out.println("Next!");
  }

  public void playGladosIA(Player p) {
    System.out.println("I'm thinking...");
    String res = this.tourGlados().getWord();
    if (res.equals("")) {
      System.out.println("I have nothing to propose...");
    } else {
      System.out.println("I propose : " + res);
      Word word = new Word(res);
      this.verify(word, p);
    }
    System.out.println("Next!");
  }

  // test if a char is in a string
  public boolean charInString(char c, String chain) {
    int i;
    for (i=0; i<chain.length(); i++) {
      if (c == chain.charAt(i)) return true;
    }
    return false;
  }

  // test if word of one player is in word of other player
  public boolean wordInWord(Word w, Player p) {
    for (Player player : this.players) {
      for (Word word : player.getWords()) {
        if (w.getWord().length() > word.getWord().length() && w.getWord().contains(word.getWord())) {
          if (player != p) {
            player.removeWord(word);
          }
          String test = w.getWord().replace(word.getWord(), "");
          return this.verifyLetters(test);
        }
      }
    }
    return false;
  }

  // Add a player
  public void addPlayer(Player p) {
    this.players.add(p);
  }

  // We have a Winner ! :D
  public boolean hasSomeoneWon() {
    for (Player p : this.players) {
      if (p.nbWords() >= 10) {
        System.out.println("Congratulations ! " + p.getName() + " won !");
        return true;
      }
    }
    return false;
  }

  // Number of players
  public int nbPlayers() {
    return this.players.size();
  }

  // Informations about the current state of the game
  public void printCurrentState() {
    System.out.println("\n======== CURRENT STATE ========");
    int nb = this.nbPlayers();
    if(nb > 1) System.out.println("There are currently " + nb + " players :");
    else if(nb == 1) System.out.println("There is currently " + nb + " player :");
    else System.out.println("Nobody is playing currently.");
    for(Player p : this.players) {
      System.out.println("    - " + p.getName() + " (" + p.nbWords() + " word(s) found)");
      for(Word w : p.getWords()) {
        System.out.println("        - " + w.getWord());
      }
    }
    System.out.println("============= END =============");
  }

  // Génère toutes les combinaisons possibles avec les chaînes passées en arg
  public ArrayList<String> r2d2Anagramme(ArrayList<String> t) {
    String racine;
    ArrayList<String> ch = new ArrayList<String>();
    ArrayList<String> h = new ArrayList<String>();
    ArrayList<String> result = new ArrayList<String>();
    LinkedList<String> ret = new LinkedList<String>();
    int i, a;
    if (t.size() == 1) return t;
    else {
      for (i = 0; i < t.size(); i++) {
        racine = t.get(i);
        if (ret.indexOf(racine) < 0) {
          ret.add( racine );
          h.clear() ;
          h.addAll( t);
          h.remove(i);
          a = result.size();
          ch = this.r2d2Anagramme(h);
          result.addAll(ch);
          for (int j = a; j < result.size(); j++) {
              result.set(j, racine + result.get(j));
          }
        }
      }
      return result;
    }
  }

  // Génère toutes les combinaisons possibles en enlevant une lettre à chaque fois
  public ArrayList<String> r2d2SubAnagramme(ArrayList<String> list) {
    ArrayList<String> list2 = new ArrayList<String >();
    ArrayList<String> res = new ArrayList<String >();
    int nb = list.size();
    list2.addAll(list);
    for (int i = 0; i < nb; i++) {
      String f = list2.get(0);
      list2.remove(0);
      res.addAll(this.r2d2Anagramme(list2));
      if (nb > 0) res.addAll(this.r2d2SubAnagramme(list2));
      list2.add(f);
    }
    return res;
  }

  // Test if a piece of word is in the dico
  public String testPieceofword(Word wd, String word){
    if (!(wd.searchWordinDico(word))){
      word=word.substring(0, word.length()-1);
    }
    return word;
  }

  // Try to do a word (for Glados)
  public Word makeWord(Pot p) {
    Word wd = new Word();
    String word="";
    for (Character letter : p.getContent()) {
      word += letter;
      word = word.toLowerCase();
      word = this.testPieceofword(wd, word);
      wd.setWord(word);
    }
    return wd;
  }

  public Word tourGlados() {
    Pot potCopy = new Pot();
    potCopy.addAll(this.pot.getContent());
    Word mot = this.makeWord(potCopy);
    int i = 0;
    while (!(mot.isWord()) && (i<potCopy.size())) {
      potCopy.remove("" + potCopy.getContent().get(0));
      mot = this.makeWord(potCopy);
      i++;
    }
    if (!(mot.isWord())) {
      Word empty = new Word("");
      return empty;
    }
    else return mot;
  }

}
