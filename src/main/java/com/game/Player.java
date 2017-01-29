package com.game;
import java.util.ArrayList;
import java.util.List;

// Public class which describe a player
public class Player {

  private String name;
  private List<Word> wordsFound;
  private boolean isHuman = true;

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
