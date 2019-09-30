package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
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

	public Grafo(ArrayList<Coordinate> lista) {
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
	Boolean isCyclicUtil(int v, Boolean marcados[], int parent) 
    { 
        // Mark the current node as visited 
        marcados[v] = true; 
        Integer i; 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> it = vecinos(v).iterator(); 
        while (it.hasNext()) 
        { 
            i = it.next(); 
  
            // If an adjacent is not visited, then recur for that 
            // adjacent 
            if (!marcados[i]) 
            { 
                if (isCyclicUtil(i, marcados, v)) 
                    return true; 
            } 
  
            // If an adjacent is visited and not parent of current 
            // vertex, then there is a cycle. 
            else if (i != parent) 
                return true; 
        } 
        return false; 
    } 
  
    // Returns true if the graph contains a cycle, else false. 
    Boolean isCyclic() 
    { 
        // Mark all the vertices as not visited and not part of 
        // recursion stack 
        Boolean marcados[] = new Boolean[tamano()]; 
        for (int i = 0; i <tamano(); i++) 
            marcados[i] = false; 
  
        // Call the recursive helper function to detect cycle in 
        // different DFS trees 
        for (int u = 0; u < tamano(); u++) 
            if (!marcados[u]) // Don't recur for u if already visited 
                if (isCyclicUtil(u, marcados, -1)) 
                    return true; 
  
        return false; 
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
		return A[i][j] != 0 ? true : false;
	}

	// Vecinos de un vertice
	public Set<Integer> vecinos(int i) {
		verificarVertice(i);

		Set<Integer> ret = new HashSet<Integer>();
		for (int j = 0; j < tamano(); ++j) {
			if (i != j && existeArista(i, j))
				ret.add(j);
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

	public Map<Double, Pair<Integer, Integer>> getMap() {
		Map<Double, Pair<Integer, Integer>> map = new LinkedHashMap<Double, Pair<Integer, Integer>>();
		for(int i=0;i<tamano();i++) {
			for(int j=0;j<tamano() && j!=i;j++){
				if(A[i][j]!=0.0){
				Pair<Integer,Integer>par=new Pair<Integer,Integer>(i,j);
				map.put(A[i][j],par);
				}
			}
		}
		return map;
	}
	

	/*public int cantAristas() {
		int cont = 0;
		for (int i = 0; i < tamano() - 1; i++) {////tamano-1??
			for (int j = 1 + i; j < tamano(); j++) {
				if(A[i][j] != 0.0) {
					cont++;
				}
			}
		}
		return cont;

	}*/
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
