package edd.Red;

import org.w3c.dom.*;

import edd.estructuras.graficas.LinkedListNonDirectedGraph;
import edd.estructuras.listas.ArrayList;

import javax.xml.parsers.*;
import java.io.*;

/**
 * Clase para crear una red telefonica simple
 * 
 * @author theghostwarrion
 */
public class Red {

  /**
   * Referencia a la grafica que contiene las estaciones de la red.
   */
  protected static LinkedListNonDirectedGraph<Estacion> grafica;

  /**
   * Referencia al numero de estaciones que hay en la red.
   */
  protected static String numEstaciones;

  /**
   * Referencia al numero de enlaces que hay entre las estaciones.
   */
  protected static String numEnlaces;

  /**
   * Contruye una red de telefonos con los datos quee hay en el archivo cuya referencia es filename.
   * 
   * @param fileName El archivo donde se encuentran los datos de la red telefonica.
   */
  public Red(String fileName){
    grafica = new LinkedListNonDirectedGraph<>();
    try {
      load(fileName);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Metodo que verifica que dos numeros puedan llamarse entre si
   * 
   * @param saliente  El numero del que sale la llamada.
   * @param entrante  El numero que recibe la llamada.
   * @return  true si pueden llamar se, de lo contrario devuelve false.
   */
  public boolean puedeLlamar(String saliente, String entrante) {
    String[] aux;
    int sal, ent;

    aux = saliente.split("-");

    sal = Integer.parseInt(aux[1]);

    aux = entrante.split("-");

    ent = Integer.parseInt(aux[1]);

    return grafica.havePath(sal, ent);
  }

  /**
   * Metodo que realiza la llamada entre dos numeros, ademas de regresar la distacia de las estaciones.
   * 
   * @param saliente  El numero del que sale la llamada.
   * @param entrante  El numero que recibe la llamada.
   * @return  Una cadena que confirma la llamada y contien la distancia de las estaciones.
   */
  public String hacerLlamada(String saliente, String entrante) {
    String llamada = "Llamada exitosa. Distancia :";

    ArrayList<String> tmp = new ArrayList<>();

    String[] aux;
    int sal, ent;

    aux = saliente.split("-");

    sal = Integer.parseInt(aux[1]);

    aux = entrante.split("-");

    ent = Integer.parseInt(aux[1]);
    
    for (Estacion estacion : grafica.recoverPath(sal, ent)) {
      tmp.add(tmp.size(), estacion.getEstacion());
    }
    

    llamada += distancia(saliente, entrante) + "\n";

    llamada += "Las estaciones usadas fueron: " + tmp.toString();

    return llamada;
  }


  /**
   * Metodo que verifica que un numero de telefono sea falido, devuelve verdadero si este ese valido, 
   * en caso contrario devuelve falso.
   * 
   * @param aux El numero de telefono.
   * @return verdadero si el numero es valido, en caso contrario devuelve falso.
   */
  public boolean valid(String aux) {
    if (aux.length() != 14) {
      return false;
    }

    String[] tmp = aux.split("-");

    if (tmp.length == 3 && tmp[0].length() == 2 && tmp[1].length() == 3 && tmp[2].length() == 7) {

      int tmp1 = Integer.parseInt(tmp[1]);
      
      if (tmp1 >= grafica.size()) {
        return false;
      }

      Estacion estacion = grafica.vertex(tmp1);

      Cliente cliente = new Cliente("nombre", aux, "" + tmp1);

      if (estacion.find(cliente) == -1) {
        return false;
      }
    }

    return true;
  }

  /**
   * Metodo que devuelve la distancia entre las estaciones de dos numeros.
   * 
   * @param saliente El numero del que sale la llamada.
   * @param entrante  El numero que recibe la llamada. 
   * @return El numero de estaciones que recorrera la llamada, si las estaciones estan desconectadas devuelve -1.
   */
  public int distancia(String saliente, String entrante) {

    String[] aux;
    int sal, ent;

    aux = saliente.split("-");

    sal = Integer.parseInt(aux[1]);

    aux = entrante.split("-");

    ent = Integer.parseInt(aux[1]);

    if (!grafica.havePath(sal, ent)) return -1;

    return grafica.pathLength(sal, ent);
  }

  /**
   * Metodo que realiza una video llamada entre dos numeros, ademas regresa la distancia entre las estaciones.
   * 
   * @param aux El numero del que sale la video llamada.
   * @param aux2 El numero que recibe la video llamada.
   * @return
   */
  public String hacerVideoLlamada(String saliente, String entrante) {

    String llamada = "Video llamada exitosa. Distancia :";

    ArrayList<String> tmp = new ArrayList<>();

    String[] aux;
    int sal, ent;

    aux = saliente.split("-");

    sal = Integer.parseInt(aux[1]);

    aux = entrante.split("-");

    ent = Integer.parseInt(aux[1]);
    
    for (Estacion estacion : grafica.recoverPath(sal, ent)) {
      tmp.add(tmp.size(), estacion.getEstacion());
    }
    

    llamada += distancia(saliente, entrante) + "\n";

    llamada += "Las estaciones usadas fueron: " + tmp.toString();

    return llamada;
  }

  /**
   * Metodo que devuelve todos los clientes dentro de la red.
   * 
   * @return un iterador con todos los clientes en las estaciones de la red.
   */
  public Iterable getAll() {

    ArrayList<String> lista = new ArrayList<>(150);

    for (Estacion estacion : grafica.vertexSet()) {
      for (Cliente cliente : estacion.getClientes()) {
        lista.add(lista.size(), cliente.toString());
      }
    }
    return lista;
  }

  /**
   * Metodo que carga los datos del archivo fileName en la red telefonica.
   * 
   * @param fileName La cadena que contiene el nombre del archivo donde se encuentran los datos de la red.
   * @throws Exception
   */
  private static void load(String fileName) throws Exception{

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(new File(fileName));
    document.getDocumentElement().normalize();

    NodeList nList1 = document.getElementsByTagName("Red");

    for (int i = 0; i < nList1.getLength(); i++) {

      Node node = nList1.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element eElement = (Element) node;

        numEstaciones =  eElement.getAttribute("numEstaciones");
        numEnlaces = eElement.getAttribute("numEnlaces");
      }

    }

    NodeList nList = document.getElementsByTagName("Estacion");

    for (int tmp = 0; tmp < nList.getLength(); tmp++) {
      
      Node node = nList.item(tmp);

      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element eElement = (Element) node;

        Estacion estacion = new Estacion(eElement.getAttribute("nombreEstacion"), eElement.getAttribute("codigo"));
  
        NodeList nList2 = eElement.getElementsByTagName("Clientes").item(0).getChildNodes();

        for (int i = 0; i < nList2.getLength(); i++) {

          Node nodeC = nList2.item(i);

          if (nodeC.getNodeType() == Node.ELEMENT_NODE) {
            Element eElemtC = (Element) nodeC;

            Cliente cliente = new Cliente(eElemtC.getAttribute("nombreCliente"),eElemtC.getAttribute("telefono"), estacion.getCodigo());

            estacion.agregarCliente(cliente);
          }

        }

        grafica.newVertex(estacion);
      }
    }

    for (int tmp = 0; tmp < nList.getLength(); tmp++) {
      
      Node node = nList.item(tmp);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element eElement = (Element) node;
  
        NodeList nList3 = eElement.getElementsByTagName("Enlaces").item(0).getChildNodes();
  
        for (int i = 0; i < nList3.getLength(); i++) {
  
          Node nodeE = nList3.item(i);
  
          if (nodeE.getNodeType() == Node.ELEMENT_NODE) {
            Element eElementE = (Element) nodeE;  
            grafica.setEdge( Integer.parseInt(eElementE.getAttribute("primeraEstacion")), Integer.parseInt(eElementE.getAttribute("segundaEstacion")));
          }
        }
      }
    }
  }

}
