/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package process;

import java.util.LinkedList;

/**
 *
 * @author Kevin Martinez
 */
public class FordFulkerson {
    
    public FordFulkerson(){}
    
    public boolean pathExist(int Graph[][], int start, int end, int contenedor[], int size){
        //Vector que marcará los nodos visitados
        int [] visitado = new int[size];
        //Pila que añadirá y elimnará el nuevo inicio del camino
        LinkedList<Integer> dato = new LinkedList<Integer>();
        
        //Comienza con el valor ingresado e irá cambiando cada que se llame la función
        dato.add(start);
        //El inicio se marca como visitado
        visitado[start] = 1;
        //El nodo inidical se marca como -1 de capacidad
        contenedor[start] = -1;
        
        //Recorrerá la píla mientras tenga un dato
        while(dato.size() != 0){
            
            //Se le da el valor a la variable u y se hace pop
            int u = dato.poll();
            
            //Recorré los nodos verificando que no se hayan visitado
            for (int i = 0; i < size; i++) {
                if(visitado[i] == 0 && Graph[u][i] > 0){
                    //si es el final elimina se detiene
                    if (i == end) {
                        contenedor[i] = u;
                        return true;
                    }
                    //si no es el final se añade un nuevo dato a la pila y se marca como visitado
                    dato.add(i);
                    contenedor[i] = u;
                    visitado[i] = 1;
                }
            }
            
        }
        
        //Si no existen más caminos da un false
        return false;
    }
    
    public int calcular(int matriz[][], int start, int end, int size){
    
        //Declaración de varables
        int [][] Graph = new int[size][size];//Copia de la matriz adyacente con la cual se trabajará
        int u;
        
        //Se rellena la matriz copia
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Graph[i][j] = matriz[i][j]; 
            }
        }
        
        //Declaramos el contenedor que tendrá los caminos tomados
        int [] contenedor = new int[size];
        //Acumulador del flujo maximo
        int maxFlow = 0;
        
        //Recorrerá la matriz copia y obtendrá los caminos posibles, restando el menor de los pesos en el camino
        while(pathExist(Graph, start, end, contenedor, size)){
            
            //Se declara una variable con el maximo valor permitido
            int path_flow = Integer.MAX_VALUE;
            
            //Se obtiene el numero menor de los pesos en es camino actual
            for (int i = end; i != start; i = contenedor[i]) {
                u = contenedor[i];
                path_flow = Math.min(path_flow, Graph[u][i]);
            }
            
            //Se restan y suman los flujos de cada nodo en el camino, ya sea el de llegada como el restante del peso
            for (int i = end; i != start; i = contenedor[i]) {
                u = contenedor[i];
                Graph[u][i] -= path_flow;
                Graph[i][u] += path_flow;
            }
            //Acumulador del flujo maximo en el camino
            maxFlow += path_flow;
        }
        
        //Si existe un flujo mayor a 0, quiere decir que hay caminos
        if(maxFlow > 0){
            //Sobreescribimos la matriz original con la resultante.
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(matriz[i][j] == 0) {
                        matriz[i][j] = 0;
                    }
                    else{
                        matriz[i][j] = Graph[j][i];
                    }
                }
            }
        }
        
        return maxFlow;
            
    }
    
    
    
}
