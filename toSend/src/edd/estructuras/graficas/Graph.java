
package edd.estructuras.graficas;

import java.util.Iterator;

/**
 * TDA para Graph.
 *
 * @author mindahrelfen
 */
public interface Graph<E> extends Iterable<E> {

    /**
     * Devuelve el numero de vertices contenidos en esta grafica.
     *
     * @return El tamaño de esta grafica como entero mayor a cero.
     */
    public int size();

    /**
     * Devuelve el vertice que se encuentra en el indice index de esta grafica.
     * Si el indice esta fuera del rango valido de indices de esta grafica lanza
     * una excepcion IndexOutOfBoundsException.
     *
     * @param index Indice a revisar
     *
     * @return Una referencia al vertice en la posicion index.
     */
    public E vertex(int index) throws IndexOutOfBoundsException;

    /**
     * Devuelve un arreglo de vertices que se encuentran que se encuentran almacenados en esta grafica.
     *
     * @return Un objeto Iterable con los vertices contenidos por esta grafica.
     */
    public Iterable<E> vertexSet();
}
