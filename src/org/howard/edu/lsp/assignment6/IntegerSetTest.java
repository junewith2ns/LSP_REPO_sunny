package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for {@link IntegerSet}.
 * Covers typical behavior and edge cases for each public method.
 */
public class IntegerSetTest {

  private IntegerSet make(int... vals) {
    IntegerSet s = new IntegerSet();
    for (int v : vals) s.add(v);
    return s;
  }

  @Test
  public void testClearAndLengthAndIsEmpty() {
    IntegerSet s = make(1, 2, 3);
    assertEquals(3, s.length());
    assertFalse(s.isEmpty());

    s.clear();
    assertEquals(0, s.length());
    assertTrue(s.isEmpty());
  }

  @Test
  public void testAddPreventsDuplicates() {
    IntegerSet s = new IntegerSet();
    s.add(5);
    s.add(5);  // duplicate ignored
    s.add(6);
    assertEquals(2, s.length());
    assertTrue(s.contains(5));
    assertTrue(s.contains(6));
  }

  @Test
  public void testRemovePresentAndAbsent() {
    IntegerSet s = make(1, 2, 3);
    s.remove(2);                 // present
    assertEquals(2, s.length());
    assertFalse(s.contains(2));

    s.remove(99);                // absent: no effect
    assertEquals(2, s.length());
  }

  @Test
  public void testContains() {
    IntegerSet s = make(10, 20);
    assertTrue(s.contains(10));
    assertFalse(s.contains(30));
  }

  @Test
  public void testLargestAndSmallest() {
    IntegerSet s = make(4, 9, -1, 9);
    assertEquals(9, s.largest());
    assertEquals(-1, s.smallest());
  }

  @Test
  public void testLargestThrowsOnEmpty() {
    IntegerSet s = new IntegerSet();
    assertThrows(IllegalStateException.class, s::largest);
  }

  @Test
  public void testSmallestThrowsOnEmpty() {
    IntegerSet s = new IntegerSet();
    assertThrows(IllegalStateException.class, s::smallest);
  }

  @Test
  public void testEqualsOrderIndependent() {
    IntegerSet a = make(1, 2, 3);
    IntegerSet b = make(3, 2, 1);
    IntegerSet c = make(1, 2);
    assertTrue(a.equals(b));
    assertFalse(a.equals(c));
    assertFalse(a.equals("not a set"));
  }

  @Test
  public void testUnionMutatesThisOnly() {
    IntegerSet a = make(1, 2);
    IntegerSet b = make(2, 3, 4);
    a.union(b);
    assertEquals("[1, 2, 3, 4]", a.toString());
    // other should remain unchanged
    assertEquals("[2, 3, 4]", b.toString());
  }

  @Test
  public void testIntersectTypicalAndWithSelf() {
    IntegerSet a = make(1, 2, 3);
    IntegerSet b = make(2, 3, 4);
    a.intersect(b);
    assertTrue(a.equals(make(2, 3)));

    // with self should keep same contents
    b.intersect(b);
    assertTrue(b.equals(make(2, 3, 4)));
  }

  @Test
  public void testIntersectDisjointResultsEmpty() {
    IntegerSet a = make(1, 2);
    IntegerSet b = make(3, 4);
    a.intersect(b);
    assertTrue(a.isEmpty());
  }

  @Test
  public void testDiffRemovesElementsInOther() {
    IntegerSet a = make(1, 2, 3, 4);
    IntegerSet b = make(2, 4, 6);
    a.diff(b); // a \ b
    assertTrue(a.equals(make(1, 3)));
  }

  @Test
  public void testComplementBecomesOtherMinusThis() {
    IntegerSet a = make(1, 2, 3);
    IntegerSet b = make(2, 3, 4, 5);
    a.complement(b);  // becomes (b \ a) = {4,5}
    assertTrue(a.equals(make(4, 5)));
    // b unchanged
    assertTrue(b.equals(make(2, 3, 4, 5)));
  }

  @Test
  public void testToStringFormat() {
    IntegerSet s = make(3, 1, 2);
    // Accepts backing order; verify bracketed, comma-separated format
    String str = s.toString();
    assertTrue(str.startsWith("[") && str.endsWith("]"));
    assertTrue(str.contains("1"));
  }
}
