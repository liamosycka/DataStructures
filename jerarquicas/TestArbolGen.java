package DataStructures.jerarquicas;

import lineales.dinamicas.Lista;

public class TestArbolGen {

	public static void main(String[] args) {
		ArbolGen arb=new ArbolGen();
		arb.insertarPrimera('A', 'X');
		arb.insertarPrimera('B', 'A');
		arb.insertarUltima('C','A');
		arb.insertarPrimera('D','C');
		arb.insertarUltima('E','C');
		arb.insertarPrimera('F','E');
		
		

		Lista lista=new Lista();
                lista.insertar('A', 1);
                lista.insertar('C', 2);
                lista.insertar('F', 3);
                
		System.out.println(arb.verifCaminoNivel(lista, 3));
	}
}
