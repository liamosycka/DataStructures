package DataStructures.jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    private NodoGen obtenerNodo(NodoGen n, Object buscado) {
        NodoGen resultado = null;
        NodoGen h;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                h = n.getHijoIzq();
                while (h != null && resultado == null) {
                    resultado = obtenerNodo(h, buscado);
                    h = h.getHermanoDer();
                }
            }
        }
        return resultado;
    }

    public boolean insertarPrimera(Object elem, Object padre) {
        boolean exito = false;
        NodoGen nuevo;
        NodoGen father = obtenerNodo(this.raiz, padre);
        if (this.raiz == null) {
            nuevo = new NodoGen(elem);
            this.raiz = nuevo;
        } else if (father != null) {
            nuevo = new NodoGen(elem);
            nuevo.setHermanoDer((NodoGen) father.getHijoIzq());
            father.setHijoIzq(nuevo);
            exito = true;
        }
        return exito;
    }

    public boolean insertarUltima(Object elem, Object padre) {
        boolean exito = false;
        NodoGen nuevo;
        if (this.raiz == null) {
            nuevo = new NodoGen(elem);
            this.raiz = nuevo;
        } else {
            NodoGen father = obtenerNodo(this.raiz, padre);

            if (father != null) {
                nuevo = new NodoGen(elem);
                if (father.getHijoIzq() == null) {
                    father.setHijoIzq(nuevo);
                } else {
                    NodoGen h = father.getHijoIzq();
                    while (h.getHermanoDer() != null) {
                        h = h.getHermanoDer();
                    }
                    h.setHermanoDer(nuevo);
                }
                exito = true;
            }
        }
        return exito;
    }

    public boolean pertenece(Object elem) {
        boolean encontrado = false;
        if (obtenerNodo(this.raiz, elem) != null) {
            encontrado = true;
        }
        return encontrado;
    }

    public int nivel(Object elem) {
        int nivel = -1;
        // retorna -1 si el nodo no se encontr�
        if (this.pertenece(elem)) {
            nivel = nivelAux(this.raiz, elem, 0);
        }
        return nivel;
    }

    private int nivelAux(NodoGen n, Object elem, int nivActual) {
        int lvl = 0;
        NodoGen h;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                lvl = nivActual;
            } else {
                h = n.getHijoIzq();
                //la condicion de lvl==0 esta para cortar el while cuando el elemento haya sido encontrado.
                while (h != null && lvl == 0) {
                    lvl = nivelAux(h, elem, nivActual + 1);
                    h = h.getHermanoDer();
                }
            }
        }
        return lvl;
    }

    public Lista testLista(Object elem) {
        Lista lista = new Lista();
        lista.insertar(elem, lista.longitud() + 1);
        return lista;
    }

    public Lista ancestros(Object elem) {
        Lista lista = new Lista();
        ancestrosAux(this.raiz, elem, lista);
        return lista;
    }

    private boolean ancestrosAux(NodoGen n, Object elem, Lista lista) {
        boolean encontrado = false;
        if (n != null) {
            lista.insertar(n.getElem(), lista.longitud() + 1);
            if (n.getElem().equals(elem)) {
                encontrado = true;
            } else {
                encontrado = ancestrosAux(n.getHijoIzq(), elem, lista);
                if (n.getHijoIzq() != null) {
                    NodoGen h = n.getHijoIzq().getHermanoDer();
                    while (h != null && !encontrado) {
                        encontrado = ancestrosAux(h, elem, lista);
                        h = h.getHermanoDer();
                    }
                }

                if (!encontrado) {
                    lista.eliminar(lista.longitud());
                }
            }
        }
        return encontrado;
    }

    public Lista listarInorden() {
        Lista lista = new Lista();
        listarInordenAux(this.raiz, lista);
        return lista;
    }

    private void listarInordenAux(NodoGen n, Lista lista) {
        if (n != null) {
            if (n.getHijoIzq() != null) {
                listarInordenAux(n.getHijoIzq(), lista);
            }
            lista.insertar(n.getElem(), lista.longitud() + 1);
            if (n.getHijoIzq() != null) {
                NodoGen h = n.getHijoIzq();
                while (h.getHermanoDer() != null) {
                    listarInordenAux(h.getHermanoDer(), lista);
                    h = h.getHermanoDer();
                }
            }
        }
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = ";";
        if (n != null) {
            // visita el nodo n
            s += n.getElem().toString() + " ->";
            NodoGen hijo = (NodoGen) n.getHijoIzq();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = (NodoGen) hijo.getHermanoDer();
            }
            // comiienza recorrido de los hijos de n llamado recursivamente
            // para que cada hijo agregue su subcadena a la general
            hijo = (NodoGen) n.getHijoIzq();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = (NodoGen) hijo.getHermanoDer();
            }
        }
        return s;
    }

    // modulos simulacro parcial
    ///////////////////////////
//	public boolean verificarCamino(Lista lista) {
//		boolean exito;
//		exito = recVerifCamino(lista, this.raiz, 1);
//		return exito;
//	}
//
//	private boolean recVerifCamino(Lista lista, NodoGen n, int i) {
//		boolean exito = false;
//		if (n != null) {
//			System.out.println("entra con n: "+n.getElem());
//
//			if (n.getElem().equals(lista.recuperar(i))) {
//				i = i + 1;
//				if (lista.recuperar(i) == null) {
//					// la lista termin�
//					exito = true;
//				} else {
//					exito = recVerifCamino(lista, n.getHijoIzq(), i);
//					if (n.getHijoIzq() != null) {
//						NodoGen h = n.getHijoIzq().getHermanoDer();
//						while (h != null && !exito) {
//							exito = recVerifCamino(lista, h, i);
//							h = h.getHermanoDer();
//						}
//					}
//				}
//			}
//		}
//
//		return exito;
//
//	}
    // 1 c
    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista lista = new Lista();
        recListarEntreNiveles(lista, this.raiz, niv1, niv2, 0);
        return lista;
    }

//	private void recListarEntreNiveles(Lista lista, NodoGen n, int niv1, int niv2, int nivActual) {
//		if (n != null) {
//			System.out.println("entra con el nodo : " + n.getElem());
//			if (nivActual <= niv2) {
//				recListarEntreNiveles(lista, n.getHijoIzq(), niv1, niv2, nivActual + 1);
//				if (nivActual >= niv1) {
//					lista.insertar(n.getElem(), lista.longitud() + 1);
//				}
//				if (n.getHijoIzq() != null) {
//					NodoGen h = n.getHijoIzq().getHermanoDer();
//					while (h != null) {
//						recListarEntreNiveles(lista, h, niv1, niv2, nivActual + 1);
//						h = h.getHermanoDer();
//					}
//				}
//			}
//		}
//
//	}
    private void recListarEntreNiveles(Lista lista, NodoGen n, int niv1, int niv2, int nivActual) {
        if (n != null) {
            System.out.println("Entra con el :" + n.getElem());
            if (nivActual < niv2) {
                recListarEntreNiveles(lista, n.getHijoIzq(), niv1, niv2, nivActual + 1);
            }
            if (nivActual >= niv1) {
                System.out.println("Por insertar el : " + n.getElem());
                lista.insertar(n.getElem(), lista.longitud() + 1);
            }
            if (n.getHijoIzq() != null && nivActual < niv2) {
                NodoGen h = n.getHijoIzq();
                while (h.getHermanoDer() != null) {
                    recListarEntreNiveles(lista, h.getHermanoDer(), niv1, niv2, nivActual + 1);
                    h = h.getHermanoDer();
                }
            }
        }
    }

    public Lista listarHastaNivel(int nivMax) {
        // Devuelve un listado en inorden de los elementos hasta el nivelMax
        Lista lista = new Lista();
        listarHastaNivelAux(lista, this.raiz, nivMax, 0);
        return lista;
    }

    private void listarHastaNivelAux(Lista lista, NodoGen n, int nivMax, int nivActual) {
        if (n != null) {
            System.out.println("entra con n: " + n.getElem());

            if (nivActual + 1 <= nivMax) {
                listarHastaNivelAux(lista, n.getHijoIzq(), nivMax, nivActual + 1);
            }
            lista.insertar(n.getElem(), lista.longitud() + 1);
            if (n.getHijoIzq() != null && nivActual + 1 <= nivMax) {
                NodoGen h = n.getHijoIzq().getHermanoDer();
                while (h != null) {
                    listarHastaNivelAux(lista, h, nivMax, nivActual + 1);
                    h = h.getHermanoDer();
                }
            }

        }
    }

    public int orden() {
        int orden = ordenAux(this.raiz, 0);
        return orden;
    }

    private int ordenAux(NodoGen n, int ordenMax) {
        int ordenActual = 0;
        if (n != null) {
            System.out.println("entra con "+n.getElem());
            if (n.getHijoIzq() != null) {
                ordenActual = 1;
                NodoGen h = n.getHijoIzq().getHermanoDer();
                while (h != null) {
                    ordenActual++;
                    h = h.getHermanoDer();
                }
                if (ordenActual > ordenMax) {
                    ordenMax = ordenActual;
                }
                h=n.getHijoIzq();
                while (h != null) {
                    ordenMax = ordenAux(h, ordenMax);
                    h = h.getHermanoDer();
                }
            }
        }
        return ordenMax;
    }
    // MODULOS FINAL

    public int descendienteMasLejano(Object elem) {
        int nivel = -1;
        boolean pertenece = obtenerNodo(this.raiz, elem) != null;
        if (pertenece) {
            nivel = descendienteMasLejanoAux(this.raiz, elem, 0, Integer.MIN_VALUE);
        }
        return nivel;
    }

    private int descendienteMasLejanoAux(NodoGen n, Object elem, int nivActual, int nivMax) {
        if (n != null) {
            if (n.getElem().equals(elem)) {
                if (nivActual > nivMax) {
                    nivMax = nivActual;
                }
            }
            NodoGen h = n.getHijoIzq();
            while (h != null) {
                nivMax = descendienteMasLejanoAux(h, elem, nivActual + 1, nivMax);
                h = h.getHermanoDer();
            }
        }
        return nivMax;
    }

    public int descendienteMasCercano(Object elem) {
        int nivel = -1;
        boolean pertenece = obtenerNodo(this.raiz, elem) != null;
        if (pertenece) {
            nivel = descendienteMasCercanoAux(this.raiz, elem, 0, Integer.MAX_VALUE);
        }
        return nivel;
    }

    private int descendienteMasCercanoAux(NodoGen n, Object elem, int nivActual, int nivMin) {
        if (n != null) {
            if (n.getElem().equals(elem)) {
                if (nivActual < nivMin) {
                    nivMin = nivActual;
                }
            }
            NodoGen h = n.getHijoIzq();
            while (h != null && nivActual + 1 < nivMin) {
                nivMin = descendienteMasCercanoAux(h, elem, nivActual + 1, nivMin);
                h = h.getHermanoDer();
            }
        }
        return nivMin;
    }

    public Lista listaQueJustificaAltura() {
        Lista lista = new Lista();
        Lista visitados = new Lista();
        lista = listaQueJustificaAlturaAux(this.raiz, lista, visitados);
        return lista;
    }

    private Lista listaQueJustificaAlturaAux(NodoGen n, Lista lista, Lista visitados) {

        if (n != null) {
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            if (n.getHijoIzq() == null) {
                if (visitados.longitud() > lista.longitud()) {
                    lista = visitados.clone();
                }
            } else {
                lista = listaQueJustificaAlturaAux(n.getHijoIzq(), lista, visitados);
                NodoGen h = n.getHijoIzq();
                while (h.getHermanoDer() != null) {
                    lista = listaQueJustificaAlturaAux(h.getHermanoDer(), lista, visitados);
                    h = h.getHermanoDer();
                }

            }
            visitados.eliminar(visitados.longitud());
        }
        return lista;

    }

    public Lista listaQueJustificaMenorAltura() {
        Lista lista = new Lista();
        Lista visitados = new Lista();
        lista = listaQueJustificaMenorAlturaAux(this.raiz, lista, visitados);
        return lista;
    }

    private Lista listaQueJustificaMenorAlturaAux(NodoGen n, Lista lista, Lista visitados) {

        if (n != null) {
            System.out.println("Entra con " + n.getElem());
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            if (n.getHijoIzq() == null) {
                if (visitados.longitud() < lista.longitud() || lista.longitud() == 0) {
                    lista = visitados.clone();
                }
            } else {
                NodoGen h = n.getHijoIzq();
                while (h != null) {
                    if (visitados.longitud() < lista.longitud() + 1 || lista.longitud() == 0) {
                        lista = listaQueJustificaMenorAlturaAux(h, lista, visitados);
                    }
                    h = h.getHermanoDer();
                }

            }
            visitados.eliminar(visitados.longitud());
        }
        return lista;

    }

    public boolean verCamino(Lista lista) {
        boolean exito = verCaminoAux(this.raiz, lista, 1);
        return exito;
    }

    private boolean verCaminoAux(NodoGen n, Lista lista, int pos) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(lista.recuperar(pos))) {
                if (pos == lista.longitud()) {
                    exito = true;
                }
                if (!exito) {
                    exito = verCaminoAux(n.getHijoIzq(), lista, pos + 1);
                    NodoGen h = n.getHijoIzq();
                    while (h.getHermanoDer() != null && !exito) {
                        exito = verCaminoAux(h.getHermanoDer(), lista, pos + 1);
                        h = h.getHermanoDer();
                    }
                }
            }
        }
        return exito;
    }
    public boolean verifCaminoNivel(Lista lisElem,int nivel){
        return verifCaminoNivelAux(this.raiz,lisElem,nivel,0,1);
    }
    private boolean verifCaminoNivelAux(NodoGen n,Lista lisElem,int nivel, int nivActual,int pos){
        boolean exito=false,fin=false;
        if(n!=null){
            if(n.getElem().equals(lisElem.recuperar(pos))){
                if(pos==lisElem.longitud()){
                    if(nivActual==nivel){
                        exito=true;
                    }else{
                        fin=true;
                    }
                }
                NodoGen h=n.getHijoIzq();
                while(h!=null&&!exito&&!fin){
                    exito=verifCaminoNivelAux(h,lisElem,nivel,nivActual+1,pos+1);
                    h=h.getHermanoDer();
                }
            }
        }
        return exito;
    }
}
