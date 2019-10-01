package clustering;


import java.util.ArrayList;
import java.util.List;

import grafo.Grafo;
import javafx.util.Pair;
import ordenar.Ordenar;

public class Clustering {

	private List<Pair<Integer,Integer>>eliminar;
	
	public Grafo hacer_Clustering(Grafo g,int cant) {
		eliminar=new ArrayList<Pair<Integer,Integer>>();
		Ordenar ordenar=new Ordenar(g.getDistancias(),g.getIndices());
		int cont=0;
		for(int i=ordenar.tamano()-1;i>=0;i--) {
			if(cont==cant-1)
				break;
			eliminar.add(ordenar.indices(i));
			cont++;
		}
		Grafo f=new Grafo(g.tamano());
		f=g;
		for(int i=0;i<eliminar.size();i++) {
			g.borrarArista(eliminar.get(i).getKey(), eliminar.get(i).getValue());
		}
		return f;
	}

}
