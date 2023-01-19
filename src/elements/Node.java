
package elements;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;


public class Node{
    
    //Creación de variables.
    private int x=0, y=0,id=0;
    private String name;
    private static int r = 50;
    private boolean color;
   
    //Constrcutores.
    public Node() {
    }
    
    public Node(int x, int y, String name, int id) {
        this.x=x;
        this.y=y;
        this.name = name;
        this.color = false;
        this.id=id;
    }
    
    public Node(int x, int y, String name,boolean color, int id) {
        this.x=x;
        this.y=y;
        this.name = name;
        this.color = color;
        this.id=id;
    } 
    

    
    //Métodos.
    public void DibFigura(Graphics g)//Método para crear la figura.
    {   
        if(!this.color){
            g.setColor(java.awt.Color.YELLOW);
        }else{
            g.setColor(java.awt.Color.BLUE);
        }
        g.fillOval(this.x-r/2, this.y-r/2, r, r);
        g.setColor(java.awt.Color.BLACK);
        g.drawOval(this.x-r/2, this.y-r/2, r, r);
        g.drawString(this.name, this.x, this.y);
    } 
    
  
    //Setters y Getters
    public int getId() {
        return id;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    } 
    
    public boolean isColor() {
        return color;
    }
    
    public void setName(String name) {
        this.name = name;
    } 
    
    public void setColor(boolean color) {
        this.color = color;
    } 

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setId(int id) {
        this.id = id;
    }   

    @Override
    public String toString() {
        return name+" "+id;
    }
    
    
}
