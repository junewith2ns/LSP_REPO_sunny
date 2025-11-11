package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A mutable mathematical set of integers backed by an {@link ArrayList}.
 * <p>
 * This class disallows duplicates and supports common set operations.
 * All mutator methods modify the current instance (i.e., {@code this}).
 */
public class IntegerSet  {
  /** Internal storage; duplicates are prevented by method logic. */
  private List<Integer> set = new ArrayList<Integer>();

  /**
   * Clears the internal representation of the set.
   */
  public void clear() {
    set.clear();
  }

  /**
   * Returns the number of elements in the set.
   * @return element count
   */
  public int length() {
    return set.size();
  }

  /**
   * Returns true if the two sets are equal, false otherwise.
   * Two sets are equal if they contain all of the same values in any order.
   * This overrides the equals method from the Object class.
   * @param o another object to compare
   * @return {@code true} when the contents match irrespective of order
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof IntegerSet)) return false;
    IntegerSet other = (IntegerSet) o;
    if (this.length() != other.length()) return false;
    // order-independent content comparison
    return this.set.containsAll(other.set) && other.set.containsAll(this.set);
  }

  /**
   * Returns true if the set contains the given value.
   * @param value integer to check membership of
   * @return membership result
   */
  public boolean contains(int value) {
    return set.contains(value);
  }

  /**
   * Returns the largest item in the set.
   * @return maximum element
   * @throws IllegalStateException if the set is empty
   */
  public int largest()  {
    if (set.isEmpty()) {
      throw new IllegalStateException("largest() called on empty set");
    }
    return Collections.max(set);
  }

  /**
   * Returns the smallest item in the set.
   * @return minimum element
   * @throws IllegalStateException if the set is empty
   */
  public int smallest()  {
    if (set.isEmpty()) {
      throw new IllegalStateException("smallest() called on empty set");
    }
    return Collections.min(set);
  }

  /**
   * Adds an item to the set or does nothing if it is already present.
   * @param item value to add
   */
  public void add(int item) {
    if (!set.contains(item)) {
      set.add(item);
    }
  }

  /**
   * Removes an item from the set or does nothing if not present.
   * @param item value to remove
   */
  public void remove(int item) {
    // remove(Object) removes first occurrence; uniqueness ensures at most one
    set.remove(Integer.valueOf(item));
  }

  /**
   * Set union: modifies this to contain all unique elements that are in this or in {@code other}.
   * @param other the other set (non-null)
   */
  public void union(IntegerSet other) {
    for (Integer v : other.set) {
      if (!this.set.contains(v)) {
        this.set.add(v);
      }
    }
  }

  /**
   * Set intersection: modifies this to contain only elements present in both sets.
   * @param other the other set (non-null)
   */
  public void intersect(IntegerSet other) {
    this.set.retainAll(other.set);
  }

  /**
   * Set difference: modifies this to remove all elements that are found in {@code other}.
   * (i.e., this \ other)
   * @param other the other set (non-null)
   */
  public void diff(IntegerSet other) {
    this.set.removeAll(other.set);
  }

  /**
   * Set complement: modifies this to become (other \ this).
   * @param other the other set (non-null)
   */
  public void complement(IntegerSet other) {
    List<Integer> result = new ArrayList<>(other.set);
    result.removeAll(this.set);
    this.set.clear();
    this.set.addAll(result);
  }

  /**
   * Returns true if the set has no elements.
   * @return emptiness result
   */
  public boolean isEmpty() {
    return set.isEmpty();
  }

  /**
   * Returns a formatted string representation of the set.
   * Format: elements in square brackets, e.g., {@code [1, 2, 3]}.
   * Note: order is the internal iteration order of the backing list.
   * @return string form of the set
   */
  @Override
  public String toString() {
    return set.toString();
  }
}
