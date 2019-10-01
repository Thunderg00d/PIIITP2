package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import javafx.util.Pair;

public class Grafo {

	// Representamos el grafo por su matriz de adyacencia
	private Double[][] A;

	// El conjunto de vertices esta fijo
	public Grafo(int vertices) {
		A = new Double[vertices][vertices];
		inicializarMatriz();
	}
	
	public Grafo(List<Coordinate> lista) {
		A = new Double[lista.size()][lista.size()];
		for (int i = 0; i < tamano(); i++) {
			for (int j = 0; j < tamano()&& i!=j; j++) {
					distanciaEuclideana(i, j, lista.get(i), lista.get(j));
			}
		}
	}
	public Grafo(Grafo g) {
		A = new Double[g.tamano()][g.tamano()];
		for (int i = 0; i < g.tamano() - 1; i++) {
			for (int j = 1 + i; j < g.tamano(); j++) {
				A[i][j] = A[j][i] = g.getArista(i, j);
			}
		}
		for(int i = 0; i<g.tamano();i++) {
			A[i][i] = 0.0;
		}
		
	}

	public Double getArista(int i, int j) {
		verificarIndices(i, j);
		return A[i][j];
	}

	public Double[][] getMatriz() {
		return A;
	}

	public void setMatriz(Double[][] matriz) {
		A = matriz;
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
		Double deltax = coordenadas2.getLat() - coordenadas1.getLat();
		Double deltay = coordenadas2.getLon() - coordenadas1.getLon();
		A[i][j] = A[j][i] = Math.sqrt(deltax * deltax + deltay * deltay);
	}

	public Double distancia(int i, int j) {
		return A[i][j];
	}

	// Operaciones sobre aristas
	public void agregarArista(int i, int j, Coordinate coordenadas1, Coordinate coordenadas2) {
		distanciaEuclideana(i, j, coordenadas1, coordenadas2);
	}

	public void agregarArista(int i, int j, Double distanciaEuclidea) {
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

	// Vecinos de un vertice
	public Set<Double> vecinos(int i) {
		verificarVertice(i);
		Set<Double> ret = new HashSet<Double>();
		for (int j = 0; j < tamano(); j++) {
			if (i != j && existeArista(i, j))
				ret.add(getArista(i,j));
		}
		return ret;
	}
	// Vecinos de un vertice
		public Set<Pair<Integer,Integer>> indice_vecinos(int i) {
			verificarVertice(i);
			Set<Pair<Integer,Integer>> ret = new HashSet<Pair<Integer,Integer>>();
			for (int j = 0; j < tamano(); j++) {
				if (i != j && existeArista(i, j))
					ret.add(new Pair<Integer,Integer>(i,j));
			}
			return ret;
		}

	// Cantidad de vertices
	public int tamano() {
		return A.length;
	}
	public void imprimir() {
		for(int i=0;i<tamano();i++){
			for(int j=0;j<tamano() && j!=i;j++){
				if(A[i][j]!=0.0)
					System.out.println("g["+i+"]["+j+"]= "+A[i][j]);
			}
		}
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


	private int cantAristas() {
		int ret=0;
		for(int i=0;i<tamano();i++) {
			for(int j=0;j<tamano() && j!=i;j++) {
				if(existeArista(i,j))
					ret++;
			}
		}
		return ret;
	}
	
	public Double[][] grafo() {
		return A.clone();
	}
	public Double[] getDistancias() {
		Double[] valores=new Double[cantAristas()];
		int cont=0;
		for(int i=0;i<tamano();i++) {
			for(int j=0;j<tamano() && j!=i;j++){
				if(existeArista(i,j)) {
					valores[cont]=getArista(i,j);
					cont++;
				}
			}
			
		}
		return valores;
	}
	public List<Pair<Integer,Integer>> getIndices() {
		ArrayList<Pair<Integer,Integer>>valores=new ArrayList<Pair<Integer,Integer>>();
		for(int i=0;i<tamano();i++) {
			for(int j=0;j<tamano() && j!=i;j++){
				if(existeArista(i,j)) 
					valores.add(new Pair<Integer,Integer>(i,j));		
			}
		}
		return valores;
	}
	public void setGrafo(Double[][] otro) {
		
		A=otro;
		}
	@Override
	public boolean equals(Object f) {
		if(f.getClass()!=getClass())
			return false;
		Grafo otro=(Grafo) f;
		boolean ret=tamano()==otro.tamano();
		for(int i=0;i<tamano() && ret ;i++){
			for(int j=0;j<tamano() && j!=i;j++) {
				ret=ret && distancia(i,j).equals(otro.distancia(i, j));
			}
		}
		return ret;
	}
	
	
}
