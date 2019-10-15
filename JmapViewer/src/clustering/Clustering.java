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
	private List<Cluster> clusters;

	private int cantidadClusters;
	private Grafo grafo;

	public Clustering(Grafo g,int cantClusters) {
		corroborarExistencia(g);
		grafo = new Grafo(g);
		cantidadClusters=cantClusters;
		corroborarCantidad();
		aristasAEliminar = new HashSet<Pair<Integer, Integer>>();
		clusters = new ArrayList<Cluster>();
		
	}

	public boolean aristasMasPesadas() {
		if (!poseeClusters()) {
			eliminarMasPesadas();
			calcularClusters();
			return true;
		}
		return false;
	}

	private void corroborarExistencia(Grafo g) {
		if (g==null)
			throw new IllegalArgumentException("El grafo no existe");
		
	}

	public boolean promedio() {
		if (!poseeClusters()) {
			corroborarCantidad();
			eliminarPorPromedio();
			calcularClusters();
			return true;
		}
		return false;
	}

	public boolean azar() {
		if (!poseeClusters()) {
			corroborarCantidad();
			eliminarAlAzar();
			calcularClusters();
			return true;
		}
		return false;
	}

	private void calcularClusters() {
		for (Pair<Integer, Integer> aristaMasPesada : aristasAEliminar) {
			BFS bfs = new BFS(grafo);
			grafo.borrarArista(aristaMasPesada.getKey(), aristaMasPesada.getValue());
			if (clusters.isEmpty()) {
				agregarPrimerCluster(aristaMasPesada, bfs);
			} else {
				agregarClustersRestantes(aristaMasPesada);
			}
		}
	}

	private void agregarClustersRestantes(Pair<Integer, Integer> aristaMasPesada) {
		BFS bfs;
		List<Cluster> clustersClonado = clonarCluster();
		for (Cluster cluster : clustersClonado) {
			bfs = new BFS(grafo);
			if (cluster.getVertices().contains(aristaMasPesada.getKey())) {
				Double peso1 = bfs.pesoSubgrafo(aristaMasPesada.getKey());
				Double peso2 = bfs.pesoSubgrafo(aristaMasPesada.getValue());
				Cluster c1 = new Cluster(peso1, bfs.verticesDelSubgrafo(aristaMasPesada.getKey()));
				Cluster c2 = new Cluster(peso2, bfs.verticesDelSubgrafo(aristaMasPesada.getValue()));
				clusters.remove(cluster);
				clusters.add(c1);
				clusters.add(c2);
			}
		}
	}

	private void agregarPrimerCluster(Pair<Integer, Integer> aristaMasPesada, BFS bfs) {
		Double peso1 = bfs.pesoSubgrafo(aristaMasPesada.getKey());
		Double peso2 = bfs.pesoSubgrafo(aristaMasPesada.getValue());
		Cluster c1 = new Cluster(peso1, bfs.verticesDelSubgrafo(aristaMasPesada.getKey()));
		Cluster c2 = new Cluster(peso2, bfs.verticesDelSubgrafo(aristaMasPesada.getValue()));
		clusters.add(c1);
		clusters.add(c2);
	}

	private void eliminarMasPesadas() {
		Grafo aux=new Grafo(grafo);
		Ordenar ordenar = new Ordenar(aux);
		int aristasEliminadas = 0;
		for (int i = ordenar.tamano() - 1; i >= 0; i--) {
			if (aristasEliminadas == cantidadClusters - 1)
				break;
			aristasAEliminar.add(ordenar.indices(i));
			aux.borrarArista(ordenar.indices(i).getKey(), ordenar.indices(i).getValue());
			aristasEliminadas++;

		}
	}

	private void eliminarPorPromedio() {
		Grafo aux=new Grafo(grafo);
		for (int i = 0; i < cantidadClusters - 1; i++) {
			Pair<Integer, Integer> aristaMasPesada = aux.promedioMasPesado();
			aristasAEliminar.add(aristaMasPesada);
			aux.borrarArista(aristaMasPesada.getKey(), aristaMasPesada.getValue());
		}
	}

	private void eliminarAlAzar() {
		int aristasEliminadas = 0;
		Grafo aux=new Grafo(grafo);
		while (aristasEliminadas != cantidadClusters - 1) {
			Random r = new Random();
			int i = r.nextInt(aux.tamano());
			int j = r.nextInt(aux.tamano());
			if (i != j && aux.existeArista(i, j) && !aristasAEliminar.contains(new Pair<Integer, Integer>(i, j))) {
				aristasAEliminar.add(new Pair<Integer, Integer>(i, j));
				aux.borrarArista(i, j);
				aristasEliminadas++;
			}
		}
	}

	private List<Cluster> clonarCluster() {
		List<Cluster> ret = new ArrayList<Cluster>();
		for (Cluster cluster : clusters) {
			ret.add(cluster);
		}
		return ret;
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	private void corroborarCantidad() {
		if (cantidadClusters > grafo.cantAristas())
			throw new IllegalArgumentException("La cantidad de clusters debe ser menor a la cantidad de aristas");
		if (cantidadClusters < 0)
			throw new IllegalArgumentException("La cantidad de clusters debe ser positiva");
		if (cantidadClusters == 1)
			throw new IllegalArgumentException("La cantidad de clusters debe ser mayor a 1");
	}

	private boolean poseeClusters() {
		BFS bfs = new BFS(grafo);
		return !bfs.esConexo();
	}

	public int cantClusters() {
		return cantidadClusters;
	}

	public Grafo getGrafo() {
		return grafo;
	}
}
