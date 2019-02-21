package DataStructures.jerarquicas;

public class NodoGen {
	private Object elem;
	private NodoGen hijoIzq;
	private NodoGen hermanoDer;

	public NodoGen(Object elem) {
		this.elem = elem;
	}

	public Object getElem() {
		return this.elem;
	}

	public NodoGen getHijoIzq() {
		return hijoIzq;
	}

	public NodoGen getHermanoDer() {
		return this.hermanoDer;
	}

	public void setElem(Object elem) {
		this.elem = elem;
	}

	public void setHijoIzq(NodoGen hijoI) {
		this.hijoIzq = hijoI;
	}

	public void setHermanoDer(NodoGen hermanoD) {
		this.hermanoDer = hermanoD;
	}
}