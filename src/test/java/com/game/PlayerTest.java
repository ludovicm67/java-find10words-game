package com.game;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

  @Test
  public void testGetName() {
    Player p = new Player();
    assertThat(p.getName()).isEqualTo("Anonymous");
    p = new Player("John");
    assertThat(p.getName()).isEqualTo("John");
  }

  @Test // Checks addWord() and getWords()
  public void testAddGetWords() {
    Player p = new Player();
    Word w1 = new Word("bonjour"), w2 = new Word("monde");
    p.addWord(w1);
    p.addWord(w2);
    assertThat(p.getWords().get(0).getWord()).isEqualTo("bonjour");
    assertThat(p.getWords().get(1).getWord()).isEqualTo("monde");
  }

  @Test // Need testAddWord() to be OK
  public void testNbWords() {
    Player p = new Player();
    assertThat(p.nbWords()).isEqualTo(0);
    Word w1 = new Word("bonjour"), w2 = new Word("monde");
    p.addWord(w1);
    assertThat(p.nbWords()).isEqualTo(1);
    p.addWord(w2);
    assertThat(p.nbWords()).isEqualTo(2);
  }

}
