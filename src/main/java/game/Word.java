package game;
import java.util.Scanner;

public class Word {

  String word;

  // constructor
  public Word(String word) {
    this.word = word;
  }

  // get the word
  public String getWord() {
    return this.word;
  }

  // test if a chain is a word
  public boolean is_word() {
    try (Scanner scanner = new Scanner(App.class.getClassLoader().getResourceAsStream("file/dico.txt"))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        line = line.trim();
        if (line.equals(this.word.trim())) return true;
      }
      scanner.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
    return false;
  }

}
