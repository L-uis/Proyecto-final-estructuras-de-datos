package edd.Red;

import edd.Red.Cliente;
import edd.estructuras.hash.HashMap;
import edd.estructuras.hash.HashTable;

/**
 * Clase para crear una estacion telefonica, esta usa una tabla hash de clientes.
 * 
 * @author theghostwarrion
 */
public class Estacion {

  /**
   * Refereencia al nombre de la estacion.
   */
  private String nombreEstacion;

  /**
   * Referencia al codigo de area de la estacion.
   */
  private String codigoArea;

  /**
   * Referencia a un mapa hash de clientes.
   */
  private HashMap<Cliente> clientes;

  /**
   * Referencia a un arreglo de clientes.
   */
  private Cliente[] hash;

  /**
   * Construye una estacion con nombre y codigo de area, ademas
   * inicializa el mpa hash y el arreeglo de clientes.
   * 
   * @param nombre El nombre de la estacion.
   * @param codigo El codigo de area de la estacion.
   */
  public Estacion(String nombre, String codigo){
    this.nombreEstacion = nombre;
    this.codigoArea = codigo;
    clientes = new HashMap<>(20);
    hash = new Cliente[20];
  } 
  
  /**
   * Devuelve el nombre de la estacion.
   * 
   * @return El nombre de la estacion.
   */
  public String getEstacion(){
    return nombreEstacion;
  }

  /**
   * Metedo que agrega un cliente a la estacion, esto añadiendolo a la tabla hash
   * de la estacion.
   * 
   * @param cliente El cliente que se añadira.
   */
  public void agregarCliente(Cliente cliente) {
    clientes.add(cliente);
    
    hash[clientes.size() - 1] = cliente;
  }

  /**
   * Devuelve el codigo de area de la estacion.
   * 
   * @return El codigo de area de la estacion.
   */
  public String getCodigo() {
    return codigoArea;
  }

  /**
   * Devuelve un iterador con los clientes que pertenecen a la estacion.
   * 
   * @return La tabla hash con los clientes de esta estacion.
   */
  public Iterable<Cliente> getClientes(){
    return clientes;
  }

  /**
   * Devuelve el indice donde se encuentra el cliente dado, si este no se encuentra en la estacion
   * devuelve -1.
   * 
   * @param cliente El cliente que se buscara en la estacion.
   * @return El indice del cliente en la tabla hash, si este no se encuentra devuelve -1.
   */
  public int find(Cliente cliente) {
    return clientes.find(cliente);
  }
}
