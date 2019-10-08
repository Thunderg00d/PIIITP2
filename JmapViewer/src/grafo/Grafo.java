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
	
	public Grafo(List<Coordinate> coordenadas) {
		A = new Double[coordenadas.size()][coordenadas.size()];
		for (int i = 0; i < tamano(); i++) {
			for (int j =i+1; j < tamano()&& i!=j; j++) {
					distanciaEuclideana(i, j, coordenadas.get(i), coordenadas.get(j));
			}
		}
	}
	
	public Grafo(Grafo grafo) {
		A=grafo.grafo();
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
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(coordenadas1.getLat())) * Math.cos(Math.toRadians( coordenadas2.getLat()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        A[i][j] = A[j][i]  = radioTierra * va2;
	}

	// Operaciones sobre aristas
	public void agregarArista(int i, int j, Coordinate coordenadas1, Coordinate coordenadas2) {
		verificarIndices(i, j);
		distanciaEuclideana(i, j, coordenadas1, coordenadas2);
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

	// Vecinos de un vertice
	public Set<Integer> vecinos(int i){
		verificarVertice(i);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j=0; j<tamano(); ++j) 
			if( i!=j && existeArista(i,j) )
			    ret.add(j);
		
		return ret;
	}
		public Pair<Integer,Integer>vecinoMasPesado(int i){
			Pair<Integer,Integer>masPesado=new Pair<Integer,Integer>(0,0);
			double mayor=0.0;
			for(int j=0; j<tamano(); ++j) 
				if( i!=j && existeArista(i,j) && getArista(i,j)>mayor) {
				    mayor=getArista(i,j);
				    masPesado=new Pair<Integer,Integer>(i,j);
				}
			return masPesado;
		}
		public Double promedioVecinos(int vertice){
			Double pesos=0.0;
			int cantidadVecinos=0;
			for(int i=0;i<tamano();i++) {
				if(i!=vertice && existeArista(i,vertice)) {
					pesos+=getArista(i,vertice);
					cantidadVecinos++;
				}
			}
			return pesos/cantidadVecinos;
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

	public int cantAristas() {
		int ret=0;
		for(int i=0;i<tamano();i++) {
			for(int j=i+1;j<tamano() && j!=i;j++) {
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
			for(int j=i+1;j<tamano() && j!=i;j++){
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
			for(int j=i+1;j<tamano() && j!=i;j++){
				if(existeArista(i,j)) 
					valores.add(new Pair<Integer,Integer>(i,j));		
			}
		}
		return valores;
	}
	
}
