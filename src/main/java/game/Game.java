package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Public class which describe a the game
public class Game {

	public List<Player> players;
	public List<Character> pot;

	// Constructor
	public Game() {
		this.players = new ArrayList<Player>();
		this.pot = new ArrayList<Character>();
	}

	// Initialize a new game
	public void start() {
		System.out.println("How many players ?");
		Scanner sc = new Scanner(System.in);
		int nbPlayers = sc.nextInt();
		for (int i = 0; i < nbPlayers; i++) {
			System.out.println("Entrez le nom du joueur " + (i+1) + " :");
			String name = sc.next();
			Player p = new Player(name);
			this.addPlayer(p);
			this.pickChar(1);
		}
		while(!this.hasSomeoneWon()) {
			for (Player p : this.players) {
				this.play(p);
				this.debug();
			}
		}
	}

	// Verify the word of player
	public void verify(Word word, Player p) {
	  if (word.is_word()) {
		if (this.verifyFindWord(word, p)) {
			System.out.println("Sorry, the word has been already found.");
		} else if (!(this.verifyLetters(word.getWord()))) {
			System.out.println("Sorry, some letters aren't in the pot.");
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
		int i;
		int j = 0;
		for (i = 0; i < chain.length(); i++) {
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
			if (letter == c) {
				return true;
			}
		}
		return false;
	}

	// Verify if letters of word are in the pot
	public boolean verifyLetters(String chain) {
		int i;
		int j = 0;
		for (i = 0; i < chain.length(); i++) {
			int nb = (int) (chain.charAt(i));
			char c = (char) (nb - 32);
			if(!(verifyCharInPot(c))) return false;
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
		this.pickChar(2);
		System.out.println("C'est au tour de " + p.getName() + ".");
		System.out.println("There are currently in the pot :");
		for (Character c : this.pot) System.out.print(c + " ");
		System.out.println();
		System.out.println("Do you want to do a word ? (yes/no)");
		String answer = rep.next();
		if (answer.equals("yes")) {
			System.out.println("Write the word please.");
			String w = rep.next();
			w = w.toLowerCase();
			Word word = new Word(w);
			this.verify(word, p);
		}
		else if(answer.equals("no")) System.out.println("Tour suivant.");
		else this.play(p);
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

	// Number of pot
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
	public void debug() {
		System.out.println("\n======DEBUG======");
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
	}

}
