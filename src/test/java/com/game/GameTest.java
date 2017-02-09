package com.game;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

  @Test
  public void testNbPlayers() {
    Game g = new Game();
    assertThat(g.nbPlayers()).isEqualTo(0);
  }

  @Test
  public void testAddGlados() {
    Game g = new Game();
    assertThat(g.nbPlayers()).isEqualTo(0);
    g.addGlados();
    assertThat(g.nbPlayers()).isEqualTo(1);
  }

  @Test
  public void testAddR2d2() {
    Game g = new Game();
    assertThat(g.nbPlayers()).isEqualTo(0);
    g.addR2d2();
    assertThat(g.nbPlayers()).isEqualTo(1);
  }

  @Test
  public void testAddPlayer() {
    Game g = new Game();
    Player p = new Player();
    assertThat(g.nbPlayers()).isEqualTo(0);
    g.addPlayer(p);
    assertThat(g.nbPlayers()).isEqualTo(1);
  }

  @Test
  public void testCharInString() {
    Game g = new Game();
    assertThat(g.charInString('e', "test")).isTrue();
    assertThat(g.charInString('z', "test")).isFalse();
  }

  @Test
  public void testTestPieceofword() {
    Game g = new Game();
    Word w = new Word("bonjour"), w2 = new Word("bonjooour");
    assertThat(g.testPieceofword(w, "bonjour")).isEqualTo(w.getWord());
    assertThat(g.testPieceofword(w2, "bonjour")).isEqualTo(w.getWord());
  }

  @Test
  public void testMakeword() {
    Game g = new Game();
    Pot p = new Pot();
    p.add('B');
    p.add('O');
    p.add('N');
    p.add('J');
    p.add('O');
    p.add('U');
    p.add('R');
    Word w = g.makeWord(p);
    assertThat(w.getWord()).isEqualTo("bonjour");

    p = new Pot();
    p.add('B');
    p.add('O');
    p.add('N');
    p.add('J');
    p.add('O');
    p.add('O');
    p.add('O');
    p.add('U');
    p.add('R');
    w = g.makeWord(p);
    assertThat(w.getWord()).isEqualTo("bonjour");
  }

  @Test // indirectly test : verifyFoundDoubleWords() and verifyFoundWord()
  public void testVerify() {
    Game g = new Game();
    Player p = new Player();
    g.addPlayer(p);
    Word w = new Word("bonjour"), w2 = new Word("bonjooour"), w3 = new Word("anti-vol");
    assertThat(g.verify(w, p)).isTrue();
    assertThat(g.verify(w2, p)).isFalse();
    assertThat(g.verify(w3, p)).isTrue();
  }

}
