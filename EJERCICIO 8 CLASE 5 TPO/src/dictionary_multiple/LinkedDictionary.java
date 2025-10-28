package dictionary_multiple;

import list.Lista;
import list.LinkedLista;

public class LinkedDictionary<K, V> implements Dictionary<K, V> {

    private static class DNode<K, V> {
        K key;
        Lista<V> values;
        DNode<K, V> next;

        DNode(K key, V value, DNode<K, V> next) {
            this.key = key;
            this.values = new LinkedLista<>();
            this.values.add(value);
            this.next = next;
        }
    }

    private DNode<K, V> head;
    private int size;

    public LinkedDictionary() {
        head = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    // Método auxiliar para buscar el nodo de una clave
    private DNode<K, V> findKey(K key) {
        DNode<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        DNode<K, V> node = findKey(key);
        if (node != null) {
            // Clave (DNI) ya existe: agregar la nota a su lista
            node.values.add(value);
        } else {
            // Clave no existe: crear un nuevo nodo y agregarlo al ppio
            head = new DNode<>(key, value, head);
            size++;
        }
    }

    @Override
    public Lista<V> get(K key) {
        DNode<K, V> node = findKey(key);
        return (node != null) ? node.values : null;
    }

    @Override
    public boolean remove(K key, V value) {
        DNode<K, V> node = findKey(key);
        if (node != null) {
            return node.values.remove(value);
        }
        return false;
    }

    @Override
    public Lista<V> remove(K key) {
        if (head == null) return null;

        DNode<K, V> current = head;
        DNode<K, V> prev = null;

        while(current != null && !current.key.equals(key)) {
            prev = current;
            current = current.next;
        }

        if (current == null) return null; // No se encontró

        // Desconectar el nodo
        if (prev == null) {
            head = current.next;
        } else {
            prev.next = current.next;
        }
        size--;
        return current.values;
    }

    @Override
    public Lista<K> keys() {
        Lista<K> keyList = new LinkedLista<>();
        DNode<K, V> current = head;
        while (current != null) {
            keyList.add(current.key);
            current = current.next;
        }
        return keyList;
    }

    @Override
    public String toString() {
        String s = "{\n";
        DNode<K, V> current = head;
        while (current != null) {
            s += "  DNI: " + current.key + " -> Notas: " + current.values + "\n";
            current = current.next;
        }
        s += "}";
        return s;
    }
}