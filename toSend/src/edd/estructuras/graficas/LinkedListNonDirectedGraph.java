package edd.estructuras.graficas;

import java.util.Iterator;

import edd.estructuras.queue.ArrayQueue;
import edd.estructuras.listas.ArrayList;

/**
 * Implementacion de una grafica mediante una lista de vertices y una lista de adyacencias
 * 
 * @author theghostwarrion
 */
public class LinkedListNonDirectedGraph<E> implements NonDirectedGraph<E>{

  /**
   * Lista donde se guardan los vetices.
   */
  protected ArrayList<Vertice> vertices;

  /**
   * Cantidad de vetices en la grafica.
   */
  protected int size;

  /**
   * Cantidad de aristas en la grafica.
   */
  protected int edgeSize;

  /**
   * Construye una grafica sin vertices ni aristas.
   */
  public LinkedListNonDirectedGraph(){
    vertices = new ArrayList<>();
  }

  @Override
  public int size() {
    return size;
  }

  /**
     * Revisa si el indice dado esta fuera del rango valido de indices de esta lista.
     * Si el indice es invalido lanza una excepcion IndexOutOfBoundsException.
     *
     * @param index Indice a revisar
     */
  protected void checkIndex(int index, int max) throws IndexOutOfBoundsException{
    if (index < 0 || index >= max) {
      throw new IndexOutOfBoundsException("Indice invalido: " + index);
    }
  }

  /**
   * Crea un vertice y y lo añade a la grafica 
   *  
   * @param elemento El elemento que tendra dentro el vetice.
   * @return  Un entero que representa la etiqueta del vertice
   */
  public int newVertex(E elemento){
    int index = vertices.size();

    Vertice nuevo = new Vertice<E>(index, elemento);

    vertices.add(index, nuevo);

    size++;
    
    return nuevo.getEtiqueta();
  }

  @Override
  public E vertex(int index) throws IndexOutOfBoundsException {
    checkIndex(index, vertices.size());

    return (E) vertices.get(index).getContenido();
  }

  @Override
  public Iterable<E> vertexSet() {
    ArrayList<E> tmp;

    if (size() == 0) return null;

    tmp = new ArrayList<>(size());

    for (Vertice vertice : vertices) {
      tmp.add(tmp.size(), (E) vertice.getContenido());
    }

    return tmp;
  }

  @Override
  public Iterator<E> iterator() {
    return (Iterator<E>) vertexSet();
  }

  @Override
  public int edgeNum() {
    return edgeSize;
  }

  @Override
  public boolean getEdge(int i, int j) throws IndexOutOfBoundsException {
    checkIndex(i, size());
    checkIndex(j, size());

    ArrayList<Integer> aux = vertices.get(i).getEdgesList();

    for (int k = 0; k < aux.size(); k++) {
      if (aux.get(k) == j) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void setEdge(int i, int j) throws IndexOutOfBoundsException {
    checkIndex(i, size());
    checkIndex(j, size());

    if (!getEdge(i, j)) {
      vertices.get(i).addEdge(j);
      vertices.get(j).addEdge(i);

      edgeSize++;
    }
    
  }

  @Override
  public Iterable<E> edgeSet(int i) throws IndexOutOfBoundsException {
    checkIndex(i, size());
    
    ArrayList<Integer> tmp1 = vertices.get(i).getEdgesList();
    ArrayList<E> tmp2 = new ArrayList<>();

    for (Integer integer : tmp1) {
      tmp2.add(tmp2.size(), (E) vertices.get(integer).getContenido());
    }

    return tmp2;
  }

  /**
   * Metdo que realiza un recorrido BFS de los vertices de la grafica
   * 
   * @param i Etiqueta del vertice en el que inicia el recorrido.
   */
  public void BFS(int i) throws IndexOutOfBoundsException{
    checkIndex(i, size());

    ArrayQueue<Integer> queue = new ArrayQueue();
    Vertice<E> aux = vertices.get(i);
    aux.setDistancia(0);
    aux.setAnterior(0);
    queue.enqueue(vertices.get(i).getEtiqueta());


    while (!queue.isEmpty()){
      int tmp1 = queue.dequeue();
      aux = vertices.get(tmp1);
      for (int etiqueta : aux.adyacencias) {
        Vertice<E> aux2 = vertices.get(etiqueta);
        if (aux2.getDistancia() == -1) {
          aux2.setDistancia(vertices.get(tmp1).getDistancia() + 1);
          aux2.setAnterior(tmp1);
          queue.enqueue(aux2.getEtiqueta());
        }
      }
    }
  }

  /**
   * Devuelve la trayectoria en forma de lista que hay entre el vertice con indice i y el vertice con el indice j,
   * si no hay una trayectoria se devolvera una lista vacia.
   * 
   * @param i El indice del vertice donde inicia la trayectoria.
   * @param j El indice del vertice donde termina la trayecoria.
   * @return  Una lista con la trayectoria que hay de un vertice a otro, si no exciste una devuelve una lista vacia.
   */
  public ArrayList<E> recoverPath(int i, int j) throws IndexOutOfBoundsException{
    checkIndex(i, size());
    checkIndex(j, size());
    BFS(i);

    ArrayList<E> path = new ArrayList<>();
    Vertice<E> aux =vertices.get(j);

    if (aux.getanterior() == -1) {
      return path;
    }

    int aux2 = j;
    while(aux2 != 0){
      aux = vertices.get(aux2);
      path.add(0, aux.getContenido());
      aux2 = aux.getanterior();
    }

    if (path.size() == 0) {
      path.add(0, aux.getContenido());
    }

    return path;
  }

  /**
   * Devuelve verdadero si hay una trayectoria entre los vertices con indices i y j.
   * 
   * @param i El indice del vertice donde inicia la trayectoria.
   * @param j El indice del vertice donde termina la trayectoria.
   * @return verdadero si exciste una trayectoria entree los vertices, falso en caso contrario.
   */
  public boolean havePath(int i, int j) {
    BFS(i);

    Vertice<E> aux =vertices.get(j);

    if (aux.getDistancia() == -1) {
      return false;
    }

    return true;
  }

  /**
   * Devuelve la distacia que tendra una trayectoria entre los vertices con indices i y j.
   * 
   * @param i El indice del vertice donde inicia la trayectoria.
   * @param j El indice del vertice donde termina la trayectoria.
   * @return La distancia que tendra una trayectoria entre los vertices.
   */
  public int pathLength(int i, int j) {
    BFS(i);

    Vertice<E> aux =vertices.get(j);

    return aux.getDistancia();
  }

  /**
   * Clase que simula un vertice de una grafica.
   */
  protected class Vertice<E>{

    /**
     * Referencia al contenido del vertce.
     */
    protected E contenido;

    /**
     * Etiqueta del vertice.
     */
    protected int etiqueta;
    
    /**
     * Lista con las etiquetas de los vertices que son adyacentes a este.
     */
    protected ArrayList<Integer> adyacencias;
    
    /**
     * Etiquete del vertice anterior a este en una trayectoria.
     * La trayectoria se formara despues de usar el metodo BFS.
     */
    protected int anterior;

    /**
     * Distencia que hay entre el vertice en el que empieza una trayectoria y este vertice.
     * La trayectoria se formara despues de usar el metodo BFS.
     */
    protected int distancia;

    /**
     * Cra un vertice sin adyacencias, con la etiqueta y el contenido dados.
     * 
     * @param etiqueta  La etiqueta del vertice.
     * @param contenido El conteenico del vertice
     */
    protected Vertice(int etiqueta, E contenido){
      this.contenido = contenido;
      this.etiqueta = etiqueta;
      this.adyacencias = new ArrayList<>();
      this.anterior = -1;
      this.distancia = -1;
    }

    /**
     * Devuelve el contenido del vertice.
     * 
     * @return  El contenido del vertice
     */
    protected E getContenido(){
      return this.contenido;
    }

    /**
     * Devuelve la etiqueta del vetice.
     * 
     * @return La etiqueta del vertice.
     */
    protected int getEtiqueta(){
      return this.etiqueta;
    }

    /**
     * Agrega una adyacencia a la lista de adyacencias de este vertice.
     * 
     * @param adyacencia
     */
    protected void addEdge(int adyacencia){
      adyacencias.add(adyacencias.size(), adyacencia);
    }

    /**
     * Devuelde la lista de adyacencias de este vertice.
     * 
     * @return La lista de adyacencias de este vertice.
     */
    protected ArrayList<Integer> getEdgesList(){
      return adyacencias;
    }
    
    /**
     * Devuelve la etiqueta del vertice anterior a este en la trayectoria.
     * La trayectoria se formara despues de usar el metodo BFS.
     * Si no se a usado el metodo BFS o el vertice no es alcanzabe
     * devuelve -1.
     * 
     * @return La etiqueta del vertice anterir a este o -1 si no es alcanzable.
     */
    protected int getanterior(){
      return this.anterior;
    }
    
    /**
     * Devuelve la distancia que hay entre el origen que delimina el metodo BFS y
     * este vertice.
     * 
     * @return La distancia que hay entre el origen delimitado por el metodo BFS y este vertice.
     */
    protected int getDistancia(){
      return this.distancia;
    }

    /**
     * Asigna una nueva etiqueta al vertice anterior, si es que hay una trayectoria
     * establecia despues de usar el metodo BFS.  
     * 
     * @param anterior La etiqueta al vertice amterior a este o -1 si no es alcanzable.
     */
    protected void setAnterior(int anterior){
      this.anterior = anterior;
    }

    /**
     * Asigna una nueva distacia a este vertice despues de usar el metodo BFS
     * Si este vertice no es alcanzado se le asigna -1.
     * 
     * @param distacia  La distancia que hay desde el origen delimitado por el metodo BFS.
     */
    protected void setDistancia(int distacia){
      this.distancia = distacia;
    }
  }
}