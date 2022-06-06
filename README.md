# Tarea_Aspectos_G1E
**Funcionalidad minima**
El pointcut captura las llamadas a y optiene el parametro el awt.Color Object que se le pasa como parametro. El awt.Color es convertido a formato de texto y escrito en la consola

```
pointcut colorChange(Color color) : call(* setBackground(Color)) && args(color);

void around(Color color) : colorChange(color) {
		
		//Dejar al comando del pointcut ejecutarse sin cambios al color
		proceed(color);
		
		//Imprecion del color capturado despues del poincut
		String colorTexto = mapeo.get(color);
    	System.out.println("Color de fondo cambiado a: " + colorTexto);
    	
    	int res = fileColor.guardar(color, archivo);
    	
    	...
	}
```

*Ejemplo de ejecusion*

![Consola](/imagenes/cap1.jpg)



**Funcionalidad Adicional**
La Ventana solo esta encargada de mostrar al usuario la interfas grafica y ejecutar las acciones corespondientes a los botones. Pero estos cambios no son guardados a largo plazo. En una aplicasion real , las responsabilidades de guardado de cambios esta a cargo de otras clase, pero dentro de la interfas se deben incorporar estos metodos de guardado y carga de cambios dentro de su codigo. Por lo tanto el alamacenamiento se vuelve un consern transversal.

Para remediar esto en el programa, se ha agregado al pointcut la responsabilidad de guardar en un archivo binario el awt.Color que registra cuando se cambia el color del fondo.

```
    pointcut colorChange(Color color) : call(* setBackground(Color)) && args(color);

    void around(Color color) : colorChange(color) {
            
            ...

            int res = fileColor.guardar(color, archivo);
            
            if(res < 0) {
                System.out.println("Proseso de guardado fallido!");
            }
        }
```

 Ademas se ha agregado un pointcut que captura la ejecusion del contructor de la clase Ventana y asigna el color de fondo guardado previemente o blanco si no exite el archivo binario.

```
	pointcut newVentana(): execution( Ventana.new(..));

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

```

 Los cambios seran guardador en el archivo *ajustes.dat*
 
 ![Carpetas](/imagenes/cap2.jpg)
