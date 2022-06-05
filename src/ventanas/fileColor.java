package ventanas;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class fileColor {
	
	/**
	 * Lee y devuelve un objeto de tipo java.awt.Color del archivo ingresado
	 * @param archivo
	 * @return Color guardado en archivo
	 * @throws FileNotFoundException
	 */
	public  static Color leer(String archivo) throws FileNotFoundException {
	        
	        Color color;
	        
	        try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivo))){
	            
	            color = (Color) lector.readObject()
	                    ;
	            return color;
	            
	        }catch(Exception e1){
	            
	            throw new FileNotFoundException("No se ha encontrado a: " + archivo);
	            
	        }
	    }
	
	/**
	 * Guarda en una archivo binario con el nombre ingresado, el color ingresado
	 * @param color
	 * @param archivo
	 * @return 0 si se guardo y -1 si no
	 */
	public static int guardar(Color color , String archivo){
	        
	        try(ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(archivo))){
	            
	            escritor.writeObject(color);
	            return 0;
	        
	        }catch(Exception ex){
	            return -1;
	        }
	    }

}
