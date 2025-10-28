package tda.interfaces;

public interface AVLTDA<E extends Comparable<E>> {


    void insertar(E x);


    boolean estaVacio();


    int getAltura();
}