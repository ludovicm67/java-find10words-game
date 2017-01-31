package com.game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pot {

  private List<Character> content;

  // Constructor
  public Pot() {
    this.content = new ArrayList<Character>();
  }

  // Number of chars in the pot
  public int size() {
    return this.content.size();
  }

  // Add a char in the pot
  public void add(Character c) {
    this.content.add(c);
  }

  // Add many chars in the pot
  public void addAll(List<Character> c) {
    this.content.addAll(c);
  }

  // Remove letters from the pot
  public void remove(String chain) {
    for (int i = 0; i < chain.length(); i++) {
      char c = Character.toUpperCase(chain.charAt(i));
      if (this.contains(c)) {
        this.content.remove(this.content.indexOf(c));
      }
    }
  }

  // Get content of the pot
  public List<Character> getContent() {
    return this.content;
  }

  // Get a random char
  public char randomChar() {
    Random r = new Random();
    int nb = r.nextInt(37);
    if (nb < 26) return (char) (65 + nb);
    else if(nb < 30) return 'E';
    else if(nb < 33) return 'A';
    else if(nb < 35) return 'I';
    else if(nb < 36) return 'O';
    else return 'U';
  }

  // Pick a random char (A-Z) and put it in the pot
  public char pickChar() {
    char c = this.randomChar();
    this.add(c);
    return c;
  }

  // Pick n random char (A-Z) and put them in the pot
  public void pickChar(int n) {
    while (n > 0) {
      this.pickChar();
      n--;
    }
  }

  // Verify if a char is contained in the pot
  public boolean contains(Character c) {
    return this.content.contains(c) || c == '-';
  }

  // Print content of the pot
  public void print() {
    System.out.println("There are currently in the pot :");
    for (Character c : this.content) System.out.print(" " + c);
    System.out.println();
  }


}
