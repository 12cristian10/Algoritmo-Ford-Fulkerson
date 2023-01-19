
package elements;

import java.awt.*;

public class Arcos{

    //Creación de variables.
    private int x1, y1, x2, y2, id;
    private double theta1, theta2;
    private int value;
    private Point arista1P, arista2P,puntoFlecha1,puntoFlecha2;
    private static final double DELTA = Math.PI / 8;
    private static final double C = 25;
    private static final double r = 50/2;
    private String from, to;
   
    //Constrcutor.
    public Arcos(int x1, int y1, int x2, int y2, int value,String from, String to, int id) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.value = value;
        this.id=id;
        this.from = from;
        this.to = to;
    }
    

    
    //Métodos.
    public void calcularFlecha() {//Metodo para determinar la posicion y el tamaño de la flecha y la punta de la flecha 
        
        this.theta1 = calcularAngulo(this.x1,this.y1,this.x2,this.y2);
        this.theta2 = calcularAngulo(this.x2,this.y2,this.x1,this.y1);
        this.puntoFlecha1 = calcularPosicion(theta1,r,this.x2,this.y2);
        this.puntoFlecha2 = calcularPosicion(theta2,r,this.x1,this.y1);
        double[] ajustadas = trasladarFigura(this.puntoFlecha2.x,this.puntoFlecha2.y);
	double[] cateto = calcularCatetos(ajustadas[0], ajustadas[1]);
	int[] factor = calcularFactor(ajustadas[0], ajustadas[1]);
	double xp = this.puntoFlecha1.x+ (factor[0] * cateto[0]);
	double yp = this.puntoFlecha1.y+ (factor[1] * cateto[1]);		
	ajustadas = trasladarFigura(xp, yp);
	this.arista1P = rotarHorario(ajustadas[0], ajustadas[1]);
	this.arista2P = rotarAntihorario(ajustadas[0], ajustadas[1]);    
    }
    
    public void DibRelacion(Graphics g){//Método para crear la figura.
        Graphics2D g2d = (Graphics2D)g;
        
        calcularFlecha();

        //Medida del dash.
        float singuiones [] = {100,0};
        
        
        g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,singuiones,1));
        g2d.setColor(Color.BLACK);

        g2d.drawLine(this.puntoFlecha2.x,this.puntoFlecha2.y,this.puntoFlecha1.x,this.puntoFlecha1.y);
        g2d.drawLine(this.arista1P.x, this.arista1P.y,this.puntoFlecha1.x,this.puntoFlecha1.y);
        g2d.drawLine(this.arista2P.x, this.arista2P.y,this.puntoFlecha1.x,this.puntoFlecha1.y);

        g.drawString(Integer.toString(this.value), this.puntoFlecha2.x+(this.puntoFlecha1.x-this.puntoFlecha2.x)/2+5, this.puntoFlecha2.y+(this.puntoFlecha1.y-this.puntoFlecha2.y)/2+2);
    } 
    


    //Trasladar la figura
    private double[] trasladarFigura(double x, double y) {
	double xp = x - this.puntoFlecha1.x;
	double yp = this.puntoFlecha1.y  - y;
	double[] coordenadas = {xp, yp};
	return coordenadas;
    }

    //Calcular longitud de catetos
    private double[] calcularCatetos(double x, double y) {
	double ang = Math.atan(y / x);
	double a = Math.sqrt(Math.pow(C, 2) / (1 + Math.pow(Math.tan(ang), 2)));
	double b = Math.sqrt(Math.pow(C, 2) - Math.pow(a, 2));
	double[] ab = {a, b};
	return ab;
    }

    //Calcular factor de aristas
    private int[] calcularFactor(double x, double y) {
	double fx = x / Math.abs(x);
	double fy = y / (Math.abs(y) * -1);
	int[] factor = {(int) fx, (int) fy};
	return factor;
    }

    //Calcular arista horario
    private Point rotarHorario(double x, double y) {
        double xp = x * Math.cos(DELTA) + y * Math.sin(DELTA);
	double yp = (-1 * x * Math.sin(DELTA)) + y * Math.cos(DELTA);
	xp = this.puntoFlecha1.x  + xp;
	yp = ((int)Math.atan(Math.PI)*(this.puntoFlecha1.y )) - yp;
	return new Point((int) xp, (int) yp);
    }

    //Calcular arista antihorario
    private Point rotarAntihorario(double x, double y) {
	double xp = x * Math.cos(DELTA) + (-1 * y * Math.sin(DELTA));
	double yp = x * Math.sin(DELTA) + y * Math.cos(DELTA);
	xp = this.puntoFlecha1.x+ xp;
	yp = ((int)Math.atan(Math.PI)*(this.puntoFlecha1.y)) - yp;
        return new Point((int) xp, (int) yp);
    } 
    
    //calcular posicion de arista entre nodos
    private Point calcularPosicion(double angulo, double radio, double x, double y){
            
        double posx =Math.round((float) (x + Math.sin(angulo) * radio));
        double posy =Math.round((float) (y + Math.cos(angulo) * radio));
        return new Point((int) posx, (int) posy);
    }
    
    //calcular angulo de arista respecto a la posicion de un nodo
    private double calcularAngulo(double x1, double y1, double x2, double y2){
      
        double theta = Math.atan2(x2-x1, y2-y1);
        theta = Math.toRadians(Math.toDegrees(theta) + 180);
        return theta;
    }
        
    //Setters y Getters
    public int getId() {
        return id;
    }
    
     public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }
 
    public int getValue() {
        return value;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
    
    public int getFx1(){
        return this.puntoFlecha2.x;
    }
    
    public int getFy1(){
        return this.puntoFlecha2.y;
    }
    
     public int getFx2(){
        return this.puntoFlecha1.x;
    }
    
    public int getFy2(){
        return this.puntoFlecha1.y;
    } 

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
    
    
    
    public void setValue(int value) {
        this.value = value;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }   

    @Override
    public String toString() {
        return "Arcos{" + "id=" + id + ", value=" + value + ", from=" + from + ", to=" + to + '}';
    }
    
}