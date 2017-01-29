package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordTest {

  @Test
  public void testGetWord() {
    String str = "test";
    Word w = new Word(str);
    assertThat(w.getWord()).isEqualTo(str);
  }

  @Test // Need testGetWord() to be OK
  public void testSetWord() {
    Word w = new Word();
    String str = "test";
    w.setWord(str);
    assertThat(w.getWord()).isEqualTo(str);
  }

  @Test
  public void testIsWord() {
    Word w = new Word("test");
    assertThat(w.isWord()).isTrue();
    w = new Word("test-false");
    assertThat(w.isWord()).isFalse();
  }

  @Test // Need testGetWord() to be OK
  public void testDoubleWords() {
    Word w = new Word("bonjour-monde");
    Word[] ws = w.doubleWords();
    assertThat(ws[0].getWord()).isEqualTo("bonjour");
    assertThat(ws[1].getWord()).isEqualTo("monde");
  }

}
