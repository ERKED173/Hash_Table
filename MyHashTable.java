import java.util.ArrayList;

public class MyHashTable<K, V> implements IMyHashTable<K, V> {

    private ArrayList<K> keySet;
    private ArrayList<V> values;
    private MyEntrySet<K, V> entrySet;
    private boolean [] isRemoved;
    private int size = 20011; // Prime number
    private int maxProbabingSequenceLength = 1;

    MyHashTable () {
        keySet   = new ArrayList<>(size);
        values   = new ArrayList<>(size);
        entrySet = new MyEntrySet<>();
        for (int i = 0; i < size; i++) {
            keySet.add(null);
            values.add(null);
        }

        isRemoved = new boolean[size];
        for (int i = 0; i < isRemoved.length; i++) isRemoved[i] = true;
    }

    @Override
    public V get(K key) {
        int index = hashCode(key, 0);
        long iterator = 1;
        while (keySet.get(index) != null) {
            if (!isRemoved[index]) {
                if (keySet.get(index).equals(key)) {
                    return values.get(index);
                }
            }
            index = hashCode(key, iterator);
            iterator++;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = hashCode(key, 0);
        long iterator = 1;
        while (keySet.get(index) != null) {
            if (isRemoved[index]) {
                index = hashCode(key, iterator);
                iterator++;
            } else {
                if (keySet.get(index).equals(key)) {
                    isRemoved[index] = true;
                    entrySet.remove(keySet.get(index));
                    keySet.set(index, null);
                    size--;
                } else {
                    index = hashCode(key, iterator);
                    iterator++;
                }
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if (entrySet.getSet().size() >= size) {
            int oldSize = size;
            size = nextPrime(size * 2);
            for (int i = oldSize; i < size; i++) {
                keySet.add(null);
                values.add(null);
            }
            boolean[] temp = new boolean[size];
            System.arraycopy(isRemoved, 0, temp, 0, oldSize);
            for (int i = oldSize; i < size; i++) temp[i] = true;
            isRemoved = temp;
        } else {
            int index = hashCode(key, 0);
            long iterator = 1;
            if (keySet.get(index) != null && keySet.get(index).equals(key)) {
                values.set(index, value);
                keySet.set(index, key);
                entrySet.get(key).setValue(value);
                isRemoved[index] = false;
                return;
            }
            while (keySet.get(index) != null && !isRemoved[index]) {
                index = hashCode(key, iterator);
                iterator++;
            }
            if (iterator > maxProbabingSequenceLength) maxProbabingSequenceLength = (int) iterator;
            keySet.set(index, key);
            values.set(index, value);
            entrySet.add(new MyEntry<>(keySet.get(index), values.get(index)));
            isRemoved[index] = false;
        }
    }

    @Override
    public ArrayList<K> getKeySet() {
        return keySet;
    }

    @Override
    public ArrayList<V> getValues() {
        return values;
    }

    @Override
    public MyEntrySet<K, V> getEntrySet() {
        return entrySet;
    }

    @Override
    public long size () {
        return size;
    }

    @Override
    public boolean isEmpty () {
        return size == 0;
    }

    @Override
    public int getMaxProbabingSequenceLength() {
        return maxProbabingSequenceLength;
    }

    /** Returns hash code for key K. It uses a quadratic probing and also
     *  it uses the standard Java-hashCode function (because designing a hash function is a black art).
     *  @param key - object to be transformed to hashcode.
     *  @param iterator - iterator of quadratic probing.
     *  @return hashcode for given object.*/
    private int hashCode (K key, long iterator) {
        return Math.abs((key.hashCode() + (int)(iterator * iterator)) % size);
    }

    /** Returns next nearest prime number to the given number.
     *  @param n - given number.
     *  @return nearest prime number to n.*/
    private int nextPrime (int n) {
        n++;
        boolean primeFound = false;
        while (!primeFound) {
            primeFound = true;
            for (int i = 2; i < Math.sqrt(n) + 1; i++) {
                if (n % i == 0){
                    n++;
                    primeFound = false;
                    break;
                }
            }
        }
        return n;
    }

}