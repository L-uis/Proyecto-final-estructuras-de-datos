package edd.estructuras.hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edd.estructuras.listas.ArrayList;

public class HashMap<E> extends HashSet<E> {

    int colision;

    public HashMap(int capacity) {
        super(capacity);
        colision = 0;
    }

    public int indexOf(E e){
        int index, max;

        index = e.hashCode();
        if (index < 0) index = -index;
        index = compression(index);
        max = map.length;

        while (map[index] != NIL && !map[index].equals(e) && max > 0) {
            index = compression(index + 1);
            max--;
        }

        if (max != map.length) {
            System.out.println("Colision!!! " + ++colision);
        }

        return index;
    }

    public String toString() {
        String s = "";
        for(E e: map) {
            s = s + e + " ";
        }

        return s;
    }

    public int find (E e){
        int index, max;

        index = indexOf(e);

        if (map[index].equals(e)) {
            return index;
        } else {
            max = map.length;
            while (!map[index].equals(e) && max > 0) {
                index = compression(index + 1);
                max--;
            }

            if (map[index].equals(e)) {
                return index;
            } else {
                return -1;
            }
        }
    }
}
