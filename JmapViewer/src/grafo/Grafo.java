package grafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Grafo {
	// Representamos el grafo por su matriz de adyacencia
	private Coordinate[] A;
	private Double[][] distancias;

	// Crea un grafo completo
	public  Grafo(HashMap<Double, Double> Coordenadas) {
		Set<Entry<Double, Double>> c = Coordenadas.entrySet();
		A = new Coordinate[Coordenadas.size()];
		distancias = new Double[Coordenadas.size()][Coordenadas.size()];
		int i = 0;

		for (Map.Entry<Double, Double> m : c) {
			A[i] = new Coordinate(m.getKey(), m.getValue());
			i++;
		}
		setDistancias();
	}

	public void setDistancias() {
		for (int i = 0; i < tamano(); i++) {
			for (int j = 0; j < tamano(); j++) {
				calcularPeso(i, j);
			}
		}
	}
	
	private void calcularPeso(int i, int j) {
		if (i != j) {
			Double delta_i=Math.abs(A[i].getLat()-A[i].getLon());
			Double delta_j=Math.abs(A[j].getLat()-A[j].getLon());
			
			Double distanciaEuclidea = Math.sqrt((delta_i*delta_i)+(delta_j*delta_j));
			
			distancias[i][j] = distanciaEuclidea;
			distancias[j][i] = distanciaEuclidea;
		}
		else {
			distancias[i][j] = -1.0;
			distancias[j][i] = -1.0;
		}
	}

	public void borrarArista(int i, int j) {
		verificarIndices(i, j);
		distancias[i][j] = distancias[j][i] = -1.0;
	}

	public boolean existeArista(int i, int j) {
		verificarIndices(i, j);
		return distancias[i][j] != -1;
	}

	// Vecinos de un vertice
	public Set<Integer> vecinos(int i) {
		verificarVertice(i);

		Set<Integer> ret = new HashSet<Integer>();
		for (int j = 0; j < tamano(); ++j)
			if (i != j && existeArista(i, j))
				ret.add(j);

		return ret;
	}

	public Double getArista(int i,int j){
		verificarIndices(i,j);
		return distancias[i][j];
	}
	// Cantidad de vertices
	public int tamano() {
		return A.length;
	}

	// Lanza excepciones si los índices no son válidos
	private void verificarIndices(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);

		if (i == j)
			throw new IllegalArgumentException(
					"No existen aristas entre un vertice y si mismo! vertice = "+ i);
	}

	private void verificarVertice(int i) {
		if (i < 0 || i >= tamano())
			throw new IllegalArgumentException("El vertice " + i
					+ " no existe!");
	}
}
