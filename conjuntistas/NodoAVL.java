package conjuntistas;

public class NodoAVL {

	private Comparable elem;
	private int altura;
	private NodoAVL hijoIzq;
	private NodoAVL hijoDer;

	public NodoAVL(Comparable elemento) {
		elem = elemento;
		hijoIzq = null;
		hijoDer = null;
		altura = 0;
	}

	public Comparable getElem() {
		return elem;
	}

	public void setElem(Comparable nuevo) {
		elem = nuevo;
	}

	public int getAltura() {
		return altura;
	}

//	private int recalcularAltura(NodoAVL n) {
//		int alturaIzq, alturaDer, altura3 = -1;
//		if (n != null) {
//			System.out.println("lian");
//			if (n.getDer() == null && n.getIzq() == null) {
//				altura3 = 0;
//			} else {
//				/* calcula la altura del subarbol derecho */
//				if (n.getDer() != null) {
//					alturaDer = ((recalcularAltura(n.getDer()))) + 1;
//				} else {
//					alturaDer = 0;
//				}
//				if (n.getIzq() != null) {
//					alturaIzq = ((recalcularAltura(n.getIzq()))) + 1;
//				} else {
//					alturaIzq = 0;
//				}
//				if (alturaDer > alturaIzq) {
//					altura3 = alturaDer;
//				} else {
//					altura3 = alturaIzq;
//				}
//			}
//		}
//		return altura3;
//	}

	public void recalcularAltura() {
		//altura = recalcularAltura(this);
		if(this.getIzq()!=null) {
			if(this.getDer()!=null) {
				altura=1+Math.max(this.getDer().getAltura(), this.getIzq().getAltura());
			}else {
				altura=1+this.getIzq().getAltura();
			}
		}else {
			if(this.getDer()!=null) {
				altura=1+this.getDer().getAltura();
			}else {
				if(this.getDer()==null) {
					altura=0;
				}
			}
		}
	}

	public NodoAVL getIzq() {
		return hijoIzq;
	}

	public NodoAVL getDer() {
		return hijoDer;
	}

	public void setIzq(NodoAVL izquierdo) {
		hijoIzq = izquierdo;
		if (izquierdo != null) {
			izquierdo.recalcularAltura();
		}
		this.recalcularAltura();
	}

	public void setDer(NodoAVL derecho) {
		hijoDer = derecho;
		if (derecho != null) {
			derecho.recalcularAltura();
		}
		this.recalcularAltura();
	}
}
