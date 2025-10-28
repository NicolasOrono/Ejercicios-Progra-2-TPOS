package dictionary_multiple;
import list.Lista;

public interface Dictionary<K, V> {
    void put(K key, V value);
    Lista<V> get(K key);
    Lista<V> remove(K key);
    boolean remove(K key, V value);
    Lista<K> keys();
    int size();
    boolean isEmpty();
}