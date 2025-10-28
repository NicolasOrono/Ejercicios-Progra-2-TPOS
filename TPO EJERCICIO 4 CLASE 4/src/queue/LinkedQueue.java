package queue;
import list.MyException;
import list.Node;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> head; // Frente de la cola
    private Node<E> tail; // Final de la cola
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
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
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (isEmpty()) {
            // Si está vacía, la cabeza y la cola son el nuevo nodo
            head = newNode;
        } else {
            // Si no, el nodo anterior (tail) apunta al nuevo
            tail.setNext(newNode);
        }
        // La cola (tail) es siempre el nodo recién agregado
        tail = newNode;
        size++;
    }

    @Override
    public E dequeue() throws MyException {
        if (isEmpty()) {
            throw new MyException("No se puede hacer dequeue: la cola está vacía");
        }
        // El elemento a sacar es el de la cabeza
        E element = head.getElement();
        // La nueva cabeza es el siguiente nodo
        head = head.getNext();
        size--;
        // Si la cola queda vacía, la cola (tail) también debe ser null
        if (isEmpty()) {
            tail = null;
        }
        return element;
    }

    @Override
    public E front() throws MyException {
        if (isEmpty()) {
            throw new MyException("No se puede ver el frente: la cola está vacía");
        }
        return head.getElement();
    }
}