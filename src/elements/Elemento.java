/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.LinkedList;

/**
 *
 * @author POWER
 */
public class Elemento {
    
    //Creación de variables.
    private String nombre;
    private String accion;
    private int indice, id;
    private  Node Nodo;
    private  Arcos Arco;
    
    //Constrcutores.
    public Elemento() {
    }
    
    public Elemento(String nombre,String accion,int indice, Node Nodo, int id) {
        this.nombre = nombre;
        this.indice = indice;
        this.Nodo = Nodo;
        this.accion = accion;
        this.id = id;
    }
    
    public Elemento(String nombre,String accion, int indice, Arcos Arco, int id) {
        this.nombre = nombre;
        this.indice = indice;
        this.Arco = Arco;
        this.accion = accion;
        this.id = id;
    } 
    
    
    //Setters y Getters
    public String getNombre() {
        return nombre;
    }

    public Node getNodo() {
        return Nodo;
    }

    public Arcos getArco() {
        return Arco;
    } 

    public int getIndice() {
        return indice;
    }
   

    public String getAccion() {
        return accion;
    } 

    public int getId() {
        return id;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    @Override
    public String toString() {
        return nombre+" "+indice+" "+id; 
    }
       
}
