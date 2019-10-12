package clustering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import grafo.BFS;
import grafo.Grafo;
import javafx.util.Pair;
import ordenar.Ordenar;

public class Clustering {

	private Set<Pair<Integer, Integer>> aristasAEliminar;
	private List<Cluster>clusters;

	private int cantClusters = 1;
	private Grafo grafo;

	public Clustering(Grafo g) {
		grafo = new Grafo(g);
		aristasAEliminar = new HashSet<Pair<Integer, Integer>>();
		clusters=new ArrayList<Cluster>();
	}
	
	
	public Grafo clusteringAristasMasPesadas(int cantidadClusters) {
		corroborarCantidad(cantidadClusters);
		reiniciarValores();
		agregarAristasAEliminar(cantidadClusters);
		eliminarAristas(cantidadClusters);
		cantClusters += aristasAEliminar.size();
		return grafo;
	}
	
	public Grafo clusteringAlAzar(int cantidadClusters) {
		corroborarCantidad(cantidadClusters);
		reiniciarValores();
		agregarAristasAEliminarAzar(cantidadClusters);
		eliminarAristas(cantidadClusters);
		cantClusters += aristasAEliminar.size();
		return grafo;
	}

	private void eliminarAristas(int cantidadClusters) {
		
		
		for (Pair<Integer,Integer>aristaAEliminar: aristasAEliminar) {
			grafo.borrarArista(aristaAEliminar.getKey(), aristaAEliminar.getValue());
			BFS bfs = new BFS(grafo);
			
			if(clusters.isEmpty()) {
				Double peso1=bfs.pesoSubgrafo(aristaAEliminar.getKey());
				Double peso2=bfs.pesoSubgrafo(aristaAEliminar.getValue());
				Cluster c1=new Cluster(peso1,bfs.verticesDelSubgrafo(aristaAEliminar.getKey()));
				Cluster c2=new Cluster(peso2,bfs.verticesDelSubgrafo(aristaAEliminar.getValue()));
				clusters.add(c1);
				clusters.add(c2);
			}
			else {
				List<Cluster>clustersClonado= clonarCluster();
				for(Cluster cluster: clustersClonado) {
					bfs=new BFS(grafo);
					if(cluster.getVertices().contains(aristaAEliminar.getKey())) {
						Double peso1=bfs.pesoSubgrafo(aristaAEliminar.getKey());
						Double peso2=bfs.pesoSubgrafo(aristaAEliminar.getValue());
						Cluster c1=new Cluster(peso1,bfs.verticesDelSubgrafo(aristaAEliminar.getKey()));
						Cluster c2=new Cluster(peso2,bfs.verticesDelSubgrafo(aristaAEliminar.getValue()));
						clusters.remove(cluster);
						clusters.add(c1);
						clusters.add(c2);
					}
				}
				
			}
		}
		cantClusters += aristasAEliminar.size();

	}


	private void agregarAristasAEliminar(int cantidadClusters) {
		Ordenar ordenar = new Ordenar(grafo.getDistancias(), grafo.getIndices());
		int aristasEliminadas = 0;
		for (int i = ordenar.tamano() - 1; i >= 0; i--) {
			if (aristasEliminadas == cantidadClusters - 1)
				break;
			aristasAEliminar.add(ordenar.indices(i));
			aristasEliminadas++;

		}
	}
	private void agregarAristasAEliminarAzar(int cantidadClusters) {
		int aristasEliminadas = 0;
		while(aristasEliminadas!=cantidadClusters-1) {
			Random r  = new Random();
			int i=r.nextInt(grafo.tamano());
			int j=r.nextInt(grafo.tamano());
			if( i!=j && grafo.existeArista(i,j) && !aristasAEliminar.contains(new Pair<Integer,Integer>(i,j))) {
				aristasAEliminar.add(new Pair<Integer,Integer>(i,j));
				aristasEliminadas++;
			}
		}
	}
	
	private List<Cluster> clonarCluster() {
		List<Cluster>ret=new ArrayList<Cluster>();
		for(Cluster cluster: clusters) {
			ret.add(cluster);
		}
		return ret;
	}


	public List<Cluster>getClusters(){
		return clusters;
	}

	private void reiniciarValores() {
		aristasAEliminar.clear();
		cantClusters=1;
	}

	private void corroborarCantidad(int cantidadClusters) {
		if (cantidadClusters > grafo.cantAristas())
			throw new IllegalArgumentException("La cantidad de clusters debe ser menor a la cantidad de aristas");
	}

	public int cantClusters() {
		return cantClusters;
	}

}

