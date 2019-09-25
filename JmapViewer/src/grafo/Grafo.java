package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;


public class Grafo {
	
	// Representamos el grafo por su matriz de adyacencia
	private Double[][] A;

	// El conjunto de vertices esta fijo
	public Grafo(int vertices) {
		A = new Double[vertices][vertices];
	}
	public Grafo(ArrayList<Coordinate>lista){
		A = new Double[lista.size()][lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			for (int j = 0; j < lista.size(); j++) {
				if (i != j) {
					distanciaEuclideana(i,j,lista.get(i),lista.get(j));
				}
			}
		}
	}

	public Double getArista(int i,int j){
		verificarIndices(i,j);
		return A[i][j];
	}

	private void distanciaEuclideana(int i, int j, Coordinate coordenadas1, Coordinate coordenadas2) {
		verificarIndices(i,j);
		Double deltax = Math.abs(coordenadas1.getLat() - coordenadas1.getLon());
		Double deltay = Math.abs(coordenadas2.getLat() - coordenadas2.getLon());
		A[i][j] = A[j][i] = Math.sqrt(deltax*deltax + deltay*deltay);
	}

	public Double distancia(int i, int j) {
		return A[i][j];
	}

	// Operaciones sobre aristas
	public void agregarArista(int i, int j, Coordinate coordenadas1, Coordinate coordenadas2) {
		distanciaEuclideana(i,j,coordenadas1,coordenadas2);
	}

	public void borrarArista(int i, int j) {
		verificarIndices(i, j);
		A[i][j] = A[j][i] = 0.0;
	}

	public boolean existeArista(int i, int j) {
		verificarIndices(i, j);
		return A[i][j] != 0 ? true : false;
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

	// Cantidad de vertices
	public int tamano() {
		return A.length;
	}

	// Lanza excepciones si los indices no son validos
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
}
