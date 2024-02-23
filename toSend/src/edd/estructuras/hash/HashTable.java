package edd.estructuras.hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edd.estructuras.listas.ArrayList;

public abstract class HashTable<E> implements Iterable<E> {

    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<E> it = iterator();

        sb.append("[");
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) sb.append(" ");
        }
        sb.append("]");

        return sb.toString();
    }

    public boolean add(E e) {
        int index;

        index = indexOf(e);

        if (add(index, e)) {
            size++;

            return true;
        } else {
            return false;
        }
    }

    abstract boolean add(int index, E e);

    public boolean remove(E e) {
        int index;

        if (isEmpty()) return false;

        index = find(e);

        if (index == -1) {
            return false;
        } else {
            size--;
            remove(index, e);

            return true;
        }
    }

    abstract void remove(int index, E e);

    public abstract boolean contains(E e);

    public int indexOf(E e) {
        int index;

        index = e.hashCode();
        if (index < 0) index = -index;
        index = compression(index);

        return index;
    }

    abstract int compression(int index);

    abstract public int find(E e);
}
