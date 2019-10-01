package ordenar;

import java.util.List;

import javafx.util.Pair;

public class Ordenar {
	Double []d;
	List<Pair<Integer,Integer>>ind;
	
	
	public Ordenar(Double[]distancias,List<Pair<Integer, Integer>> list,String op) {
		if(distancias.length!=list.size())
			throw new IllegalArgumentException("Los parametros deben tener el mismo largo");
		d=distancias;
		ind=list;
		quicksort(0,tamano()-1,op);
	}
	public int tamano() {
		return d.length;
	}
	
	private void quicksort(int inicio, int fin, String s) {
	    if (inicio < fin) {
	        int partitionIndex = partition(inicio, fin,s);
	 
	        quicksort(inicio, partitionIndex-1,s);
	        quicksort(partitionIndex+1, fin,s);
	    }
	}
	
	private int partition( int inicio, int fin,String s) {
	    Double pivot = d[fin];
	    int i = (inicio-1);
	 
	    for (int j = inicio; j < fin; j++) {
	      if(s.equals("ascendente")) {
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
	      if(s.contentEquals("descendente")) {
	    	  if (d[j] >= pivot) {
		            i++;
		 
		            Double swapTemp = d[i];
		            Pair<Integer,Integer> swapITemp = ind.get(i);
		            ind.set(i, ind.get(j));
		            ind.set(j, swapITemp);
		            d[i] = d[j];
		            d[j] = swapTemp;
		        }
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
