
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Scanner;

// Public class which describe a player
public class Player {

  private String name;
  private List<Word> wordsFound;
  private boolean isHuman = true;
  private List<Word> pieceofword; // for Glados
  private String baseDeDonnees = "~/home/eloise/jeux-java/java2/dico.txt"; // for Glados
  public Game game;

  // Constructors
  public Player(String name) {
    this.name = name;
    this.wordsFound = new ArrayList<Word>();
  }
  public Player() {
    this.name = "Anonymous";
    this.wordsFound = new ArrayList<Word>();
  }

  // Get player's name
  public String getName() {
    return this.name;
  }

  // Set player's name
  public void setName(String name) {
    this.name = name;
  }

  // Set this player to be an human (default)
  public void setHuman() {
    this.isHuman = true;
  }

  // Set this player to be an IA
  public void setIA() {
    this.isHuman = false;
  }

  // Is this player an human ?
  public boolean isHuman() {
    return this.isHuman;
  }

  // Is this player an IA ?
  public boolean isIA() {
    return !this.isHuman;
  }

  // test if a word in the pot is in the dico with this indice
  public boolean searchWordinDico(char l, int indice){
    ClassLoader classloader = App.class.getClassLoader();
    InputStream fileStream = classloader.getResourceAsStream(this.baseDeDonnees);
    try (Scanner scanner = new Scanner(this.baseDeDonnees)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        line = line.trim();
        System.out.println(line);
        if (line.charAt(indice)==l) {
          System.out.println("lol");
          return true;
        }
      }
      scanner.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
    return false;
  }


  // Try to do a word
  public Word makeWord(){
    this.pieceofword = new ArrayList<Word>();
    int i=0;
    String word="";
    for(Character letter : game.pot.content){
      System.out.println("plop");
      if(this.searchWordinDico(letter, i)){
        word+=letter;
        System.out.println(word);
      }
      i++;
    }
    for(Word mot : this.pieceofword){
      System.out.println(mot);
    }
    Word w = new Word(word);
    this.addPieceOfWord(w);
    if(w.isWord()) return w;
    Word wEmpty = new Word("");
    return wEmpty;
  }

  public List<Word> getpieceOfword(){
    return this.pieceofword;
  }

  // Piece of word for Glados
  public void addPieceOfWord(Word w){
    this.pieceofword.add(w);
  }

  // Add a word that a player found
  public void addWord(Word word) {
    this.wordsFound.add(word);
  }

  // Remove word to a player
  public void removeWord(Word w) {
    this.wordsFound.remove(w);
  }

  // Get the list of all words the player founds
  public List<Word> getWords() {
    return this.wordsFound;
  }

  // Get the number of words the player founds
  public int nbWords() {
    return this.wordsFound.size();
  }

}
