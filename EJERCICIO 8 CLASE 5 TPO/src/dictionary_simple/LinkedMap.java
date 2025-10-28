package dictionary_simple;

import list.Lista;
import list.LinkedLista;

public class LinkedMap<K, V> implements Map<K, V> {

    private Lista<Entry<K, V>> list;

    public LinkedMap() {
        list = new LinkedLista<>();
    }

    private Entry<K, V> findKey(K key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    @Override
    public V put(K key, V value) {
        Entry<K, V> entry = findKey(key);
        if (entry != null) {
            V oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        } else {
            list.add(new Entrada<>(key, value));
            return null;
        }
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = findKey(key);
        return (entry != null) ? entry.getValue() : null;
    }

    @Override
    public Lista<Entry<K, V>> entries() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}