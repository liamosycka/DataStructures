package conjuntistas;

public class NodoArbol {

	private Comparable elem;
    private NodoArbol izq;
    private NodoArbol der;
    public NodoArbol(Comparable elem){
        this.elem=elem;
    }
    public NodoArbol(Comparable elem,NodoArbol izq,NodoArbol der){
        this.elem=elem;
        this.izq=izq;
        this.der=der;
    }
    public Comparable getElem(){
        return this.elem;
    }
    public NodoArbol getIzq(){
        return this.izq;
    }
    public NodoArbol getDer(){
        return this.der;
    }
    public void setElem(Comparable elem){
        this.elem=elem;
    }
    public void setIzq(NodoArbol izq){
        this.izq=izq;
    }
    public void setDer(NodoArbol der){
        this.der=der;
}
}
