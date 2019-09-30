package grafo;
import ordenar.Ordenar;

public class AGM {
	private int [] padres;
	private Grafo grafo;
	private Ordenar ord;
	public void ordenadasAristas(Grafo gr) {
		grafo=new Grafo(gr.tamano());
		ord=new Ordenar(gr.getDistancias(),gr.getIndices());
		System.out.println("primero: "+ord.getDistancia(0));
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

	 boolean find(int i, int j){
		 return raiz(i) == raiz(j);
	  }
	 
	 void union(int i, int j){
	  int ri = raiz(i);
	  int rj = raiz(j);
	 
	  padres[ri] = rj;
	  }
	

	public Grafo calcularKruskal(Grafo gr) {
		ordenadasAristas(gr);
		for(int i=0;i<ord.tamano();i++) {
			if(!find(ord.getIndice(i).getKey(),ord.getIndice(i).getValue())) {
				union(ord.getIndice(i).getKey(),ord.getIndice(i).getValue());
				grafo.agregarArista(ord.getIndice(i).getKey(), ord.getIndice(i).getValue(), ord.getDistancia(i));
			}
		}
	/*	for(int i = 0; i<aristasOrdenadas.length; i++) {
			if(!find(aristas.get(aristasOrdenadas[i]).getKey(),aristas.get(aristasOrdenadas[i]).getValue())){
				union(aristas.get(aristasOrdenadas[i]).getKey(),aristas.get(aristasOrdenadas[i]).getValue());
				grafo.agregarArista(aristas.get(aristasOrdenadas[i]).getKey(), aristas.get(aristasOrdenadas[i]).getValue(), aristasOrdenadas[i]);
			}
			
			
		}*/
		
		return grafo;
	}

}
