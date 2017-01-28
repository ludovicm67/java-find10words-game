package game;
import java.io.InputStream;
import java.util.Scanner;

public class Word {

  String word;
  String fileName = "file/dico.txt";

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
    ClassLoader classloader = App.class.getClassLoader();
    InputStream fileStream = classloader.getResourceAsStream(this.fileName);
    try (Scanner scanner = new Scanner(fileStream)) {
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
