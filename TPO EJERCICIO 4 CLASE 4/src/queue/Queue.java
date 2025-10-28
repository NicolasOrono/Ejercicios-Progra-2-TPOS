package queue;
import list.MyException;

public interface Queue<E> {

    void enqueue(E element);


    E dequeue() throws MyException;


    E front() throws MyException;


    boolean isEmpty();


    int size();
}