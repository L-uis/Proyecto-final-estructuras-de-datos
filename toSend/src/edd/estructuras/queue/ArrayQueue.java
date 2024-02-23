package edd.estructuras.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de Cola con arreglos
 * 
 * @author Juan Luis Peña Mata 
 */

public class ArrayQueue<E> implements Queue<E> {

  /**
   * Tamaño de la cola cuando se usa el constructor por omision.
   */
  protected static final int NUM_ELEMENTOS_MAXIMOS = 50;

  /**
   * Referencia al inicio de la cola en el arreglo.
   */
  protected int head;

  /**
   * Referencia al final de la cola en el arreglo.
   */
  protected int tail;

  /**
   * Tamaño de la cola.
   */

  protected int size;

  /**
   * Arreglo donde estará la cola.
   */
  protected E elementos[];

  /**
   * Constructor por defeccto, construye una cola sin elementos con un arreglo de capacidad 50
   */
  public ArrayQueue(){
    this(NUM_ELEMENTOS_MAXIMOS);
  }

  /**
   * Construte una cola vacia con capacidad inicial
   * 
   * @param capacidad Capacidad inicial de la cola
   */
  public ArrayQueue(int capacidad){
    size = 0;
    head = 0;
    tail = capacidad -1;
    elementos = (E[]) new Object[capacidad];
  }

  @Override
  public int size(){
    return size;
  }

  @Override
  public boolean isEmpty(){
    return size == 0;
  }

  @Override
  public E first(){
    if(isEmpty()) return null;
    
    return elementos[head];
  }

  @Override
  public E dequeue(){
    if(isEmpty()) return null;

    E aux = elementos[head];
    elementos[head] = null;
    size--;
    head = (head + 1) % elementos.length;
    
    return aux;

  }

  @Override
  public void enqueue(E elem){
    if (size == elementos.length) throw new RuntimeException("El arreglo llego a su capacidad maxima");

    size++;
    tail = (tail + 1) % elementos.length;
    elementos[tail] = elem;
  }

  @Override
  public Iterator<E> iterator(){
    return new ArrayQueueIterator();
  }

  /**
   * Clase que implementa el Iterador de la clase ArrayQueue
   */
  protected class ArrayQueueIterator implements Iterator<E>{

    /**
     * posicion del valor next a devolver.
     */
    protected int next;

    /**
     * Bandera que dice si se puede borrar un elemento o no.
     */
    protected boolean canRemove;

    /**
     * Inicializa el iterador en la cabesa de la cola.
     */
    public ArrayQueueIterator(){
      next = head;
      canRemove = false;
    }



    @Override
    public boolean hasNext(){
      return next < size;

    }

    @Override
    public E next(){
      E e;

      if (!hasNext()) throw new NoSuchElementException();

      e = elementos[(next++) % elementos.length];
      canRemove = true;

      return e;
    }

    @Override
    public void remove(){
      int index;
      if (!canRemove) throw new IllegalStateException();

      next = (next--) % elementos.length;
      index = 0;
      while(index < size -1){

        elementos[(next + index) % elementos.length] = elementos[(next + index + 1) % elementos.length];
        index++;
      }
      

    }

  }

  @Override
  public String toString(){
    StringBuilder sb;
    
    if(isEmpty()) return "[]";

    sb = new StringBuilder();
    
    sb.append("[");
    for(int i = 0 ; i < size ; i++){
      sb.append(elementos[(head + i) % elementos.length]);
      if(i < size - 1) sb.append(" ");
    }
    sb.append("]");

    return sb.toString();
  }
}