package ventanas;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.FileNotFoundException;



public aspect Observant {
	
	private final static String archivo = "ajuestes.dat";
		
	/**
	 * Poincut con captura del parametro de setBackground()
	 * @param color
	 */
	pointcut colorChange(Color color) : call(* setBackground(Color)) && args(color);
    
	/**
	 * Imprecion del color capturado en pantalla
	 * @param color
	 */
	void around(Color color) : colorChange(color) {
		
		//Dejar al comando del pointcut ejecutarse sin cambios al color
		proceed(color);
		
		//Imprecion del color capturado despues del poincut
		String colorTexto = color.toString();
    	System.out.println("Color de fondo cambiado a: " + colorTexto);
    	
    	int res = fileColor.guardar(color, archivo);
    	
    	if(res < 0) {
    		System.out.println("�Proseso de guardado fallido!");
    	}
	}
	
	/**
	 * Poincut que captura la ejecucion del constructor de la clase Ventana
	 */
	pointcut newVentana(): execution( Ventana.new(..));
	
	/**
	 * Asigna el color grardado en memoria al nuevo objeto ventana
	 */
	after(): newVentana(){
		
		System.out.println("Cargando datos alamasenados"); 
		
		//Captura de objeto del constructor
		Ventana ventana = (Ventana) thisJoinPoint.getThis();
		
		//Cargar al ultimo clor guardado
		try {
			Color fondo = fileColor.leer(archivo);
			ventana.getContentPane().setBackground(fondo);
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage() + "\nCargando color por defecto");
			ventana.getContentPane().setBackground(Color.white);
			
		} 
		
	}
	
	

}