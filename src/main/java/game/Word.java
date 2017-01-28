package game;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Word {

  String word;

  // constructor
  public Word() {
    this.word = "??";
  }

  // constructor
  public Word(String chain) {
    if (chain.length() > 1) {
      this.word = chain;
    } else {
      this.word = "??";
    }
  }

  // get the word
  public String getWord() {
    return this.word;
  }

  // test if a chain is a word
  public boolean is_word() {
    try (BufferedReader buffer = new BufferedReader(new FileReader("/home/eloise/jeux-java/java2/dico.txt"))) {
      String line = buffer.readLine();
      while (line != null) {
        line = line.trim();
        if (line.equals(this.word)) {
          return true;
        }
        line = buffer.readLine();
      }
    } catch(IOException e) {}
    return false;
  }

}
