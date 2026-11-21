package set;

import java.util.Iterator;
import map.HashMap;
import map.Map;

/**
 * A hash-based implementation of the Set ADT, backed by the course HashMap.
 * The keys of the map are the elements of the set.
 *
 * @param <T> the type of elements in this set.
 */
public class HashSet<T> implements Set<T> {

  private Map<T, Boolean> entries;  // the keys are the elements; the values are not used

  public HashSet() {
    entries = new HashMap<>();
  }

  @Override
  public boolean add(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    return entries.put(item, true);
  }

  @Override
  public boolean remove(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    return entries.remove(item);
  }

  @Override
  public boolean contains(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    return entries.containsKey(item);
  }

  @Override
  public int size() {
    return entries.size();
  }

  @Override
  public Iterator<T> iterator() {
    return entries.iterator();
  }
}
