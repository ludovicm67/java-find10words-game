package com.game;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Public class which describe the game
public class Game {

  private List<Player> players = new ArrayList<Player>();
  private Pot pot = new Pot();

  private List<Word> pieceofword; // for Glados

  // Initialize a new game
  public void start() {



    this.pot.add('A');
    this.pot.add('B');
    this.pot.add('C');
    this.pot.add('D');
    this.pot.add('E');
    this.pot.add('F');

    ArrayList<String> po=new ArrayList<String >();
    ArrayList<String> po2=new ArrayList<String >();
    ArrayList<String> pan= new ArrayList<String >();



    // Tranforme les lettres du pot commun en String en minuscule
    for (Character letter : this.pot.getContent()) {
      po.add("" + Character.toLowerCase(letter));
    }

    pan = this.test(po); // n
    if (po.size() > 0) pan.addAll(this.test2(po));

    // Permet de virer tous les doublons
    Object[] st = pan.toArray();
    for (Object s : st) {
      if (pan.indexOf(s) != pan.lastIndexOf(s)) {
          pan.remove(pan.lastIndexOf(s));
       }
    }

    // int nb = po.size();
    // po2.addAll(po); // n-1
    // for (int i = 0; i < nb; i++) {
    //   String f = po2.get(0);
    //   po2.remove(0);
    //   pan.addAll(this.test(po2));
    //   pan.addAll(this.test2(po2));
    //   po2.add(f);
    // }

    for (int i = 0; i < pan.size(); i++) {
      System.out.println(pan.get(i));
    }
    System.out.println(pan.size());
    // this.setUp();
    // for (Player p : this.players) {
    //   System.out.println(
    //     p.getName() + " has picked the letter " + this.pot.pickChar()
    //   );
    // }
    // while(!this.hasSomeoneWon()) {
    //   for (Player p : this.players) {
    //     this.play(p);
    //     this.printCurrentState();
    //   }
    // }
  }

  // Set up a new game (ask for required parameters)
  public void setUp() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Do you want to play with Glados, an IA ? (yes/no) : ");
    String answer = sc.next();
    if (answer.equals("yes")) this.addGlados();
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

  // Verify the word of player
  public void verify(Word word, Player p) {
    if (word.isWord()) {
      Pot pot = this.pot;
      if (this.verifyFindWord(word, p)) {
        System.out.println("Sorry, you already found this word.");
      } else if (!this.verifyLetters(word.getWord())) {
        System.out.println("Sorry, some letters aren't in the pot.");
      } else if (this.charInString('-', word.getWord())) {
          System.out.println("Good job ! You found two words at once !");
          this.removeLetters(word.doubleWords()[0].getWord());
          this.removeLetters(word.doubleWords()[1].getWord());
          Word word1 = word.doubleWords()[0];
          Word word2 = word.doubleWords()[1];
          p.addWord(word1);
          p.addWord(word2);
          pot.pickChar();
      } else if (this.wordInWord(word, p)) {
        System.out.println("Good job ! You found a word and you stole a word from a player !");
        this.removeLetters(word.getWord());
        p.addWord(word);
        pot.pickChar();
      } else {
        System.out.println("Good job !");
        this.removeLetters(word.getWord());
        p.addWord(word);
        pot.pickChar();
      }
    } else System.out.println("Sorry, isn't a word.");
  }

  // remove letters in pot
  public void removeLetters(String chain) {
    this.pot.remove(chain);
  }

  // Verify if char in the pot
  public boolean verifyCharInPot(char c) {
    return this.pot.contains(c);
  }

  // Verify if letters of word are in the pot
  public boolean verifyLetters(String chain) {
    char c;
    for (int i = 0; i < chain.length(); i++) {
      if (chain.charAt(i) != '-') {
        int nb = (int) (chain.charAt(i));
        c = (char) (nb - 32);
      } else c = '-';
      if(!verifyCharInPot(c)) return false;
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
    Scanner rep = new Scanner(System.in);
    System.out.println("\n\n\033[0;1mIt's " + p.getName() + "'s turn.\033[0m");
    this.pot.pickChar(2);
    this.pot.print();
    System.out.println("Do you want to do a word ? (yes/no)");
    String answer = rep.next();
    if (answer.equals("yes")) {
      System.out.print("Please write the word : ");
      String w = rep.next();
      w = w.toLowerCase();
      Word word = new Word(w);
      this.verify(word, p);
    } else {
      System.out.println("Next turn.");
    }
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
      if (player != p) {
        for (Word word : player.getWords()) {
          if (w.getWord().length() > word.getWord().length()
              && w.getWord().contains(word.getWord())) {
            player.removeWord(word);
            return true;
          }
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

  // Try to do a word (for Glados)
  public Word makeWord() {
    Word wd = new Word();
    this.pieceofword = new ArrayList<Word>();
    int i = 0;
    String word = "";
    for (Character letter : this.pot.getContent()) {
      System.out.println("plop");
      if (wd.searchWordinDico(letter, i)) {
        word += letter;
        System.out.println(word);
      }
      i++;
    }
    for (Word mot : this.pieceofword) {
      System.out.println(mot);
    }
    Word w = new Word(word);
    this.pieceofword.add(w);
    if (w.isWord()) return w;
    Word wEmpty = new Word("");
    return wEmpty;
  }

  // Génère toutes les combinaisons possibles avec les chaînes passées en arg
  public ArrayList<String> test(ArrayList<String> t) {
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
          ch = this.test(h);
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
  public ArrayList<String> test2(ArrayList<String> list) {
    ArrayList<String> list2 = new ArrayList<String >();
    ArrayList<String> res = new ArrayList<String >();
    int nb = list.size();
    list2.addAll(list);
    for (int i = 0; i < nb; i++) {
      String f = list2.get(0);
      list2.remove(0);
      res.addAll(this.test(list2));
      if (nb > 0) res.addAll(this.test2(list2));
      list2.add(f);
    }
    return res;
  }

}
