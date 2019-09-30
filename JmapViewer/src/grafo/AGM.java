package grafo;

import java.util.LinkedHashMap;

import javafx.util.Pair;

public class AGM {
	private LinkedHashMap<Double, Pair<Integer, Integer>> aristas;
	private Double[] aristasOrdenadas;
	private int [] padres;
	private Grafo grafo;
	
	public void ordenadasAristas(Grafo gr) {
		grafo=new Grafo(gr.tamano());
		
		aristas = (LinkedHashMap<Double, Pair<Integer, Integer>>) gr.getMap();
	
		Double[] prueba = new Double[aristas.size()];
		
		aristas.keySet().toArray(prueba);
		
		quickSort qs = new quickSort();
		
		qs.sort(prueba, 0, prueba.length-1);
		
		aristasOrdenadas = prueba;
		
		inicializarPadres(gr.tamano());
	}
	private void inicializarPadres(int tamano) {
		padres=new int[tamano];
		for(int i=0;i<tamano;i++) {
			padres[i]=i;
		}
		
	}
	private boolean formaCiclo(Double arista ) {
		
		//agm.agregarArista(aristas.get(arista).getKey(), aristas.get(arista).getValue(), arista);
		//Grafo grafoTemporal  = new Grafo(agm);
	//	if(grafoTemporal.isCyclic()) {
		//	agm.borrarArista(aristas.get(arista).getKey(), aristas.get(arista).getValue());

			return true;
		//}
		//return false;
	}
	
	private int raiz(int i){
	 while(padres[i] != i)
	    i = padres[i];
	 return i;
	 }

	 boolean find(int i, int j){
		 return raiz(i) == raiz(j);
	  }
	 
	 void union(int i, int j){
	  int ri = raiz(i);
	  int rj = raiz(j);
	 
	  padres[ri] = rj;
	  }
	 
	 void deshacerUnion(int i,int anterior) {
		 padres[i]=anterior;
	 }

	public Grafo calcularKruskal(Grafo gr) {
		
		ordenadasAristas(gr);
	
		for(int i = 0; i<aristasOrdenadas.length; i++) {
			if(!find(aristas.get(aristasOrdenadas[i]).getKey(),aristas.get(aristasOrdenadas[i]).getValue())){
				union(aristas.get(aristasOrdenadas[i]).getKey(),aristas.get(aristasOrdenadas[i]).getValue());
				grafo.agregarArista(aristas.get(aristasOrdenadas[i]).getKey(), aristas.get(aristasOrdenadas[i]).getValue(), aristasOrdenadas[i]);
			}
			
			
		}
		
		return grafo;
	}

}
