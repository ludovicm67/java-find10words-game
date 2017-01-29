package com.game;
import java.io.InputStream;
import java.util.Scanner;

public class Word {

  private String word;
  private String fileName = "file/dico.txt";

  // constructors
  public Word(String word) {
    this.word = word;
  }
  public Word() {
    this.word = "";
  }

  // get the word
  public String getWord() {
    return this.word;
  }

  // set the word
  public void setWord(String w) {
    this.word = w;
  }

  // test if a chain is a word
  public boolean isWord() {
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

  // decompose un mot composé en deux
  public Word[] doubleWords() {
    String chain = this.getWord();
    String chain1 = "";
    String chain2 = "";
    if (chain.contains("-")) {
      int t = chain.indexOf('-');
      for (int i=0; i<t; i++) {
        chain1 += chain.charAt(i);
      }
      for (int j=t+1; j<chain.length(); j++) {
        chain2 += chain.charAt(j);
      }
    }
    Word[] tab = {
      new Word(chain1),
      new Word(chain2),
    };
    return tab;
  }

}
