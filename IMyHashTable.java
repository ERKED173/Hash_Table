import java.util.ArrayList;

public interface IMyHashTable<K, V> {

    /** Returns the value V for a given key K.
     *  @param key - key that connected with value
     *  @return appropriate value for a given key.*/
    V get (K key);

    /** Removes value V from table and returns it for a given key K.
     *  @param key - key that connected with value
     *  @return appropriate value for a given key.*/
    V remove (K key);

    /** Adds value V to the table and returns it for a given key K.
     *  @param key - key that connected with value
     *  @param value - value that connected with key.*/
    void put (K key, V value);


    /** Returns array of all keys.
     *  @return array of all keys.*/
    ArrayList<K> getKeySet();

    /** Returns array of all values.
     *  @return array of all values.*/
    ArrayList<V> getValues();

    /** Returns array of all entries.
     *  @return array of all entries.*/
    MyEntrySet<K, V> getEntrySet();

    /** Returns size of hash table.
     *  @return size of hash table.*/
    long size ();

    /** Returns whether hash table is empty.
     *  @return is hash table empty of not.*/
    boolean isEmpty ();


    /** Returns length of max probabing sequence.
     *  @return length of max probabing sequence.*/
    int getMaxProbabingSequenceLength ();

}
