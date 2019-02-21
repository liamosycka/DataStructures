package TablaBusqueda;

import conjuntistas.NodoAVL;
import lineales.dinamicas.Lista;

public class Diccionario {

	private NodoAVLDicc raiz;

	public Diccionario() {
		this.raiz = null;
	}

	public boolean insertar(Comparable clave, Object dato) {
		boolean exito = true;
		System.out.println("insertado nodo " + clave);
		if (this.raiz == null) {
			this.raiz = new NodoAVLDicc(clave, dato);
		} else {
			exito = insertarAux(this.raiz, clave, dato);
		}
		this.raiz.recalcularAltura();
		System.out.println("Altura nodo raiz" + this.raiz.getAltura());
		return exito;
	}

	private boolean insertarAux(NodoAVLDicc n, Comparable clave, Object dato) {
		boolean exito = true;
		if (clave.compareTo(n.getClave()) < 0) {
			if (n.getIzq() == null) {
				n.setIzq(new NodoAVLDicc(clave, dato));
			} else {
				exito = insertarAux(n.getIzq(), clave, dato);
			}
		} else {
			if (clave.compareTo(n.getClave()) > 0) {
				if (n.getDer() == null) {
					n.setDer(new NodoAVLDicc(clave, dato));
				} else {
					exito = insertarAux(n.getDer(), clave, dato);
				}
			} else {
				if (clave.compareTo(n.getClave()) == 0) {
					exito = false;
				}
			}
		}
		n.recalcularAltura();
		this.balancearArbol(n);
		return exito;
	}

	private void balancearArbol(NodoAVLDicc n) {
		NodoAVLDicc padre = obtenerPadre(this.raiz, n.getClave());
		int balanceN = calcularBalance(n), caidoIzquierda = 2, caidoDerecha = -2;
		System.out.println("Nodo: " + n.getClave() + " balance= " + balanceN + " altura= " + n.getAltura());
		if (balanceN == caidoIzquierda) {
			/* arbol caido a la izquierda */
			if ((calcularBalance(n.getIzq())) < 0) {
				/* rotacion doble izq ---> derecha */
				System.out.println("Rotacion doble izq derecha");
				rotacionDobleIzquierdaDerecha(n, padre);
			} else {
				/* rotacion simple a derecha */
				System.out.println("Rotacion simple a derecha");
				if (n == this.raiz) {
					this.raiz = rotacionSimpleDerecha(n);
					this.raiz.recalcularAltura();
				} else {
					NodoAVLDicc nuevaRaiz=rotacionSimpleDerecha(n);
					if(padre.getIzq()==n) {
						padre.setIzq(nuevaRaiz);
					}else {
						padre.setDer(nuevaRaiz);
						
					}
				}
			}
		} else {
			if (balanceN == caidoDerecha) {
				/* arbol caido a la derecha */
				if (calcularBalance(n.getDer()) > 0) {
					/* rotacion doble derecha izquierda */
					System.out.println("rotacion doble derecha izquierda");
					rotacionDobleDerechaIzquierda(n, padre);
				} else {
					/* rotacion simple a izquierda */
					System.out.println("Rotacion simple a izq");
					if (n == this.raiz) {
						this.raiz = rotacionSimpleIzquierda(n);
						this.raiz.recalcularAltura();
					} else {
						System.out.println("n: "+n.getClave()+" padre: "+padre.getClave());
						NodoAVLDicc nuevaRaiz=rotacionSimpleIzquierda(n);
						if(padre.getIzq()==n) {
							padre.setIzq(nuevaRaiz);
						}else {
							padre.setDer(nuevaRaiz);
							
						}
					}
				}
			}
		}
		n.recalcularAltura();
	}

	private NodoAVLDicc rotacionSimpleIzquierda(NodoAVLDicc r) {
		System.out.println("en rotacion simpl izq nodo r: " + r.getClave());
		NodoAVLDicc h, temp;
		h = r.getDer();
		//System.out.println("nodo h : " + h.getClave());
		temp = h.getIzq();
		if (temp != null) {
			System.out.println("nodo temp: " + temp.getClave());
		}
		h.setIzq(r);
		System.out.println("h.setIzq ( .." + r.getClave() + "..)");
		r.setDer(temp);
		if (temp != null) {
			System.out.println("r.setDer ( .." + temp.getClave() + "..)");
		}
		System.out.println("return h = " + h.getClave());
		return h;
	}

	private NodoAVLDicc rotacionSimpleDerecha(NodoAVLDicc r) {
		System.out.println("en rotacion simpl der nodo r: "+r.getClave());
		NodoAVLDicc h, temp;
		h = r.getIzq();
		System.out.println("nodo h: "+h.getClave());
		temp = h.getDer();
		if(temp!=null) {
			System.out.println("nodo temp: "+temp.getClave());
		}
		h.setDer(r);
		System.out.println("h.setDer( ... "+r.getClave()+"....)");
		r.setIzq(temp);
		if(temp!=null) {
			System.out.println("r.setDer(..."+temp.getClave()+"...)");
		}
		System.out.println("return h= "+h.getClave());
		return h;
	}

	private void rotacionDobleDerechaIzquierda(NodoAVLDicc r, NodoAVLDicc padre) {
		r.setDer(rotacionSimpleDerecha(r.getDer()));
		if (r == this.raiz) {
			this.raiz = rotacionSimpleIzquierda(r);
		} else {
			NodoAVLDicc nuevaRaiz=rotacionSimpleIzquierda(r);
			if(padre.getIzq()==r) {
				padre.setIzq(nuevaRaiz);
			}else {
				padre.setDer(nuevaRaiz);
			}
			//padre.setDer(rotacionSimpleIzquierda(r));
		}
	}

	private void rotacionDobleIzquierdaDerecha(NodoAVLDicc r, NodoAVLDicc padre) {
		r.setIzq(rotacionSimpleIzquierda(r.getIzq()));
		if (r == this.raiz) {
			this.raiz = rotacionSimpleDerecha(r);
		} else {
			NodoAVLDicc nuevaRaiz=rotacionSimpleDerecha(r);
			if(padre.getIzq()==r) {
				padre.setIzq(nuevaRaiz);
			}else {
				padre.setDer(nuevaRaiz);
			}
			//padre.setIzq(rotacionSimpleDerecha(r));
		}
	}

	private int calcularBalance(NodoAVLDicc n) {
		int balance = 0;
		if ((n.getIzq() == null) && (n.getDer() == null)) {
			balance = 0;
		} else {
			if (n.getIzq() != null) {

				if (n.getDer() == null) {
					balance = n.getIzq().getAltura() + 1;
				} else {
					balance = n.getIzq().getAltura() - n.getDer().getAltura();
				}
			} else {
				if (n.getDer() != null) {
					balance = -1 - n.getDer().getAltura();
				}
			}
		}
		return balance;
	}

	private NodoAVLDicc obtenerPadre(NodoAVLDicc n, Comparable clave) {
		NodoAVLDicc nodo = null;
		if (n != null) {
			if ((n.getIzq() != null && n.getIzq().getClave().compareTo(clave) == 0)
					|| (n.getDer() != null && n.getDer().getClave().compareTo(clave) == 0)) {
				nodo = n;
			} else {
				if (n.getClave().compareTo(clave) > 0) {
					nodo = obtenerPadre(n.getIzq(), clave);
				} else {
					nodo = obtenerPadre(n.getDer(), clave);
				}
			}
		}
		return nodo;
	}

	public String toString() {
		String arbol = "";

		if (this.raiz != null) {
			arbol += toStringAux(this.raiz);
		} else {
			arbol = "Arbol vacio";
		}

		return arbol;
	}

	private String toStringAux(NodoAVLDicc nodo) {
		String listado = "";

		if (nodo != null) {
			listado += "Padre: " + nodo.getClave() + " ALTURA " + nodo.getAltura();
			listado += "\n";
			if (nodo.getIzq() != null) {
				listado += "Hijo izquierdo: " + nodo.getIzq().getClave() + " ";
			} else {
				listado += "Hijo izquierdo: No tiene ";
			}
			if (nodo.getDer() != null) {
				listado += "Hijo derecho: " + nodo.getDer().getClave() + " ";
			} else {
				listado += " Hijo derecho: No tiene ";
			}

			listado += "\n";
			listado += toStringAux(nodo.getIzq());
			listado += toStringAux(nodo.getDer());
		}
		return listado;
	}

	public boolean existeClave(Comparable clave) {
		boolean exito = false;
		if (this.raiz != null) {
			if (clave.compareTo(this.raiz.getClave()) < 0) {
				exito = existeClaveAux(this.raiz.getIzq(), clave);
			} else {
				if (clave.compareTo(this.raiz.getClave()) > 0) {
					exito = existeClaveAux(this.raiz.getDer(), clave);
				} else {
					if (clave.compareTo(this.raiz.getClave()) == 0) {
						exito = true;
					}
				}
			}
		}
		return exito;
	}

	private boolean existeClaveAux(NodoAVLDicc n, Comparable clave) {
		boolean exito = false;
		if (n != null && !exito) {
			if (clave.compareTo(n.getClave()) == 0) {
				exito = true;
			} else {
				if (clave.compareTo((n.getClave())) < 0) {
					exito = existeClaveAux(n.getIzq(), clave);
				} else {
					exito = existeClaveAux(n.getDer(), clave);
				}

			}
		}
		return exito;
	}

	public Object obtenerInformacion(Comparable clave) {
		Object dato = null;
		if (existeClave(clave)) {
			if (clave.compareTo(this.raiz.getClave()) < 0) {
				dato = obtenerInfAux(this.raiz.getIzq(), clave);
			} else {
				if (clave.compareTo(this.raiz.getClave()) > 0) {
					dato = obtenerInfAux(this.raiz.getDer(), clave);
				} else {
					if (clave.compareTo(this.raiz.getClave()) == 0) {
						dato = this.raiz.getDato();
					}
				}
			}
		}
		return dato;

	}

	private Object obtenerInfAux(NodoAVLDicc n, Comparable clave) {
		Object dato = null;
		if (n != null && dato == null) {
			if (clave.compareTo(n.getClave()) == 0) {
				dato = n.getDato();
			} else {
				if (clave.compareTo((n.getClave())) < 0) {
					dato = obtenerInfAux(n.getIzq(), clave);
				} else {
					dato = obtenerInfAux(n.getDer(), clave);
				}

			}
		}
		return dato;
	}

	public Lista listarClaves() {
		Lista listaClaves = new Lista();
		if (!esVacio()) {
			listarClavesAux(this.raiz, listaClaves);
		}
		return listaClaves;
	}

	private void listarClavesAux(NodoAVLDicc n, Lista listado) {

		if (n != null) {
			if ((n.getIzq() == null) || (n.getIzq() == null && n.getDer() == null)) {
				listado.insertar(n.getClave(), listado.longitud() + 1);
			} else {
				listarClavesAux(n.getIzq(), listado);
				listado.insertar(n.getClave(), listado.longitud() + 1);
			}
			listarClavesAux(n.getDer(), listado);
		}
	}

	public boolean esVacio() {
		boolean vacio = false;
		if (this.raiz == null) {
			vacio = true;
		}
		return vacio;
	}
	
	  public Lista listarRango(Comparable min,Comparable max){
          Lista lista= new Lista();
          System.out.println("en listarRango");
          listarRangoAux(lista,this.raiz,min,max);
          return lista;
      }
      private void listarRangoAux(Lista lista,NodoAVLDicc n,Comparable min,Comparable max){
          //listar rango de mayor a menor
          if(n!=null){
              
             System.out.println("nodo : "+n.getClave());
              if(min.compareTo(n.getClave())<0)
                  listarRangoAux(lista,n.getIzq(),min,max);
                 
              if(max.compareTo(n.getClave())>0&&min.compareTo(n.getClave())<0)
                  lista.insertar(n.getClave(),lista.longitud()+1);
              if(max.compareTo(n.getClave())>0)
                  listarRangoAux(lista,n.getDer(),min,max);
}

}
}
