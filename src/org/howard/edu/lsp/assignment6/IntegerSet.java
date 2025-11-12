package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that reps a set of integers.
 * <p>
 * Uses an ArrayList internally but makes sure no duplicate values exist.
 * All methods that modify the set (eg add, union, etc.) change the current object.
 */
public class IntegerSet  {
  /** Stores all the integers in the set. Duplicates are not allowed. */
  private List<Integer> set = new ArrayList<Integer>();

  /**
   * Removes every element from the set so its empty.
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
   * Overrides the equals method from the Object class.
   * @param o another object to compare
   *  @return true if both sets have the same values; false otherwise
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
   * Checks if a specific number exists in the set.
   * @param value the number to look for
   * @return true if found, false if not
   */
  public boolean contains(int value) {
    return set.contains(value);
  }

  /**
   * Returns largest number in the set.
   * @return largest integer in the set
   * @throws IllegalStateException if the set is empty
   */
  public int largest()  {
    if (set.isEmpty()) {
      throw new IllegalStateException("largest() called on empty set");
    }
    return Collections.max(set);
  }

  /**
   * Returns smallest number in the set.
   * @return smallest integer in the set
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
    // REQUIRED FIX: handle union with self safely
    if (other == this) return;

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
   * Returns a string showing all elements in the set.
   * Example: [1, 2, 3]
   * The order matches how the elements are stored internally.
   */
  @Override
  public String toString() {
    return set.toString();
  }
}
