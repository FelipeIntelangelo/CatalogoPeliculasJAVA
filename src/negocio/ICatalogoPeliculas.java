package negocio;

import excepciones.*;

public interface ICatalogoPeliculas {
    
    String NOMBRE_RECURSO = "peliculas.txt";
    
    void agregarPelicula(String nombrePelicula) throws EscrituraDatosEx;
    
    void listarPeliculas() throws LecturaDatosEx;
    
    void buscarPelicula(String buscar) throws LecturaDatosEx;
    
    void iniciarArchivo() throws AccesoDatosEx;
    
}
