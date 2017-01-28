package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordTest {

  @Test
  public void testGetWord() {
    String str = "test";
    Word w = new Word(str);
    assertEquals(w.getWord(), str);
  }

  @Test
  public void testSetWord() {
    Word w = new Word();
    String str = "test";
    w.setWord(str);
    assertEquals(w.getWord(), str);
  }

  @Test
  public void testIsWord() {
    Word w = new Word("test");
    assertTrue(w.isWord());
    w = new Word("test-false");
    assertFalse(w.isWord());
  }

  @Test
  public void testDoubleWords() {
    Word w = new Word("bonjour-monde");
    Word[] ws = w.doubleWords();
    assertEquals(ws[0].getWord(), "bonjour");
    assertEquals(ws[1].getWord(), "monde");
  }

}
