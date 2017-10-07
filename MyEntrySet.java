import java.util.ArrayList;
import java.util.Iterator;

/** Implementation of entry set.*/
public class MyEntrySet<K, V> implements Iterable<MyEntry<K, V>> {

    private ArrayList<MyEntry<K, V>> set;
    /* I use Iterator for easy access to entries.*/
    private Iterator<MyEntry<K, V>> iterator;
    private int index = 0;

    MyEntrySet () {
        set = new ArrayList<>();
        iterator = new Iterator<MyEntry<K, V>>() {

            @Override
            public boolean hasNext() {
                if (index + 1 <= set.size()) {
                    return true;
                } else {
                    index = 0;
                    return false;
                }
            }

            @Override
            public MyEntry<K, V> next() {
                return set.get(index++); // It is need to use ++ here and it works without errors
            }
        };
    }

    void add (MyEntry<K, V> entry) {
        set.add(entry);
    }

    /** Searches for appropriate entry and return it by given key.
     *  @param key given key.
     *  @return entry for a given key.*/
    MyEntry<K, V> get (K key) {
        for (MyEntry<K, V> entry : set)
            if (entry.getKey().equals(key))
                return entry;
        return null;
    }

    /** Searches for appropriate entry and remove it from entry set by given key.
     *  @param key given key.*/
    void remove (K key) {
        for (int i = 0; i < set.size(); i++)
            if (set.get(i).getKey().equals(key))
                set.remove(i);
    }

    /** Returns iterator of this entry set.
     *  @return iterator.*/
    @Override
    public Iterator<MyEntry<K, V>> iterator() {
        return iterator;
    }

    ArrayList<MyEntry<K, V>> getSet () {
        return set;
    }

}
