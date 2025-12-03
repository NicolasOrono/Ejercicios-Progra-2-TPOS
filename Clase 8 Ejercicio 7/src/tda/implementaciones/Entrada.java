package tda.implementaciones;

import tda.interfaces.ABBTDA;
import tda.interfaces.EntryTDA;

public class Entrada<K, V extends Comparable<V>> implements EntryTDA<K, V> {

    private K clave;
    private ABBTDA<V> valores; // Usa un ABB para los valores

    public Entrada(K clave) {
        this.clave = clave;
        this.valores = new ABB<>(); // Inicializa el ABB
    }

    @Override
    public K getKey() {
        return this.clave;
    }

    @Override
    public ABBTDA<V> getValues() {
        return this.valores;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof EntryTDA)) return false;

        EntryTDA<?, ?> otra = (EntryTDA<?, ?>) obj;
        return this.clave.equals(otra.getKey());
    }
}