package negocio;

import datos.*;
import domain.Pelicula;
import excepciones.*;
import java.util.function.Consumer;


public class CatalogoPeliculasImpl implements ICatalogoPeliculas{

    private final IAccesoDatos datos;
    
    public CatalogoPeliculasImpl(){
        this.datos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula){
        var pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al agregar la pelicula");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarPeliculas(){
        try{
          var peliculas = this.datos.listar(NOMBRE_RECURSO);
          peliculas.forEach((Pelicula pelicula) -> {
              System.out.println("pelicula: " + pelicula);
          });
        }catch(AccesoDatosEx ex){
            System.out.println("Error de acceso de datos");
            ex.printStackTrace(System.out);
        }
        
        
    }

    @Override
    public void buscarPelicula(String buscar){
        String resultado = null;
        try{
            resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
            System.out.println("resultado: " + resultado);
        }catch(AccesoDatosEx ex){
            System.out.println("Error de Acceso de Datos buscando pelicula");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void iniciarArchivo(){
        try{
            if(this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }else{
                datos.crear(NOMBRE_RECURSO);
            }
        } catch(AccesoDatosEx ex){
            System.out.println("Error al inicar el archivo de peliculas");
            ex.printStackTrace(System.out);
        }
    }
    

}
