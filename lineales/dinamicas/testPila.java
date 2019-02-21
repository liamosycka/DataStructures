package lineales.dinamicas;

import java.util.Scanner;


public class testPila {

	public static void main (String[]args) {
		Scanner sc = new Scanner(System.in);
		PilaD stack=new PilaD();
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
		PilaD pilaClon=stack.clonar();
		System.out.println("Pila clonada: "+pilaClon.toString());
		
	}
}