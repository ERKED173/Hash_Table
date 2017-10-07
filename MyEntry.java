/** Implementation of entry (in my point of view).*/
class MyEntry<K, V> {

    private K key;
    private V value;

    MyEntry(K key, V value) {
        this.key   = key;
        this.value = value;
    }

    K getKey()        { return key;   }
    V getValue()      { return value; }

    void setKey   (K key)   {this.key = key;}
    void setValue (V value) {this.value = value;}

}