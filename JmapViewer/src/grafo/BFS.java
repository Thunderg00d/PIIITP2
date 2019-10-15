package grafo;

import java.util.HashSet;
import java.util.Set;

public class BFS
{
	
	private Grafo grafo;
	private Set<Integer> pendientes;
	private boolean[] marcados;
	private Double pesoTotal = 0.0;
	
	public BFS(Grafo g){
		corroborarExistencia(g);
		grafo = g;
	}
	
	private void corroborarExistencia(Grafo g) {
		if(g==null)
			throw new IllegalArgumentException("El grafo no existe");
	}
	
	public boolean esConexo(){
		inicializarAuxiliares();
		
		while( pendientes.size() != 0 ){
			int i = seleccionarPendiente();
			marcados[i] = true;
			
			pendientes.addAll(vecinosNoMarcados(i));
			pendientes.remove(i);
		}
			pesoTotal=0.0;
		return todosMarcados();
	}
	
	public double pesoSubgrafo(int j) {
		inicializarAuxiliares(j);
		while( pendientes.size() != 0 ){
			int i = seleccionarPendiente();
			marcados[i] = true;
			pendientes.addAll(vecinosNoMarcados(i));
			pendientes.remove(i);
		}
		double temp = pesoTotal;
			pesoTotal=0.0;
		return temp;
	}
	
	public Set<Integer> verticesDelSubgrafo(int j){
		Set<Integer> vertices = new HashSet<Integer>();
		inicializarAuxiliares(j);
	
		while( pendientes.size() != 0 ){
			int i = seleccionarPendiente();
			marcados[i] = true;
			pendientes.addAll(vecinosNoMarcados(i));
			vertices.addAll(vecinosNoMarcados(i));
			pendientes.remove(i);
		}
		vertices.add(j);
		return vertices;
	}
	

	private void inicializarAuxiliares(){
		pendientes = singleton(0);
		marcados = new boolean[grafo.tamano()]; 
	}
	
	private void inicializarAuxiliares(int j){
		pendientes = singleton(j);
		marcados = new boolean[grafo.tamano()]; 
	}

	private Set<Integer> vecinosNoMarcados(int i){
		Set<Integer> ret = new HashSet<Integer>();
		for(Integer vertice: grafo.vecinos(i) ) {
			if( marcados[vertice] == false ) {
				pesoTotal+=grafo.getArista(i, vertice);
				ret.add(vertice);		
			}
	    }
		return ret;
	}

	private Set<Integer> singleton(int elemento){
		Set<Integer> L = new HashSet<Integer>();
		L.add(elemento);
		return L;
	}

	private boolean todosMarcados(){
		int i = 0;
		while( i < marcados.length && marcados[i] == true )
			++i;
		
		return i == marcados.length;
	}

	private int seleccionarPendiente(){
		for(Integer elemento: pendientes)
			return elemento;
		
		throw new IllegalArgumentException("El set esta vacio!");
	}
}
