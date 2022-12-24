package datos;

import domain.Pelicula;
import excepciones.*;
import java.io.*;
import java.util.*;


public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreArchivo) throws LecturaDatosEx {
        var archivo = new File(nombreArchivo);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null) {
                var pelicula = new Pelicula(linea);
                peliculas.add(pelicula);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            throw new LecturaDatosEx("Excepcion al listar peliculas:" + ex.getMessage());
        } catch (IOException ex) {
            throw new LecturaDatosEx("Excepcion al listar peliculas:" + ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File(nombreArchivo);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito informacion al archivo:" + pelicula);
        } catch (IOException ex) {
            throw new EscrituraDatosEx("Excepcion al escribir la pelicula:" + ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombreArchivo);
        String resultado = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            int indice = 0;
            while(linea != null){
                if(buscar != null && buscar.equalsIgnoreCase(linea)){
                    resultado = "Pelicula " + linea + " encontrada en el indice " + indice;
                    break;
                }
                linea = entrada.readLine();
                indice++;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            throw new LecturaDatosEx("Excepcion al buscar la pelicula:"+ex.getMessage());
        } catch (IOException ex) {
            throw new LecturaDatosEx("Excepcion al buscar la pelicula:"+ex.getMessage());
        }

        return resultado;
    }

    @Override
    public void crear(String nombreArchivo) throws EscrituraDatosEx {
        var archivo = new File(nombreArchivo);
        try{
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo:");
            
        } catch (FileNotFoundException ex) {
            throw new EscrituraDatosEx("Error al crear el archivo" + ex.getMessage());
        } catch (IOException ex) {
            throw new EscrituraDatosEx("Error al crear el archivo" + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);
        if(archivo.exists()){
            archivo.delete();
            System.out.println("Se ha borrado el archivo");
        }else{
            System.out.println("Ese archivo no existe");
        }
    }

}
