package ventanas;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Color;

public class Ventana extends JFrame implements ActionListener {

    JButton boton1, boton2, boton3;
    int y = 65;
    int width = 100;
    int heigth = 30;

    /*
     * Crea una ventana con tres botones, cada boton cambia el fondo de la ventana
     */
    public Ventana() {

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Creacion de botones
        boton1 = new JButton("Red");
        boton1.setBounds(10, y, width, heigth);
        add(boton1);
        boton1.addActionListener(this);
        
        boton2 = new JButton("Green");
        boton2.setBounds(140, y, width, heigth);
        add(boton2);
        boton2.addActionListener(this);
        
        boton3 = new JButton("Blue");
        boton3.setBounds(270, y, width, heigth);
        add(boton3);
        boton3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	//Control de eventos
        if (e.getSource() == boton1) {

        	getContentPane().setBackground(Color.red);
        }
        if (e.getSource() == boton2) {

        	getContentPane().setBackground(Color.green);
        }
        if (e.getSource() == boton3) {

        	getContentPane().setBackground(Color.blue);
        }
    }

    public static void main(String[] args) {

    	//Creacion de ventana
    	Ventana ventana = new Ventana();
    	
        ventana.setBounds(0, 0, 400, 200);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setResizable(false);

    }
}