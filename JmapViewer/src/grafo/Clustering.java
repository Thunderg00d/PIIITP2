package grafo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import ordenar.Ordenar;

public class Clustering {

	private List<Pair<Integer,Integer>>mayores;
	private List<Double>mayoresDistancias;
	private List<Pair<Integer,Integer>>eliminar;
	
	public Clustering(Grafo g,int cant) {
		mayores=new ArrayList<Pair<Integer,Integer>>();
		mayoresDistancias=new ArrayList<Double>();
		eliminar=new ArrayList<Pair<Integer,Integer>>();
		
		for(int i=0;i<g.tamano();i++) {
			mayor(g.vecinos(i),g.indice_vecinos(i));
		}
		
		Double[]aux=new Double[mayoresDistancias.size()];
		int cont=0;
		for(Double distancia:mayoresDistancias) {
			aux[cont]=distancia;
			cont++;
		}
		
		Ordenar ordenar=new Ordenar(aux,mayores);
		int cantidad=0;
		for(int i=ordenar.tamano()-1;i>=0;i--) {
			if(cantidad==cant)
				break;
			eliminar.add(ordenar.indices(i));
			cantidad++;
		}
		
		for(int i=0;i<eliminar.size();i++) {
			g.borrarArista(eliminar.get(i).getKey(),eliminar.get(i).getValue());
		}
		g.setGrafo(g.getMatriz());
		
	}

	private void mayor(Set<Double> vecinos,Set<Pair<Integer,Integer>> indices) {
		Double[] vec=new Double[vecinos.size()];
		vecinos.toArray(vec);
		
		List<Pair<Integer,Integer>> ind=new ArrayList<Pair<Integer,Integer>>();
		for(Pair<Integer,Integer>indice: indices) {
			ind.add(indice);
		}

		Double dis_mayor=Double.MIN_VALUE;
		Pair<Integer,Integer>ind_mayor=new Pair<Integer,Integer>(Integer.MIN_VALUE,Integer.MIN_VALUE);
		for(int i=0;i<vec.length;i++) {
			if(vec[i]>dis_mayor) {
				dis_mayor=vec[i];
				ind_mayor=ind.get(i);
				System.out.println("entro");
			}
		}
		
		mayores.add(ind_mayor);
		mayoresDistancias.add(dis_mayor);
	}
}
