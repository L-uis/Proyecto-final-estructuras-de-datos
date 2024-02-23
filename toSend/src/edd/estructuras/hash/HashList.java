package edd.estructuras.hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edd.estructuras.listas.ArrayList;
import edd.estructuras.listas.List;

public class HashList<E> extends HashTable<E> {

    List<E>[] list;

    public HashList(int capacity) {
        size = 0;
        list = new List[capacity];

        for (int i = 0; i < capacity; i++) {
            list[i] = new ArrayList<>();
        }
    }

    class ListIterator implements Iterator<E> {

        Iterator<E> it;

        int counter;

        int position;

        boolean canRemove;

        public ListIterator() {
            counter = 0;
            position = -1;
            canRemove = false;
            it = null;
        }

        public boolean hasNext() {
            return counter < size;
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            if (it != null) {
                if (it.hasNext()) {
                    counter++;
                    canRemove = true;

                    return it.next();
                }
            }

            do {
                position++;
                it = list[position].iterator();
            } while(!it.hasNext());

            counter++;
            canRemove = true;

            return it.next();
        }

        public void remove() {
            if (!canRemove) throw new IllegalStateException();

            canRemove = false;
            it.remove();
            counter--;
            size--;
        }
    }

    public Iterator<E> iterator() {
        return new ListIterator();
    }

    boolean add(int index, E e) {
        list[index].add(list[index].size(), e);

        return true;
    }

    void remove(int index, E e) {
        Iterator<E> it = list[index].iterator();


        while (it.hasNext()) {
            if (e == null) {
                if (it.next() == null) {
                    it.remove();
                }
            } else {
                if (e.equals(it.next())) {
                    it.remove();
                }
            }
        }
    }

    public boolean contains(E e) {
        if (isEmpty()) return false;

        return find(e) != -1;
    }

    int compression(int index) {
       return index % list.length;
    }

    public int find (E e){
        int index;
        Iterator<E> it;

        index = indexOf(e);

        it = list[index].iterator();

        while (it.hasNext()) {
            if (e == null) {
                if (it.next() == null) {
                    return index;
                }
            } else {
                if (e.equals(it.next())) {
                    return index;
                }
            }
        }

        return -1;
    }
}
