package grafo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import javafx.util.Pair;

public class AGM {
	
	private LinkedHashMap<Double, Pair<Integer, Integer>> aristas;
	private Set<Coordinate> setC;
	
	private Double[] aristasOrdenadas;
	private Grafo grafo;
	private int[] vertices;
	Grafo agm;
	
	
	public void ordenadasAristas(Grafo gr) {
		

		
		aristas = (LinkedHashMap<Double, Pair<Integer, Integer>>) gr.getMap();
		Double[] prueba = new Double[aristas.size()];
		aristas.keySet().toArray(prueba);
		
		quickSort qs = new quickSort();
		qs.sort(prueba, 0, prueba.length-1);
		aristasOrdenadas = prueba;
		grafo = new Grafo(gr.tamano(), null);
		
	}
	private boolean formaCiclo(Double arista ) {
		
		agm.agregarArista(aristas.get(arista).getKey(), aristas.get(arista).getValue(), arista);
		Grafo grafoTemporal  = new Grafo(agm);
		if(grafoTemporal.isCyclic()) {
			agm.borrarArista(aristas.get(arista).getKey(), aristas.get(arista).getValue());

			return true;
		}
		return false;
	}
	public Grafo calcularKruskal(Grafo gr, ArrayList<Coordinate> cord) {
		
		ordenadasAristas(gr);
		
		
		grafo.inicializarMatriz();
		agm = new Grafo(grafo.tamano(),cord );
		agm.inicializarMatriz();
		for(int i = 0; i<aristasOrdenadas.length; i++) {
			if(!formaCiclo(aristasOrdenadas[i])){
				grafo.agregarArista(aristas.get(aristasOrdenadas[i]).getKey(), aristas.get(aristasOrdenadas[i]).getValue(), aristasOrdenadas[i]);
			}
			
		}
		return grafo;
	}

}
