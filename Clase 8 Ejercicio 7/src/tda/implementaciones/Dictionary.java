package tda.implementaciones;

import tda.interfaces.ABBTDA;
import tda.interfaces.DictionaryTDA;
import tda.interfaces.EntryTDA;
import tda.interfaces.ListaTDA;

public class Dictionary<K, V extends Comparable<V>> implements DictionaryTDA<K, V> {

    private ListaDinamica<EntryTDA<K, V>> entradas;

    public Dictionary() {
        this.entradas = new ListaDinamica<>();
    }


    private EntryTDA<K, V> buscarEntrada(K k) {
        if (k == null) return null;

        EntryTDA<K, V> entradaBusqueda = new Entrada<>(k);

        return this.entradas.buscar(entradaBusqueda);
    }

    @Override
    public void put(K k, V v) {
        EntryTDA<K, V> entrada = buscarEntrada(k);

        if (entrada == null) {
            entrada = new Entrada<>(k);
            this.entradas.agregar(entrada);
        }

        entrada.getValues().insertar(v);
    }

    @Override
    public ListaTDA<V> get(K k) {
        EntryTDA<K, V> entrada = buscarEntrada(k);
        if (entrada == null) {
            return null;
        }
        return entrada.getValues().getElementosInOrder();
    }

    @Override
    public ListaTDA<V> remove(K k) {
        EntryTDA<K, V> entrada = buscarEntrada(k);
        if (entrada == null) {
            return null;
        }

        ListaTDA<V> valoresEliminados = entrada.getValues().getElementosInOrder();

        this.entradas.eliminar(entrada);

        return valoresEliminados;
    }

    @Override
    public V remove(K k, V v) {
        EntryTDA<K, V> entrada = buscarEntrada(k);
        if (entrada == null) {
            return null;
        }

        if (!entrada.getValues().pertenece(v)) {
            return null;
        }

        entrada.getValues().eliminar(v);

        if (entrada.getValues().estaVacio()) {
            this.entradas.eliminar(entrada);
        }

        return v;
    }

    @Override
    public boolean isEmpty() {
        return this.entradas.estaVacia();
    }

    @Override
    public int size() {
        return this.entradas.tamanio();
    }
}