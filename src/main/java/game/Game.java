package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Public class which describe a the game
public class Game {

	private List<Player> players;
	private List<Character> pot;

	// Constructor
	public Game() {
		this.players = new ArrayList<Player>();
		this.pot = new ArrayList<Character>();
	}

	// Initialize a new game
	public void start() {
		System.out.print("How many players ? ");
		Scanner sc = new Scanner(System.in);
		int nbPlayers = sc.nextInt();
		for (int i = 0; i < nbPlayers; i++) {
			System.out.print("Enter player " + (i+1) + "'s name : ");
			String name = sc.next();
			Player p = new Player(name);
			this.addPlayer(p);
			this.pickChar(1);
		}
		while(!this.hasSomeoneWon()) {
			for (Player p : this.players) {
				this.play(p);
				this.printCurrentState();
			}
		}
	}

	// Verify the word of player
	public void verify(Word word, Player p) {
	  if (word.isWord()) {
		if (this.verifyFindWord(word, p)) {
			System.out.println("Sorry, the word has been already found.");
		} else if (!this.verifyLetters(word.getWord())) {
			System.out.println("Sorry, some letters aren't in the pot.");
		} else if (this.charInString('-', word.getWord())) {
				System.out.println("Good job ! You found two words at once !");
				this.removeLetters(this.doubleWords(word)[0]);
				this.removeLetters(this.doubleWords(word)[1]);
				Word word1 = new Word(this.doubleWords(word)[0]);
				Word word2 = new Word(this.doubleWords(word)[1]);
				p.addWord(word1);
				p.addWord(word2);
				this.pickChar(1);
		} else if (this.wordInWord(word, p)) {
			System.out.println("Good job ! You found a word and you stole a word from a player !");
			this.removeLetters(word.getWord());
			p.addWord(word);
			this.pickChar(1);
		} else {
			System.out.println("Good job !");
			this.removeLetters(word.getWord());
			p.addWord(word);
			this.pickChar(1);
		}
	  } else System.out.println("Sorry, isn't a word.");
	}

	// remove letters in pot
	public void removeLetters(String chain) {
		for (int i = 0; i < chain.length(); i++) {
			int nb = (int) (chain.charAt(i));
			char c = (char) (nb - 32);
			if (verifyCharInPot(c)) {
				this.pot.remove(this.pot.indexOf(c));
			}
		}
	}

	// Verify if char in the pot
	public boolean verifyCharInPot(char c) {
		for (Character letter : this.pot) {
			if (letter == c) return true;
			else if (c == '-') return true;
		}
		return false;
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
		System.out.println("There are currently in the pot :");
		for (Character c : this.pot) System.out.print(" " + c);
		System.out.println("\nDo you want to do a word ? (yes/no)");
		String answer = rep.next();
		if (answer.equals("yes")) {
			this.pickChar(2);
			System.out.print("Please write the word : ");
			String w = rep.next();
			w = w.toLowerCase();
			Word word = new Word(w);
			this.verify(word, p);
		} else if (answer.equals("no")) {
			this.pickChar(2);
			System.out.println("Next turn.");
		} else this.play(p);
	}

	// test if a char is in a string
	public boolean charInString(char c, String chain) {
		int i;
		for (i=0; i<chain.length(); i++) {
			if(c == chain.charAt(i)) return true;
		}
		return false;
	}

	// decompose un mot composé en deux
	public String[] doubleWords(Word w) {
		String chain = w.getWord();
		String chain1 = "";
		String chain2 = "";
		if (this.charInString('-', chain)) {
			int t = chain.indexOf('-');
			for (int i=0; i<t; i++) {
				chain1 += chain.charAt(i);
			}
			for (int j=t+1; j<chain.length(); j++) {
				chain2 += chain.charAt(j);
			}
		}
		String[] tab = {chain1, chain2};
		return tab;
	}

	//test if word of one player is in word of other player
	public boolean wordInWord(Word w, Player p) {
		for (Player player : this.players) {
			if (player != p) {
				for (Word word : player.wordsFound) {
					if (w.getWord().length() > word.getWord().length()) {
						if (w.getWord().contains(word.getWord())) {
							player.removeWord(word);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// Management of game turns
	public void round() {
		for(Player p : this.players) this.play(p);
	}

	// Add a player
	public void addPlayer(Player p) {
		this.players.add(p);
	}

	// Number of players
	public int nbPlayers() {
		return this.players.size();
	}

	// Number of characters in pot
	public int nbChars() {
		return this.pot.size();
	}

	// Pick a random char (A-Z) and put it in the pot
	public void pickChar(int nb) {
		while (nb > 0) {
			Random r = new Random();
			char c = (char) (65 + r.nextInt(26));
			this.pot.add(c);
			nb--;
		}
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

}
