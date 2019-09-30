package grafo;

import javafx.util.Pair;
import ordenar.Ordenar;

public class AGM {
	private int [] padres;
	private Grafo grafo;
	private Ordenar ord;

	public Grafo calcularKruskal(Grafo gr) {
		inicializarValores(gr);
		for(int i=0;i<ord.tamano();i++) {
			Pair<Integer,Integer>actual=ord.indices(i);
			if(!find(actual.getKey(),actual.getValue())) {
				union(actual.getKey(),actual.getValue());
				grafo.agregarArista(actual.getKey(),actual.getValue(), ord.getDistancia(i));
			}
		}	
		return grafo;
	}
	private void inicializarValores(Grafo gr) {
		grafo=new Grafo(gr.tamano());
		ord=new Ordenar(gr.getDistancias(),gr.getIndices());
		inicializarPadres(gr.tamano());
	}
	private void inicializarPadres(int tamano) {
		padres=new int[tamano];
		for(int i=0;i<tamano;i++) {
			padres[i]=i;
		}
	}

	
	private int raiz(int i){
	 while(padres[i] != i)
	    i = padres[i];
	 return i;
	 }

	 private boolean find(int i, int j){
		 return raiz(i) == raiz(j);
	  }
	 
	private void union(int i, int j){
	  int ri = raiz(i);
	  int rj = raiz(j);
	 
	  padres[ri] = rj;
	  }
	

	

}
