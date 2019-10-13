package ordenar;

import java.util.List;

import grafo.Grafo;
import javafx.util.Pair;

public class Ordenar {

	List<Pair<Integer,Integer>>ind;
	Grafo grafo;
	
	public Ordenar(Grafo g) {
		corroborarExistencia(g);
		grafo=new Grafo(g);
		ind=grafo.getIndices();
		quicksort(0,tamano()-1);
	}
	private void corroborarExistencia(Grafo g) {
		if(g==null)
			throw new IllegalArgumentException("El grafo no existe");
	}
	public int tamano() {
		return ind.size();
	}
	
	private void quicksort(int inicio, int fin) {
	    if (inicio < fin) {
	        int partitionIndex = partition(inicio, fin);
	 
	        quicksort(inicio, partitionIndex-1);
	        quicksort(partitionIndex+1, fin);
	    }
	}
	
	private int partition( int inicio, int fin) {
		//Pair<Integer,Integer>indiceFin=new Pair<Integer,Integer>(ind.get(fin).getKey(),ind.get)
	    Double pivot = grafo.getArista(ind.get(fin).getKey(), ind.get(fin).getValue());
	    int i = (inicio-1);
	 
	    for (int j = inicio; j < fin; j++) {
	    	if (grafo.getArista(ind.get(j).getKey(), ind.get(j).getValue()) <= pivot) {
	            i++;
	 
	           // Double swapTemp = d[i];
	            Pair<Integer,Integer> swapITemp = ind.get(i);
	            ind.set(i, ind.get(j));
	            ind.set(j, swapITemp);
	           // d[i] = d[j];
	           // d[j] = swapTemp;
	        }
	      
	    }
	    Pair<Integer,Integer> swapITemp=ind.get(i+1);
	  //  Double swapTemp = d[i+1];
	   // d[i+1] = d[fin];
	    ind.set(i+1, ind.get(fin));
	   // d[fin] = swapTemp;
	    ind.set(fin, swapITemp);
	 
	    return i+1;
	}
	public Pair<Integer,Integer>indices(int i){
		return ind.get(i);
	}
	
	public int indice_de(Pair<Integer,Integer>valor) {
		for(int i=0;i<ind.size();i++) {
			if(ind.get(i).equals(valor))
				return i;
		}
		return -1;

	}
	public Double getDistancia(int i) {
		return grafo.getArista(ind.get(i).getKey(), ind.get(i).getValue());
	}
	public List<Pair<Integer,Integer>>getIndices(){
		return ind;
	}
	
	
}
