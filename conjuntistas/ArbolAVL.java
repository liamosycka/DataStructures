package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolAVL {

	private NodoAVL raiz;

	public ArbolAVL() {
		this.raiz = null;
	}

	public boolean insertar(Comparable elem) {
		boolean exito = true;
		System.out.println("insertado nodo " + elem);
		if (this.raiz == null) {
			this.raiz = new NodoAVL(elem);
		} else {
			exito = insertarAux(this.raiz, elem);
		}
		this.raiz.recalcularAltura();
		System.out.println("Altura nodo raiz" + this.raiz.getAltura());
		return exito;
	}

	private void balancearArbol(NodoAVL n) {
		NodoAVL padre = obtenerPadre(this.raiz, n.getElem());
		int balanceN=calcularBalance(n),caidoIzquierda=2,caidoDerecha=-2;
		System.out.println("Nodo: " + n.getElem() + " balance= " + balanceN + " altura= " + n.getAltura());
		if (balanceN== caidoIzquierda) {
			/* arbol caido a la izquierda */
			if ((calcularBalance(n.getIzq())) < 0) {
				/* rotacion doble izq ---> derecha */
				System.out.println("Rotacion doble izq derecha");
				rotacionDobleIzquierdaDerecha(n,padre);
			} else {
				/* rotacion simple a derecha */
				System.out.println("Rotacion simple a derecha");
				if (n == this.raiz) {
					this.raiz = rotacionSimpleDerecha(n);
					this.raiz.recalcularAltura();
				} else {
					padre.setIzq(rotacionSimpleDerecha(n));
				}
			}
		} else {
			if (balanceN == caidoDerecha) {
				/* arbol caido a la derecha */
				if (calcularBalance(n.getDer()) > 0) {
					/* rotacion doble derecha izquierda */
					System.out.println("rotacion doble derecha izquierda");
					rotacionDobleDerechaIzquierda(n,padre);
				} else {
					/* rotacion simple a izquierda */
					System.out.println("Rotacion simple a izq");
					if (n == this.raiz) {
						this.raiz = rotacionSimpleIzquierda(n);
						this.raiz.recalcularAltura();
					} else {
						padre.setDer(rotacionSimpleIzquierda(n));
					}
				}
			}
		}
		n.recalcularAltura();
	}

	private NodoAVL rotacionSimpleIzquierda(NodoAVL r) {
		NodoAVL h, temp;
		System.out.println("en rotacion simple izquierda nodo r = "+r.getElem());
		h = r.getDer();
		System.out.println("en rotacion simple izquierda nodo h= r.getDer() = "+h.getElem());
		temp = h.getIzq();
		h.setIzq(r);
		r.setDer(temp);
		return h;
	}

	private NodoAVL rotacionSimpleDerecha(NodoAVL r) {
		NodoAVL h, temp;
		h = r.getIzq();
		temp = h.getDer();
		h.setDer(r);
		r.setIzq(temp);
		return h;
	}
	private  void rotacionDobleDerechaIzquierda(NodoAVL r,NodoAVL padre) {
		r.setDer(rotacionSimpleDerecha(r.getDer()));
		if(r==this.raiz) {
			this.raiz=rotacionSimpleIzquierda(r);
		}else {
			padre.setDer(rotacionSimpleIzquierda(r));
		}
	}
	private void rotacionDobleIzquierdaDerecha(NodoAVL r, NodoAVL padre) {
		r.setIzq(rotacionSimpleIzquierda(r.getIzq()));
		if(r==this.raiz) {
			this.raiz=rotacionSimpleDerecha(r);
		}else {
			padre.setIzq(rotacionSimpleDerecha(r));
		}
	}

	private int calcularBalance(NodoAVL n) {
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

//	private boolean insertarAux(NodoAVL n, Comparable elem) {
//		boolean exito = true;
//		if (n.getElem().compareTo(elem) == 0) {
//			exito = false;
//		} else if (elem.compareTo(n.getElem()) < 0) {
//			// baja por izquierda
//			if (n.getIzq() == null) {
//				n.setIzq(new NodoAVL(elem));
//				n.recalcularAltura();
//			} else {
//				exito = insertarAux(n.getIzq(), elem);
//			}
//		} else if (n.getDer() == null) {
//			n.setDer(new NodoAVL(elem));
//			n.recalcularAltura();
//		} else {
//			exito = insertarAux(n.getDer(), elem);
//		}
//		n.recalcularAltura();
//		this.balancearArbol(n);
//		return exito;
//	}
	private boolean insertarAux(NodoAVL n,Comparable elem) {
		boolean exito=true;
		if(elem.compareTo(n.getElem())<0) {
			if(n.getIzq()==null) {
				n.setIzq(new NodoAVL(elem));
			}else {
				exito=insertarAux(n.getIzq(),elem);
			}
		}else {
			if(elem.compareTo(n.getElem())>0) {
				if(n.getDer()==null) {
					n.setDer(new NodoAVL(elem));
				}else {
					exito=insertarAux(n.getDer(),elem);
				}
			}else {
				if(elem.compareTo(n.getElem())==0) {
					exito=false;
				}
			}
		}
		n.recalcularAltura();
		this.balancearArbol(n);
		return exito;
	}

	private NodoAVL obtenerPadre(NodoAVL n, Comparable elem) {
		NodoAVL nodo = null;
		if (n != null) {
			if ((n.getIzq() != null && n.getIzq().getElem().compareTo(elem) == 0)
					|| (n.getDer() != null && n.getDer().getElem().compareTo(elem) == 0)) {
				nodo = n;
			} else {
				if (n.getElem().compareTo(elem) > 0) {
					nodo = obtenerPadre(n.getIzq(), elem);
				} else {
					nodo = obtenerPadre(n.getDer(), elem);
				}
			}
		}
		return nodo;
	}

	public boolean pertenece(Comparable elem) {
		boolean pertenece = perteneceAux(this.raiz, elem);

		return pertenece;
	}

	private boolean perteneceAux(NodoAVL n, Comparable elem) {
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

	private Comparable obtenerCandidato(NodoAVL n) {
		Comparable candidato = null;

		while (n != null) {
			candidato = n.getDer().getElem();
		}

		return candidato;
	}

	public boolean eliminar(Comparable elem) {
		boolean exito = false;
		if (this.raiz.getElem().compareTo(elem) == 0) {
			if (this.raiz.getDer() == null && this.raiz.getIzq() == null) {
				this.raiz = null;
			} else {
				if (this.raiz.getDer() != null && this.raiz.getIzq() == null) {
					this.raiz = this.raiz.getIzq();
				} else {
					if (this.raiz.getIzq() != null && this.raiz.getDer() == null) {
						this.raiz = this.raiz.getDer();
					} else {
						System.out.println("Eliminar caso 3");
						eliminarCasoTres(this.raiz);
					}
				}
				this.raiz.recalcularAltura();
				balancearArbol(this.raiz);
			}
		} else {

			if (elem.compareTo(this.raiz.getElem()) < 0) {
				exito = eliminarAux(this.raiz.getIzq(), elem, this.raiz);
			} else {
				exito = eliminarAux(this.raiz.getDer(), elem, this.raiz);
			}
		}
		return exito;
	}

	private boolean eliminarAux(NodoAVL n, Comparable elem, NodoAVL padre) {
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
						if (n.getDer() != null && n.getIzq() != null) {
							eliminarCasoTres(n);
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
		padre.recalcularAltura();
		balancearArbol(padre);
		return exito;
	}

	private void eliminarCasoUno(NodoAVL n, NodoAVL padre) {
		if (padre.getIzq() == n) {
			padre.setIzq(null);
		} else {
			padre.setDer(null);
		}
	}

	private void eliminarCasoDos(NodoAVL n, NodoAVL padre, boolean derecha) {
		if (derecha) {
			if (n.getDer() != null) {
				// el nodo N tiene un solo hijo derecho
				padre.setDer(n.getDer());
			} else {
				padre.setDer(n.getIzq());
			}

		} else {
			if (n.getDer() != null) {
				// el nodo N tiene un solo hijo derecho
				padre.setIzq(n.getDer());
			} else {
				padre.setIzq(n.getIzq());
			}
		}

	}

	private void eliminarCasoTres(NodoAVL n) {
		NodoAVL[] arrNodos = buscarCandidato(n.getIzq(), n);
		NodoAVL candidato = arrNodos[0];
		NodoAVL padreCandidato = arrNodos[1];
		n.setElem((candidato.getElem()));
		if (candidato.getIzq() != null) {
			eliminarCasoDos(candidato, padreCandidato, false);
		} else {
			eliminarCasoUno(candidato, padreCandidato);
		}

	}

	private NodoAVL[] buscarCandidato(NodoAVL n, NodoAVL padre) {
		NodoAVL candidato = n;
		NodoAVL father = padre;
		NodoAVL[] arrNodos = new NodoAVL[2];

		while (candidato.getDer() != null) {
			father = candidato;
			candidato = candidato.getDer();
		}
		arrNodos[0] = candidato;
		arrNodos[1] = father;
		return arrNodos;
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

	private String toStringAux(NodoAVL nodo) {
		String listado = "";

		if (nodo != null) {
			listado += "Padre: " + nodo.getElem() + " ALTURA " + nodo.getAltura();
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

	private NodoAVL obtenerNodo(NodoAVL n, Comparable elem) {
		NodoAVL nuevo = null;
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
	/*
	 * // modulos simulacro parcial ////////////////////////////////////// public
	 * void eliminarMinimo() { recEliminarMinimo(this.raiz.getIzq(), this.raiz); }
	 * 
	 * private void recEliminarMinimo(NodoArbol n, NodoArbol padre) { if (n != null)
	 * { if (n.getIzq() == null) { if (n.getDer() != null) {
	 * padre.setIzq(n.getDer()); } else { padre.setIzq(null); } } else {
	 * recEliminarMinimo(n.getIzq(), n); } } }
	 * 
	 * public Lista listarMayorIgual(Comparable elem) { Lista lista = new Lista();
	 * recListarMayorIgual(lista, this.raiz, elem); return lista;
	 * 
	 * } /* private NodoArbol obtenerNodoElem(NodoArbol n, Comparable elem){
	 * NodoArbol subArbol=null; if(n.getElem().compareTo(elem)==0){ subArbol=n;
	 * }else{ if(elem.compareTo(n.getElem())>0){ obtenerNodoElem(n.getDer(),elem);
	 * }else{ obtenerNodoElem(n.getIzq(),elem); } } return subArbol; }
	 * 
	 * 
	 * private void recListarMayorIgual(Lista lista, NodoArbol n, Comparable elem) {
	 * if (n != null) { if (n.getElem().compareTo(elem) > 0) { if (n.getDer() !=
	 * null) { recListarMayorIgual(lista, n.getDer(), elem);
	 * lista.insertar(n.getElem(), lista.longitud() + 1); recListarMayorIgual(lista,
	 * n.getIzq(), elem); } else { lista.insertar(n.getElem(), lista.longitud() +
	 * 1);
	 * 
	 * recListarMayorIgual(lista, n.getIzq(), elem); } } else { if (n.getDer() !=
	 * null) { recListarMayorIgual(lista, n.getDer(), elem); } } } }
	 * 
	 * 
	 * public ArbolBB clonarParteInvertida(Comparable elem) { ArbolBB arbolClon =
	 * new ArbolBB(); clonarParteInvertidaAux(this.raiz, arbolClon, elem); return
	 * arbolClon; }
	 * 
	 * private void clonarParteInvertidaAux(NodoArbol n, ArbolBB clon, Comparable
	 * elem) { NodoArbol nuevaRaiz = obtenerNodo(n, elem); NodoArbol clonAux = new
	 * NodoArbol(nuevaRaiz.getElem(), nuevaRaiz.getDer(), nuevaRaiz.getIzq()); }
	 * 
	 * public Lista listarMayores(Comparable elem) { Lista lista = new Lista();
	 * listarMayoresAux(lista, this.raiz, elem); return lista; }
	 * 
	 * private void listarMayoresAux(Lista lista, NodoArbol n, Comparable elem) { //
	 * como siempre bajo a la derecha, me conviene solo preguntar cuando bajo a la
	 * // izquierda y cuando inserto if (n != null) {
	 * System.out.println("llama con " + n.getElem()); if
	 * (elem.compareTo(n.getElem()) < 0 || elem.compareTo(n.getElem()) == 0) {
	 * listarMayoresAux(lista, n.getDer(), elem); lista.insertar(n.getElem(),
	 * lista.longitud() + 1); listarMayoresAux(lista, n.getIzq(), elem);
	 * 
	 * } else { listarMayoresAux(lista, n.getDer(), elem); } } }
	 * 
	 * public Lista listarMenores(Comparable elem) { Lista lista = new Lista();
	 * listarMenoresAux(lista, this.raiz, elem); return lista; }
	 * 
	 * private void listarMenoresAux(Lista lista, NodoArbol n, Comparable elem) { if
	 * (n != null) { System.out.println("entra con : " + n.getElem()); if
	 * (n.getElem().compareTo(elem) > 0) { listarMenoresAux(lista, n.getIzq(),
	 * elem); } if (n.getElem().compareTo(elem) < 0) { lista.insertar(n.getElem(),
	 * lista.longitud() + 1); listarMenoresAux(lista, n.getIzq(), elem);
	 * listarMenoresAux(lista, n.getDer(), elem); } } }
	 * 
	 * public Lista listarRango(Comparable min, Comparable max) { Lista lista = new
	 * Lista(); listarRangoAux(lista, this.raiz, min, max); return lista; }
	 * 
	 * private void listarRangoAux(Lista lista, NodoArbol n, Comparable min,
	 * Comparable max) { // listar rango de mayor a menor if (n != null) {
	 * 
	 * System.out.println("nodo : " + n.getElem()); if (min.compareTo(n.getElem()) <
	 * 0) listarRangoAux(lista, n.getIzq(), min, max);
	 * 
	 * if (max.compareTo(n.getElem()) > 0 && min.compareTo(n.getElem()) < 0)
	 * lista.insertar(n.getElem(), 1); if (max.compareTo(n.getElem()) > 0)
	 * listarRangoAux(lista, n.getDer(), min, max); }
	 * 
	 * // Listar rango de menor a mayor /* if(n!=null){
	 * if(max.compareTo(n.getElem())>0) listarRangoAux(lista,n.getDer(),min,max);
	 * if(max.compareTo(n.getElem())>0&&min.compareTo(n.getElem())<0)
	 * lista.insertar(n.getElem(),1); if(min.compareTo(n.getElem())<0)
	 * listarRangoAux(lista,n.getIzq(),min,max); }
	 * 
	 * 
	 * }
	 */

}
