package grafo;

import javafx.util.Pair;
import ordenar.Ordenar;

public class AGM {
	private int[] componentesConexas;
	private Grafo grafo;
	private Ordenar grafoOrdenado;

	public Grafo calcularKruskal(Grafo gr) {
		corroborarExistencia(gr);
		
		BFS bfs = new BFS(gr);
		if (!bfs.esConexo())
			return null;
		
		inicializarValores(gr);
		crearAGM();
		
		return grafo;
	}

	private void crearAGM() {
		for (int i = 0; i < grafoOrdenado.tamano(); i++) {
			Pair<Integer, Integer> actual = grafoOrdenado.indices(i);
			if (!find(actual.getKey(), actual.getValue())) {
				union(actual.getKey(), actual.getValue());
				grafo.agregarArista(actual.getKey(), actual.getValue(), grafoOrdenado.getDistancia(i));
			}
		}
	}

	private void corroborarExistencia(Grafo gr) {
		if (gr == null)
			throw new IllegalArgumentException("El grafo no existe");

	}

	private void inicializarValores(Grafo gr) {
		grafo = new Grafo(gr.tamano());
		grafoOrdenado = new Ordenar(gr);
		inicializarPadres(gr.tamano());
	}

	private void inicializarPadres(int tamano) {
		componentesConexas = new int[tamano];
		for (int i = 0; i < tamano; i++) {
			componentesConexas[i] = i;
		}
	}

	private int raiz(int i) {
		while (componentesConexas[i] != i)
			i = componentesConexas[i];
		return i;
	}

	private boolean find(int i, int j) {
		return raiz(i) == raiz(j);
	}

	private void union(int i, int j) {
		int ri = raiz(i);
		int rj = raiz(j);

		componentesConexas[ri] = rj;
	}

}
