package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolBB {

	private NodoArbol raiz;

	public ArbolBB() {
		this.raiz = null;
	}

	public boolean insertar(Comparable elem) {
		boolean exito = true;
		if (this.raiz == null) {
			this.raiz = new NodoArbol(elem);
		} else {
			exito = insertarAux(this.raiz, elem);
		}
		return exito;
	}

	private boolean insertarAux(NodoArbol n, Comparable elem) {
		boolean exito = true;
		if (n.getElem().compareTo(elem) == 0) {
			exito = false;
		} else if (elem.compareTo(n.getElem()) < 0) {
			// baja por izquierda
			if (n.getIzq() == null) {
				n.setIzq(new NodoArbol(elem));
			} else {
				exito = insertarAux(n.getIzq(), elem);
			}
		} else if (n.getDer() == null) {
			n.setDer(new NodoArbol(elem));
		} else {
			exito = insertarAux(n.getDer(), elem);
		}
		return exito;
	}

	public boolean pertenece(Comparable elem) {
		boolean pertenece = perteneceAux(this.raiz, elem);

		return pertenece;
	}

	private boolean perteneceAux(NodoArbol n, Comparable elem) {
		boolean pertenece = false;
		if (!pertenece) {
			if (n != null) {
				if (n.getElem().compareTo(elem) == 0) {
					// el elem es la raiz
					pertenece = true;
				} else if (elem.compareTo(n.getElem()) < 0) {
					pertenece = perteneceAux(n.getIzq(), elem);
				} else {
					pertenece = perteneceAux(n.getDer(), elem);
				}
			}
		}
		return pertenece;
	}

	private Comparable obtenerCandidato(NodoArbol n) {
		Comparable candidato = null;

		while (n != null) {
			candidato = n.getDer().getElem();
		}

		return candidato;
	}

	public boolean eliminar(Comparable elem) {
		boolean exito = false;
		if (elem.compareTo(this.raiz.getElem()) < 0) {
			exito = eliminarAux(this.raiz.getIzq(), elem, this.raiz);
		} else {
			exito = eliminarAux(this.raiz.getDer(), elem, this.raiz);
		}
		return exito;
	}

	private boolean eliminarAux(NodoArbol n, Comparable elem, NodoArbol padre) {
		boolean exito = false;
		if (n != null) {
			if (!exito) {
				if (elem.compareTo(n.getElem()) == 0) {
					// Verifico si el nodo es hoja
					if (n.getIzq() == null && n.getDer() == null) {
						// el nodo es hoja
						eliminarCasoUno(n, padre);

						exito = true;
						// El nodo tiene almenos un hijo
					} else {
						if (n.getElem().compareTo(padre.getElem()) > 0) {
							/*
							 * el nodo N es mayor al padre, por lo tanto se encuentra a su derecha Verifico
							 * si el nodo tiene 1 hijo
							 */
							eliminarCasoDos(n, padre, true);
						} else {
							// el nodo N es menor al padre, por lo tanto se encuentra a su izquierda
							eliminarCasoDos(n, padre, false);
						}
						exito = true;

					}
				} else {

					if (elem.compareTo(n.getElem()) < 0) {
						exito = eliminarAux(n.getIzq(), elem, n);
					} else {
						exito = eliminarAux(n.getDer(), elem, n);
					}
				}
			}

		}
		return exito;
	}

	private void eliminarCasoUno(NodoArbol n, NodoArbol padre) {
		if (padre.getIzq() == n) {
			padre.setIzq(null);
		} else {
			padre.setDer(null);
		}
	}

	private void eliminarCasoDos(NodoArbol n, NodoArbol padre, boolean derecha) {
		if (derecha) {
			if (n.getIzq() == null && n.getDer() != null) {
				// el nodo N tiene un solo hijo derecho
				padre.setDer(n.getDer());
			}
			if (n.getIzq() != null && n.getDer() == null) {
				// el nodo tiene un solo hijo izquierdo
				padre.setDer(n.getIzq());
			}
		} else {

		}

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

	private String toStringAux(NodoArbol nodo) {
		String listado = "";

		if (nodo != null) {
			listado += "Padre: " + nodo.getElem();
			listado += "\n";
			if (nodo.getIzq() != null) {
				listado += "Hijo izquierdo: " + nodo.getIzq().getElem() + " ";
			} else {
				listado += "Hijo izquierdo: No tiene ";
			}
			if (nodo.getDer() != null) {
				listado += "Hijo derecho: " + nodo.getDer().getElem() + " ";
			} else {
				listado += " Hijo derecho: No tiene ";
			}

			listado += "\n";
			listado += toStringAux(nodo.getIzq());
			listado += toStringAux(nodo.getDer());
		}
		return listado;
	}

	// modulos simulacro parcial
	//////////////////////////////////////
	public void eliminarMinimo() {
		recEliminarMinimo(this.raiz.getIzq(), this.raiz);
	}

	private void recEliminarMinimo(NodoArbol n, NodoArbol padre) {
		if (n != null) {
			if (n.getIzq() == null) {
				if (n.getDer() != null) {
					padre.setIzq(n.getDer());
				} else {
					padre.setIzq(null);
				}
			} else {
				recEliminarMinimo(n.getIzq(), n);
			}
		}
	}

	public Lista listarMayorIgual(Comparable elem) {
		Lista lista = new Lista();
		recListarMayorIgual(lista, this.raiz, elem);
		return lista;

	}
	/*
	 * private NodoArbol obtenerNodoElem(NodoArbol n, Comparable elem){ NodoArbol
	 * subArbol=null; if(n.getElem().compareTo(elem)==0){ subArbol=n; }else{
	 * if(elem.compareTo(n.getElem())>0){ obtenerNodoElem(n.getDer(),elem); }else{
	 * obtenerNodoElem(n.getIzq(),elem); } } return subArbol; }
	 */

	private void recListarMayorIgual(Lista lista, NodoArbol n, Comparable elem) {
		if (n != null) {
			if (n.getElem().compareTo(elem) > 0) {
				if (n.getDer() != null) {
					recListarMayorIgual(lista, n.getDer(), elem);
					lista.insertar(n.getElem(), lista.longitud() + 1);
					recListarMayorIgual(lista, n.getIzq(), elem);
				} else {
					lista.insertar(n.getElem(), lista.longitud() + 1);

					recListarMayorIgual(lista, n.getIzq(), elem);
				}
			} else {
				if (n.getDer() != null) {
					recListarMayorIgual(lista, n.getDer(), elem);
				}
			}
		}
	}

	private NodoArbol obtenerNodo(NodoArbol n, Comparable elem) {
		NodoArbol nuevo = null;
		if (n != null) {
			if (n.getElem().compareTo(elem) == 0) {
				nuevo = n;
			} else {
				if (n.getElem().compareTo(elem) > 0) {
					obtenerNodo(n.getIzq(), elem);
				} else {
					obtenerNodo(n.getDer(), elem);
				}
			}
		}
		return nuevo;
	}

	public ArbolBB clonarParteInvertida(Comparable elem) {
		ArbolBB arbolClon = new ArbolBB();
		clonarParteInvertidaAux(this.raiz, arbolClon, elem);
		return arbolClon;
	}

	private void clonarParteInvertidaAux(NodoArbol n, ArbolBB clon, Comparable elem) {
		NodoArbol nuevaRaiz = obtenerNodo(n, elem);
		NodoArbol clonAux = new NodoArbol(nuevaRaiz.getElem(), nuevaRaiz.getDer(), nuevaRaiz.getIzq());
	}

	public Lista listarMayores(Comparable elem) {
		Lista lista = new Lista();
		listarMayoresAux(lista, this.raiz, elem);
		return lista;
	}

	private void listarMayoresAux(Lista lista, NodoArbol n, Comparable elem) {
		// como siempre bajo a la derecha, me conviene solo preguntar cuando bajo a la
		// izquierda y cuando inserto
		if (n != null) {
			System.out.println("llama con " + n.getElem());
			if (elem.compareTo(n.getElem()) < 0 || elem.compareTo(n.getElem()) == 0) {
				listarMayoresAux(lista, n.getDer(), elem);
				lista.insertar(n.getElem(), lista.longitud() + 1);
				listarMayoresAux(lista, n.getIzq(), elem);

			} else {
				listarMayoresAux(lista, n.getDer(), elem);
			}
		}
	}

	public Lista listarMenores(Comparable elem) {
		Lista lista = new Lista();
		listarMenoresAux(lista, this.raiz, elem);
		return lista;
	}

	private void listarMenoresAux(Lista lista, NodoArbol n, Comparable elem) {
		if (n != null) {
			System.out.println("entra con : " + n.getElem());
			if (n.getElem().compareTo(elem) > 0) {
				listarMenoresAux(lista, n.getIzq(), elem);
			}
			if (n.getElem().compareTo(elem) < 0) {
				lista.insertar(n.getElem(), lista.longitud() + 1);
				listarMenoresAux(lista, n.getIzq(), elem);
				listarMenoresAux(lista, n.getDer(), elem);
			}
		}
	}

	public Lista listarRango(Comparable min, Comparable max) {
		Lista lista = new Lista();
		listarRangoAux(lista, this.raiz, min, max);
		return lista;
	}

	private void listarRangoAux(Lista lista, NodoArbol n, Comparable min, Comparable max) {
		// listar rango de mayor a menor
		if (n != null) {

			System.out.println("nodo : " + n.getElem());
			if (min.compareTo(n.getElem()) < 0)
				listarRangoAux(lista, n.getIzq(), min, max);

			if (max.compareTo(n.getElem()) > 0 && min.compareTo(n.getElem()) < 0)
				lista.insertar(n.getElem(), 1);
			if (max.compareTo(n.getElem()) > 0)
				listarRangoAux(lista, n.getDer(), min, max);
		}

		// Listar rango de menor a mayor
		/*
		 * if(n!=null){ if(max.compareTo(n.getElem())>0)
		 * listarRangoAux(lista,n.getDer(),min,max);
		 * if(max.compareTo(n.getElem())>0&&min.compareTo(n.getElem())<0)
		 * lista.insertar(n.getElem(),1); if(min.compareTo(n.getElem())<0)
		 * listarRangoAux(lista,n.getIzq(),min,max); }
		 */
	}
}
