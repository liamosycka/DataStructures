package lineales.estaticas;

import java.util.Scanner;

public class testPila {

	public static void main (String[]args) {
		Scanner sc = new Scanner(System.in);
		Pila stack=new Pila();
		int opcion=1;
		int contador=0;
		while(opcion==1) {
			stack.apilar(contador);
			opcion=sc.nextInt();
			contador+=2;
		}
		System.out.println(stack.toString());
		stack.desapilar();
		stack.desapilar();
		System.out.println(stack.toString());
		stack.apilar(566);
		System.out.println(stack.toString());
		Pila pilaClon=stack.clonar();
		System.out.println("Pila clonada: "+pilaClon.toString());
		
	}
}
