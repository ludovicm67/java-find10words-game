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

  @Test // Needs getName() to be OK
  public void testSetName() {
    Player p = new Player();
    String name = "John Doe";
    p.setName(name);
    assertThat(p.getName()).isEqualTo(name);
  }

  @Test // test setHuman() and isHuman()
  public void testHuman() {
    Player p = new Player();

    // human by default
    assertThat(p.isHuman()).isTrue();

    // test by setting manually
    p.setHuman();
    assertThat(p.isHuman()).isTrue();
  }

  @Test // test setIA() and isIA()
  public void testIA() {
    Player p = new Player();

    // human by default
    assertThat(p.isIA()).isFalse();

    // test by setting manually
    p.setIA();
    assertThat(p.isIA()).isTrue();
  }

  @Test // Checks addWord(), removeWord() and getWords()
  public void testAddGetWords() {
    Player p = new Player();
    Word w1 = new Word("bonjour"), w2 = new Word("monde");
    p.addWord(w1);
    p.addWord(w2);
    assertThat(p.getWords().get(0).getWord()).isEqualTo("bonjour");
    assertThat(p.getWords().get(1).getWord()).isEqualTo("monde");

    // Test remove
    assertThat(p.getWords().size()).isEqualTo(2);
    p.removeWord(w1);
    assertThat(p.getWords().size()).isEqualTo(1);
    assertThat(p.getWords().get(0).getWord()).isEqualTo("monde");
  }

  @Test
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
