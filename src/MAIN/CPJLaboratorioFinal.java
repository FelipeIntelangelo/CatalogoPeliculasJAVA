package MAIN;

import excepciones.*;
import java.util.Scanner;

import negocio.*;

public class CPJLaboratorioFinal {

    public static void main(String[] args) throws AccesoDatosEx {
        Scanner scan = new Scanner(System.in);
        String nombreArchivo;
        ICatalogoPeliculas catalogoPeliculas = new CatalogoPeliculasImpl();
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                                Elige opcion:
                                1.- Iniciar catalogo peliculas
                                2.- Agregar pelicula
                                3.- Listar Peliculas
                                4.- Buscar Pelicula
                                0.- Salir
                                """ + "\n");
            opcion = Integer.parseInt(scan.nextLine());
            switch (opcion){
                case 1:
                    catalogoPeliculas.iniciarArchivo();
                    break;
                case 2:
                    System.out.println("¿Cual es el nombre de la Pelicula?");
                    nombreArchivo = scan.nextLine();
                    catalogoPeliculas.agregarPelicula(nombreArchivo);
                    break;
                case 3:
                    catalogoPeliculas.listarPeliculas();
                    break;
                case 4:
                    System.out.println("¿Que pelicula quieres buscar?");
                    nombreArchivo = scan.nextLine();
                    catalogoPeliculas.buscarPelicula(nombreArchivo);
                    break;
                case 0:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
