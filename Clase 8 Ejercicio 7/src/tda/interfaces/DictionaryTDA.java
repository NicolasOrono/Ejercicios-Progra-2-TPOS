package tda.interfaces;

public interface DictionaryTDA<K, V extends Comparable<V>> {


    void put(K k, V v);


    ListaTDA<V> get(K k);


    ListaTDA<V> remove(K k);


    V remove(K k, V v);

    boolean isEmpty();
    int size();
}