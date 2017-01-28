package game;
import java.util.ArrayList;
import java.util.List;

// Public class which describe a player
public class Player {

	private String name = "Anonymous";
	private List<Word> wordsFound;

	// Constructor
	public Player(String name) {
		this.setName(name);
		this.wordsFound = new ArrayList<Word>();
	}

	// Define player's name
	public void setName(String name) {
		this.name = name;
	}

	// Get player's name
	public String getName() {
		return this.name;
	}

	// Add a word that a player found
	public void addWord(Word word) {
		this.wordsFound.add(word);
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
