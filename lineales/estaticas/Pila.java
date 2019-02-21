package lineales.estaticas;

public class Pila {

	private static final int TAM = 20;
	private Object[] arrPila;
	private int tope;

	public Pila() {
		this.arrPila = new Object[TAM];
		this.tope = -1;
	}

	/* Metodos */
	public boolean apilar(Object nuevoElem) {
		boolean exito = false;
		/* verifico si la pila NO esta llena para apilar */
		if (!(this.tope + 1 >= TAM)) {
			this.tope++;
			arrPila[tope] = nuevoElem;
			exito = true;
		}
		return exito;
	}

	public boolean desapilar() {
		boolean exito = false;
		if (!(this.tope == -1)) {
			arrPila[tope] = null;
			this.tope--;
			exito = true;
		}
		return exito;
	}

	public Object obtenerTope() {
		Object elemTope;
		if (this.tope == -1) {
			elemTope = null;
		} else {
			elemTope = this.arrPila[tope];
		}
		return elemTope;
	}

	public boolean esVacia() {
		return (this.tope == -1);
	}

	public void vaciar() {
		this.tope = -1;
	}

	public Pila clonar() {
		Pila pilaClon = new Pila();
		pilaClon.tope = this.tope;
		for (int i = 0; i <= this.tope; i++) {
			pilaClon.arrPila[i] = this.arrPila[i];
		}
		return pilaClon;
	}

	public String toString() {
		String cadena="[ ";
		for(int i=0;i<=this.tope;i++) {
			cadena+=this.arrPila[i]+" , ";
		}
		cadena+=" ]";
		return cadena;
	}

}
