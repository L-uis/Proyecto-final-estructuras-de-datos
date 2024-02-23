
package edd.estructuras.graficas;

import java.util.Iterator;

/**
 * TDA para DirectedGraph.
 *
 * @author mindahrelfen
 */
public interface DirectedGraph<E> extends Graph<E> {

    /**
     * Devuelve el numero de arcos contenidos en esta grafica.
     *
     * @return El tamaño de esta grafica como entero mayor a cero.
     */
    public int arcNum();

    /**
     * Devuelve true si existe el arco dado por los indices i y j.
     * Si alguno de los indices esta fuera del rango valido de indices de esta grafica lanza
     * una excepcion IndexOutOfBoundsException.
     *
     * @param i Indice a revisar
     * @param j Indice a revisar
     *
     * @return true su el arco existe.
     */
    public boolean getArc(int i, int j) throws IndexOutOfBoundsException;

    /**
     * Agrega el arco dado por los indices i y j.
     * Si alguno de los indices esta fuera del rango valido de indices de esta grafica lanza
     * una excepcion IndexOutOfBoundsException.
     *
     * @param i Indice a revisar
     * @param j Indice a revisar
     */
    public void setArc(int i, int j) throws IndexOutOfBoundsException;

    /**
     * Devuelve un arreglo de arcos que se encuentran que se encuentran almacenados en esta grafica.
     * Si el indice esta fuera del rango valido de indices de esta grafica lanza
     * una excepcion IndexOutOfBoundsException.
     *
     * @param i Indice a revisar
     *
     * @return Una arreglo con los arcos contenidos por esta grafica.
     */
    public Iterable<E> arcSet(int i) throws IndexOutOfBoundsException;
}
