package clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import grafo.BFS;
import grafo.Grafo;
import javafx.util.Pair;
import ordenar.Ordenar;

public class Clustering {

	private List<Pair<Integer, Integer>> aristasAEliminar;
	private HashMap<Double, ArrayList<Integer>> clus = new HashMap<Double, ArrayList<Integer>>();

	private int cantClusters = 1;
	private Grafo grafo;

	public Clustering(Grafo g) {
		grafo = g;
		aristasAEliminar = new ArrayList<Pair<Integer, Integer>>();
	}

	public void clusteringAristasMasPesadas(int cantidadClusters) {
		corroborarCantidad(cantidadClusters, grafo.cantAristas());
		reiniciarValores();
		eliminarAristas(cantidadClusters, grafo.getDistancias(), grafo.getIndices());
		cantClusters += aristasAEliminar.size();
	}

	private void eliminarAristas(int cantidadClusters, Double[] distancias, List<Pair<Integer, Integer>> indices) {
		Ordenar ordenar = new Ordenar(distancias, indices);
		int aristasEliminadas = 0;
		for (int i = ordenar.tamano() - 1; i >= 0; i--) {
			if (aristasEliminadas == cantidadClusters - 1)
				break;
			aristasAEliminar.add(ordenar.indices(i));
			aristasEliminadas++;

		}
		BFS bfs = new BFS(grafo);
		for (int i = 0; i < aristasAEliminar.size(); i++) {
			/*
			 * if(x.equals(y)) {
			 * Pair<Integer,Integer>indiceVecinoMasPesado=grafo.vecinoMasPesado(x);
			 * grafo.borrarArista(indiceVecinoMasPesado.getKey(),indiceVecinoMasPesado.
			 * getValue()); } else grafo.borrarArista(aristasAEliminar.get(i).getKey(),
			 * aristasAEliminar.get(i).getValue()); }
			 */

			grafo.borrarArista(aristasAEliminar.get(i).getKey(), aristasAEliminar.get(i).getValue());

			if (clus.size() == 0) {
				Double num1 = bfs.pesoSubgrafo(aristasAEliminar.get(i).getKey());
				Double num2 = bfs.pesoSubgrafo(aristasAEliminar.get(i).getValue());
				clus.put(num1, bfs.verticesDelSubgrafo(aristasAEliminar.get(i).getKey()));
				clus.put(num2, bfs.verticesDelSubgrafo(aristasAEliminar.get(i).getValue()));
			} else {

				HashMap<Double, ArrayList<Integer>> copy = clonarSet(clus);
				System.out.println(copy.toString());
				for (Entry<Double, ArrayList<Integer>> entry : copy.entrySet()) {
					bfs = new BFS(grafo);
					if (entry.getValue().contains(aristasAEliminar.get(i).getKey())) {
						Double num1 = bfs.pesoSubgrafo(aristasAEliminar.get(i).getKey());
						Double num2 = bfs.pesoSubgrafo(aristasAEliminar.get(i).getValue());
						clus.remove(entry.getKey());
						clus.put(num1, bfs.verticesDelSubgrafo(aristasAEliminar.get(i).getKey()));
						clus.put(num2, bfs.verticesDelSubgrafo(aristasAEliminar.get(i).getValue()));
					}

				}
			}

		}
		System.out.println("CLUSTERS: " + clus.keySet().toString());
		cantClusters += aristasAEliminar.size();

	}

	private HashMap<Double, ArrayList<Integer>> clonarSet(HashMap<Double, ArrayList<Integer>> clus2) {
		HashMap<Double, ArrayList<Integer>> copia = new HashMap<Double, ArrayList<Integer>>();
		for (Entry<Double, ArrayList<Integer>> entry : clus2.entrySet()) {
			copia.put(entry.getKey(), entry.getValue());
		}
		return copia;
	}

	public void clusteringVecinosMasPesados(int cantidadClusters) {
		corroborarCantidad(cantidadClusters, grafo.tamano());
		reiniciarValores();
		Double[] promedioVecinos = new Double[grafo.tamano()];
		List<Pair<Integer, Integer>> indices = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < grafo.tamano(); i++) {
			promedioVecinos[i] = grafo.promedioVecinos(i);
			indices.add(new Pair<Integer, Integer>(i, i));
		}
		eliminarAristas(cantidadClusters, promedioVecinos, indices);

	}

	private void reiniciarValores() {
		aristasAEliminar.clear();
	}

	private void corroborarCantidad(int cantidadClusters, int maximaCantidad) {
		if (cantidadClusters > maximaCantidad)
			throw new IllegalArgumentException("La cantidad de clusters debe ser menor a la cantidad de aristas");
	}

	public int cantClusters() {
		return cantClusters;
	}

}
