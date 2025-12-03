package tda.interfaces;

public interface ABBTDA<E extends Comparable<E>> {
    void insertar(E x);
    void eliminar(E x);
    boolean pertenece(E x);
    boolean estaVacio();


    ListaTDA<E> getElementosInOrder();
}