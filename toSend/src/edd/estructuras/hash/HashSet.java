package edd.estructuras.hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edd.estructuras.listas.ArrayList;

public class HashSet<E> extends HashTable<E> {

    static Object NIL = new Object();

    E[] map;

    public HashSet(int capacity) {
        size = 0;
        map = (E[])new Object[capacity];

        for (int i = 0; i < capacity; i++) {
            map[i] = (E)NIL;
        }
    }

    class MapIterator implements Iterator<E> {

        int counter;

        int position;

        boolean canRemove;

        public MapIterator() {
            counter = 0;
            position = -1;
            canRemove = false;
        }

        public boolean hasNext() {
            return counter < size;
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            do {
                position++;
            } while(map[position] == NIL);

            counter++;
            canRemove = true;

            return map[position];
        }

        public void remove() {
            if (!canRemove) throw new IllegalStateException();

            canRemove = false;
            map[position] = (E)NIL;
            counter--;
            size--;
        }
    }

    public Iterator<E> iterator() {
        return new MapIterator();
    }

    boolean add(int index, E e) {
        if (map[index] != NIL) {
            return false;
        } else {
            map[index] = e;

            return true;
        }
    }

    void remove(int index, E e) {
        map[index] = (E)NIL;
    }

    public boolean contains(E e) {
        if (isEmpty()) return false;

        return find(e) != -1;
    }

    int compression(int index) {
       return index % map.length;
    }

    public int find (E e){
        int index;
        index = indexOf(e);

        if (map[index].equals(e)) {
            return index;
        } else {
            return -1;
        }
    }
}
