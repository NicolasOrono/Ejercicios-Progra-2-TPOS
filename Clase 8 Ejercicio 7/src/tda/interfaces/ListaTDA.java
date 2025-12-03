package tda.interfaces;

public interface ListaTDA<E> {
    void agregar(E x);
    void eliminar(E x);
    E obtener(int pos);
    int tamanio();
    boolean estaVacia();
}