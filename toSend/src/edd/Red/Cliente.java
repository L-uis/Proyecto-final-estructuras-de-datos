package edd.Red;

/**
 * Clase para crear clientes con un nombre y numero asociados.
 * 
 * @author theghostwarrion
 */
public class Cliente {

  /**
   * Referencia al nombre del cliente.
   */
  private String nombre;
  
  /**
   * Referencia al numero del cliente
   */
  private String numero;

  /**
   * Refeereencia a la estacion del cliente.
   */
  private String estacion;

  /**
   * Construye un cliente con nombre, numero y estacion.
   * 
   * @param nombre el nombre del cliente.
   * @param numero el numero del cliente.
   * @param estacion la estacion del cliente.
   */
  public Cliente(String nombre, String numero, String estacion){
    this.nombre = nombre;
    this.numero = numero;
    this.estacion = estacion;
  }

  public int hashCode(){
    return hashCode(this.numero);
  }

  private static int hashCode(String cadena) {
    String[] aux = cadena.split("-");
    char[] aux2 = aux[2].toCharArray();
    int num = 0;

    for (char c : aux2) {
      num += Character.getNumericValue(c);
    }

    return num;
  }

  @Override
  public boolean equals(Object obj){
    Cliente e;

    if (obj == null) return false;
    if (!(obj instanceof Cliente)) return false;
    e = (Cliente) obj;
    if (!this.numero.equals(e.numero)) return false;

    return true;
  }

  @Override
  public String toString(){
    return "["+numero+" "+ nombre +" " + estacion +"]";
  }

}
