package com.game;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PotTest {

  @Test // test size(), pickChar()
  public void testSizeAndPick() {
    Pot p = new Pot();
    assertThat(p.size()).isEqualTo(0);
    p.pickChar();
    assertThat(p.size()).isEqualTo(1);
    p.pickChar();
    assertThat(p.size()).isEqualTo(2);
    p.pickChar(42);
    assertThat(p.size()).isEqualTo(44);
  }

  @Test // test add(), addAll(), remove(), getContent(), contains()
  public void testAddRemoveContent() {
    Pot p = new Pot();
    assertThat(p.size()).isEqualTo(0);
    assertThat(p.contains('A')).isFalse();
    p.add('A');
    assertThat(p.contains('A')).isTrue();
    assertThat(p.size()).isEqualTo(1);
    assertThat(p.getContent().get(0)).isEqualTo('A');

  }

  @Test
  public void testRandomChar() {
    Pot p = new Pot();
    for (int i = 0; i < 1000; i++) {
      assertThat((int) p.randomChar()).isBetween(65, 90);
    }
  }

}
