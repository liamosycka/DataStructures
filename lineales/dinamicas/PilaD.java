package lineales.dinamicas;

public class PilaD {
	private Nodo tope;

	public PilaD() {
		this.tope = null;
	}

	public boolean apilar(Object nuevoElem) {
		Nodo nodo = new Nodo(nuevoElem, this.tope);
		this.tope = nodo;
		return true;
	}

	public boolean desapilar() {
		boolean exito=false;
		if (!esVacia()) {
			this.tope = this.tope.getEnlace();
			exito=true;
		}
		return exito;
	}
	public boolean esVacia() {
		boolean vacia=false;
		if(this.tope==null) {
			vacia=true;
		}
		return vacia;
	}
	public void vaciar() {
		this.tope=null;
	}
	public PilaD clonar() {
		PilaD pilaClon=new PilaD();
		pilaClon.tope=cloneNodo(this.tope);
		return pilaClon;
	}
	private Nodo cloneNodo(Nodo nod) {
		Nodo aux=null;
		if(nod.getEnlace()==null) {	
			aux=new Nodo(nod.getElem());
		}else {
			aux=new Nodo(nod.getElem(),cloneNodo(nod.getEnlace()));
		}
		return aux;
	}
	public String toString() {
		String cad="";
		if(this.tope==null)
			cad="Pila vacia";
		else {
			cad="[";
			Nodo aux=this.tope;
			while(aux!=null) {
				cad+=aux.getElem();
				aux=aux.getEnlace();
				if(aux!=null)
					cad+=",";
			}
			cad+="]";
		}
		return cad;
	}

}
