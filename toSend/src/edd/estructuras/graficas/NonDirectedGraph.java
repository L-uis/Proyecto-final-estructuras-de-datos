
package edd.estructuras.graficas;

import java.util.Iterator;

/**
 * TDA para DirectedGraph.
 *
 * @author mindahrelfen
 */
public interface NonDirectedGraph<E> extends Graph<E> {

    /**
     * Devuelve el numero de aristas contenidos en esta grafica.
     *
     * @return El tamaño de esta grafica como entero mayor a cero.
     */
    public int edgeNum();

    /**
     * Devuelve true si existe la arista dada por los indices i y j.
     * Si alguno de los indices esta fuera del rango valido de indices de esta grafica lanza
     * una excepcion IndexOutOfBoundsException.
     *
     * @param i Indice a revisar
     * @param j Indice a revisar
     *
     * @return true su la arista existe.
     */
    public boolean getEdge(int i, int j) throws IndexOutOfBoundsException;

    /**
     * Agrega la arista dada por los indices i y j.
     * Si alguno de los indices esta fuera del rango valido de indices de esta grafica lanza
     * una excepcion IndexOutOfBoundsException.
     *
     * @param i Indice a revisar
     * @param j Indice a revisar
     */
    public void setEdge(int i, int j) throws IndexOutOfBoundsException;

    /**
     * Devuelve un Iterable de aristas que se encuentran almacenados en esta grafica.
     * Si el indice esta fuera del rango valido de indices de esta grafica lanza
     * una excepcion IndexOutOfBoundsException.
     *
     * @param i Indice a revisar
     *
     * @return Una Iterable con los aristas contenidos por esta grafica.
     */
    public Iterable<E> edgeSet(int i) throws IndexOutOfBoundsException;
}
