package clustering;


import java.util.ArrayList;
import java.util.List;

import grafo.Grafo;
import javafx.util.Pair;
import ordenar.Ordenar;

public class Clustering {

	private List<Pair<Integer,Integer>>aristasAEliminar;
	private int cantClusters = 1;
	
	public void hacer_Clustering(Grafo grafo,int cantidadClusters) {
		if(cantidadClusters>grafo.tamano())
			throw new IllegalArgumentException("La cantidad de clusters debe ser menor al tamaño del grafo");
		
		aristasAEliminar=new ArrayList<Pair<Integer,Integer>>();
	    Ordenar ordenar=new Ordenar(grafo.getDistancias(),grafo.getIndices());
	   
	    int aristasEliminadas=0;
		for(int i=ordenar.tamano()-1;i>=0;i--) {
			if(aristasEliminadas==cantidadClusters-1)
				break;
			aristasAEliminar.add(ordenar.indices(i));
			aristasEliminadas++;
		}
		
		for(int i=0;i<aristasAEliminar.size();i++) {
			grafo.borrarArista(aristasAEliminar.get(i).getKey(), aristasAEliminar.get(i).getValue());
		}
		cantClusters+=aristasAEliminar.size();
	}
	public int cantClusters() {
		return cantClusters;
	}

}
