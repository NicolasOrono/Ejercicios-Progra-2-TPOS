package dictionary;
import list.Lista;

public interface Map<K, V> {
    int size();
    boolean isEmpty();
    V get(K key);
    V put(K key, V value);
    Lista<Entry<K, V>> entries();
}