package lineales.estaticas;

import java.util.Scanner;

public class testCola {
	public static void main (String[]args) {
		Scanner sc = new Scanner(System.in);
		Cola q=new Cola();
		int opcion=1;
		int contador=0;
		while(opcion==1) {
			q.poner(contador);
			opcion=sc.nextInt();
			contador+=2;
		}
		System.out.println(q.toString());
		q.sacar();
		q.sacar();
		System.out.println(q.toString());
		q.poner(565);
		System.out.println(q.toString());
		Cola qClon=q.clonar();
		System.out.println("Pila clonada: "+qClon.toString());
	}

}
