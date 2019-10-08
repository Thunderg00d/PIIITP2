package ordenar;

import java.util.List;

import javafx.util.Pair;

public class Ordenar {
	Double []d;
	List<Pair<Integer,Integer>>ind;
	
	
	public Ordenar(Double[]distancias,List<Pair<Integer, Integer>>indices) {
		if(distancias.length!=indices.size())
			throw new IllegalArgumentException("Los parametros deben tener el mismo largo");
		d=distancias;
		ind=indices;
		quicksort(0,tamano()-1);
	}
	public int tamano() {
		return d.length;
	}
	
	private void quicksort(int inicio, int fin) {
	    if (inicio < fin) {
	        int partitionIndex = partition(inicio, fin);
	 
	        quicksort(inicio, partitionIndex-1);
	        quicksort(partitionIndex+1, fin);
	    }
	}
	
	private int partition( int inicio, int fin) {
	    Double pivot = d[fin];
	    int i = (inicio-1);
	 
	    for (int j = inicio; j < fin; j++) {
	    	if (d[j] <= pivot) {
	            i++;
	 
	            Double swapTemp = d[i];
	            Pair<Integer,Integer> swapITemp = ind.get(i);
	            ind.set(i, ind.get(j));
	            ind.set(j, swapITemp);
	            d[i] = d[j];
	            d[j] = swapTemp;
	        }
	      
	    }
	    Pair<Integer,Integer> swapITemp=ind.get(i+1);
	    Double swapTemp = d[i+1];
	    d[i+1] = d[fin];
	    ind.set(i+1, ind.get(fin));
	    d[fin] = swapTemp;
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
		return d[i];
	}
	public Double[] ordenados() {
		return d;
	}
	
}
