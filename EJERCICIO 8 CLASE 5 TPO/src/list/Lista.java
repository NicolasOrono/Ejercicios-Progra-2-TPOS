package list;

public interface Lista<E> {
    void add(E element);
    E get(int index);
    boolean remove(E element);
    int size();
    boolean isEmpty();
}