package conjuntistas;

import lineales.dinamicas.Lista;
import TablaBusqueda.*;
public class TestArbolBB {

	public static void main(String[] args) {

		
		
		ArbolAVL arb=new ArbolAVL();
		arb.insertar(8);
		arb.insertar(5);
		arb.insertar(15);
		arb.insertar(13);
		arb.insertar(20);
		arb.insertar(29);
		
		System.out.println(arb.toString());
		
		arb.eliminar(20);
		System.out.println("desp de elminar"+arb.toString());
	}
}
