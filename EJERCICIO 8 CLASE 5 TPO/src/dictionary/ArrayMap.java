package dictionary;
import list.Lista;
import list.ArrayLista;

public class ArrayMap<K, V> implements Map<K, V> {

    private Entry<K, V>[] entries;
    private int size;
    private static final int DEFAULT_CAPACITY = 100;

    @SuppressWarnings("unchecked")
    public ArrayMap() {
        entries = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        size = 0;
    }


    private int findKey(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V get(K key) {
        int index = findKey(key);
        if (index != -1) {
            return entries[index].getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = findKey(key);

        if (index != -1) {

            V oldValue = entries[index].getValue();

            entries[index] = new Entrada<>(key, value);
            return oldValue;
        } else {

            if (size < DEFAULT_CAPACITY) {
                entries[size] = new Entrada<>(key, value);
                size++;
                return null;
            } else {

                System.out.println("Error: Diccionario lleno.");
                return null;
            }
        }
    }

    @Override
    public Lista<Entry<K, V>> entries() {
        Lista<Entry<K, V>> listaEntries = new ArrayLista<>();
        for (int i = 0; i < size; i++) {
            listaEntries.add(entries[i]);
        }
        return listaEntries;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "{ }";
        String s = "{\n";
        for(int i = 0; i < size; i++) {
            s += "  " + entries[i].toString();
            if (i < size - 1) s += ",\n";
        }
        s += "\n}";
        return s;
    }
}