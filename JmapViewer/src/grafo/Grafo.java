package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import javafx.util.Pair;

public class Grafo {
	private Double[][] A;

	public Grafo(int vertices) {
		A = new Double[vertices][vertices];
		inicializarMatriz();
	}

	public Grafo(List<Coordinate> coordenadas) {
		A = new Double[coordenadas.size()][coordenadas.size()];
		for (int i = 0; i < tamano(); i++) {
			for (int j = i + 1; j < tamano() && i != j; j++) {
				distanciaEuclideana(i, j, coordenadas.get(i), coordenadas.get(j));
			}
		}
	}

	public Grafo(Grafo grafo) {
		A = new Double[grafo.tamano()][grafo.tamano()];
		for (int i = 0; i < tamano(); i++) {
			for (int j = i + 1; j < tamano(); j++) {
				agregarArista(i, j, grafo.getArista(i, j));
			}
		}
	}

	public Double getArista(int i, int j) {
		verificarIndices(i, j);
		return A[i][j];
	}

	private void inicializarMatriz() {
		for (int i = 0; i < tamano(); i++) {
			for (int j = 0; j < tamano(); j++) {
				A[i][j] = A[j][i] = 0.0;
			}
		}
	}

	private void distanciaEuclideana(int i, int j, Coordinate coordenadas1, Coordinate coordenadas2) {
		verificarIndices(i, j);
		double radioTierra = 6371;
		double dLat = Math.toRadians(coordenadas2.getLat() - coordenadas1.getLat());
		double dLng = Math.toRadians(coordenadas2.getLon() - coordenadas1.getLon());
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(coordenadas1.getLat()))
				* Math.cos(Math.toRadians(coordenadas2.getLat()));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		A[i][j] = A[j][i] = radioTierra * va2;
	}

	public void agregarArista(int i, int j, Double distanciaEuclidea) {
		verificarIndices(i, j);
		A[i][j] = A[j][i] = distanciaEuclidea;
	}

	public void borrarArista(int i, int j) {
		verificarIndices(i, j);
		A[i][j] = A[j][i] = 0.0;
	}

	public boolean existeArista(int i, int j) {
		verificarIndices(i, j);
		return A[i][j] != 0.0 ? true : false;
	}

	public Set<Integer> vecinos(int i) {
		verificarVertice(i);

		Set<Integer> vecinos = new HashSet<Integer>();
		for (int j = 0; j < tamano(); ++j)
			if (i != j && existeArista(i, j))
				vecinos.add(j);

		return vecinos;
	}

	public Pair<Integer, Integer> vecinoMasPesado(int i) {
		Pair<Integer, Integer> masPesado = new Pair<Integer, Integer>(0, 0);
		double mayor = 0.0;
		for (int j = 0; j < tamano(); ++j)
			if (i != j && existeArista(i, j) && getArista(i, j) > mayor) {
				mayor = getArista(i, j);
				masPesado = new Pair<Integer, Integer>(i, j);
			}
		return masPesado;
	}

	public Double promedioVecinos(int vertice) {
		verificarVertice(vertice);
		Double pesos = 0.0;
		int cantidadVecinos = 0;
		for (int i = 0; i < tamano(); i++) {
			if (i != vertice && existeArista(i, vertice)) {
				pesos += getArista(i, vertice);
				cantidadVecinos++;
			}
		}
		return cantidadVecinos > 0.0 && pesos > 0.0 ? pesos / cantidadVecinos : 0.0;
	}

	public Pair<Integer, Integer> promedioMasPesado() {
		Pair<Integer, Integer> verticeMasPesado = new Pair<Integer, Integer>(0, 0);
		Double masPesado = 0.0;
		for (int i = 0; i < tamano(); i++) {
			if (promedioVecinos(i) > masPesado) {
				masPesado = promedioVecinos(i);
				verticeMasPesado = vecinoMasPesado(i);
			}
		}
		return verticeMasPesado;
	}

	public int tamano() {
		return A.length;
	}

	private void verificarIndices(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);

		if (i == j)
			throw new IllegalArgumentException("No existen aristas entre un vertice y si mismo! vertice = " + i);
	}

	private void verificarVertice(int i) {
		if (i < 0 || i >= tamano())
			throw new IllegalArgumentException("El vertice " + i + " no existe!");
	}

	public int cantAristas() {
		int ret = 0;
		for (int i = 0; i < tamano(); i++) {
			for (int j = i + 1; j < tamano() && j != i; j++) {
				if (existeArista(i, j))
					ret++;
			}
		}
		return ret;
	}

	public List<Pair<Integer, Integer>> getIndices() {
		List<Pair<Integer, Integer>> indices = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < tamano(); i++) {
			for (int j = i + 1; j < tamano() && j != i; j++) {
				if (existeArista(i, j))
					indices.add(new Pair<Integer, Integer>(i, j));
			}
		}
		return indices;
	}

}
