package dictionary_simple;
import list.Lista;

public interface Map<K, V> {
    V put(K key, V value);
    V get(K key);
    int size();
    boolean isEmpty();
    Lista<Entry<K, V>> entries();

}