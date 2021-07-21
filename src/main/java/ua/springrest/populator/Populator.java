package ua.springrest.populator;

public interface Populator<T , V> {
    void converter(T t , V v);
}
