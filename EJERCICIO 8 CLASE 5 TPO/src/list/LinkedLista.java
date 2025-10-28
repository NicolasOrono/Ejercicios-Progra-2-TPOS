package list;

public class LinkedLista<E> implements Lista<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedLista() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null; // O lanzar excepción
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    @Override
    public boolean remove(E element) {
        if (isEmpty()) {
            return false;
        }

        Node<E> current = head;
        Node<E> prev = null;

        // Buscar el elemento
        while (current != null && !current.getElement().equals(element)) {
            prev = current;
            current = current.getNext();
        }

        // Si no se encontró
        if (current == null) {
            return false;
        }

        // Desconectar el nodo
        if (prev == null) {
            // Es el primer elemento
            head = current.getNext();
        } else {
            prev.setNext(current.getNext());
        }

        // Si era el último elemento, actualizar tail
        if (current == tail) {
            tail = prev;
        }

        size--;
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        String s = "[";
        Node<E> current = head;
        while (current != null) {
            s += current.getElement();
            if (current.getNext() != null) s += ", ";
            current = current.getNext();
        }
        s += "]";
        return s;
    }
}