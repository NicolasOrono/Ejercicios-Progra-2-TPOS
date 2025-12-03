package tda.interfaces;

public interface EntryTDA<K, V extends Comparable<V>> {
    K getKey();
    ABBTDA<V> getValues();
}