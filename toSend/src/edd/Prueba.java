package edd;

import java.util.Scanner;

import javax.swing.text.View;

import edd.Red.Red;
import edd.colors.Colors;
import edd.estructuras.graficas.LinkedListNonDirectedGraph;

public class Prueba{

  private static final String archivo = "red.xml";

  public static int getInt(String mensaje, String error, int min, int max) {
    int val;
    Scanner scn = new Scanner(System.in);

    while (true) {
        Colors.println(mensaje, Colors.HIGH_INTENSITY);
        if (scn.hasNextInt()) {
            val = scn.nextInt();
            // (-infinito, min) || (max, infinito)
            if (val < min || max < val) {
                Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
            } else {
                return val;
            }
        } else {
            scn.next();
            Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
        }
    }
  }

  public static String getLine(String msg) {
    Scanner scn = new Scanner(System.in);

    Colors.println(msg, Colors.HIGH_INTENSITY);

    return scn.next();
  }

  public static void print(String msg, Iterable it) {
    int i = 1;
    StringBuilder sb = new StringBuilder();

    Colors.println(msg, Colors.HIGH_INTENSITY);

    for (Object obj: it) {
        sb.append(i++);
        sb.append(". ");
        sb.append(obj.toString());
        sb.append(".\n");
    }

    Colors.println(sb.toString(), Colors.CYAN + Colors.HIGH_INTENSITY);
  }

  public static void llamar(Red red, String saliente, String entrante){
    String llamada;

    if (red.puedeLlamar(saliente, entrante)) {
      llamada = red.hacerLlamada(saliente, entrante);
      Colors.println(llamada, Colors.GREEN + Colors.HIGH_INTENSITY);
    }else{
      llamada = "La llamada no pudo realizarse pues no existe conexion directa entre ambos numeros.";
      Colors.println(llamada, Colors.RED + Colors.HIGH_INTENSITY);
    }

  }

  public static void main(String[] args) {
    
   
    String mensaje, error, aux, aux2, llamada;
    int opcion;

    Colors.println("Este programa simula llamadas telefonicas y de video.", Colors.BLUE + Colors.HIGH_INTENSITY);

    Red red = new Red(archivo);

    StringBuilder sb = new StringBuilder();

    sb.append("Seleciona una opcion.\n");
    sb.append("1. Realizar llamada telefonica.\n");
    sb.append("2. Realizar video llamada.\n");
    sb.append("3. Imprimir directorio de numeros.\n");
    sb.append("0. salir.");
    mensaje = sb.toString();
    error = "Por favor ingresa una opcion valida.";

    do {  
      opcion = getInt(mensaje, error, 0, 3);

      switch (opcion) {
        case 1:
          aux = getLine("Ingresa el numero en el formato: 55-XXX-XXXXXXX");

          aux2 = getLine("Ingresa el numero en el formato: 55-XXX-XXXXXXX");

          if (!red.valid(aux)){

            Colors.println("El numero " + aux + " es invalido", Colors.RED + Colors.HIGH_INTENSITY);

            continue;

          } else if (!red.valid(aux2)) {
            
            Colors.println("El numero " + aux2 + " es invalido", Colors.RED + Colors.HIGH_INTENSITY); 
            
            continue;

          }

          llamar(red, aux, aux2);

          break;
        
        case 2:

          aux = getLine("Ingresa el numero en el formato: 55-XXX-XXXXXXX");

          aux2 = getLine("Ingresa el numero en el formato: 55-XXX-XXXXXXX");
          
          if (!red.valid(aux)){

            Colors.println("El numero " + aux + " es invalido", Colors.RED + Colors.HIGH_INTENSITY);

          } else if (!red.valid(aux2)) {
            
            Colors.println("El numero " + aux2, Colors.RED); 
          
          }

          int videollamada = red.distancia(aux, aux2);

          if (videollamada == -1) {

            Colors.println("La video llamada no pudo realizarse, se intentara una llamada simple.", Colors.RED + Colors.HIGH_INTENSITY);
            llamar(red, aux, aux2);

          }else if (videollamada > 6) {
            
            Colors.println("La video llamada no pudo realizarse, la distancia " + videollamada +" esta fuera del rango de video llamadas.", Colors.RED + Colors.HIGH_INTENSITY);
            llamar(red, aux, aux2);
            
          }else{

            llamada = red.hacerVideoLlamada(aux, aux2);
            Colors.println(llamada, Colors.GREEN + Colors.HIGH_INTENSITY);
            
          }

          break;
        case 3:
          print("Los clientes en la red son:", red.getAll());
          break;

      }
      
    } while (opcion != 0);
  }

}