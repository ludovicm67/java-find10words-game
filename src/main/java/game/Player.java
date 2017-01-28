package game;
import java.util.ArrayList;
import java.util.List;

// Public class which describe a player
public class Player {

	protected String name;
	protected List<Word> wordsFound;

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

	// Add a word that a player found
	public void addWord(Word word) {
		this.wordsFound.add(word);
	}

	// remove word to a player
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
