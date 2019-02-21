package DataStructures.grafoEtiq;

import java.util.HashMap;
import java.util.Vector;

import lineales.dinamicas.Lista;

public class testGrafo {

	public static void main(String [] args) {
		Grafo grafo= new Grafo();
		grafo.insertarVertice('A');
		grafo.insertarVertice('B');
		grafo.insertarVertice('C');
                grafo.insertarVertice('D');
                grafo.insertarVertice('E');
                grafo.insertarVertice('F');
                grafo.insertarArco('A', 'F', 0);
		grafo.insertarArco('A','C',5);
                grafo.insertarArco('F', 'C', 0);
                grafo.insertarArco('E','C' , 0);
                
		grafo.insertarArco('A','B',20);
		grafo.insertarArco('A','D',25);
                grafo.insertarArco('B','D',10);
              
                grafo.insertarArco('C','D',5);
		System.out.println(grafo.caminoMenosNodos('A','F').toString());
}
}
