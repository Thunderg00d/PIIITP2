package grafo;

import java.util.HashSet;
import java.util.Set;

public class BFS
{
	// Grafo
	private Grafo grafo;
	
	// Auxiliares para BFS
	private Set<Integer> pendientes;
	private boolean[] marcados;
	private Double pesoTotal = 0.0;
	
	public BFS(Grafo g)
	{
		grafo = g;
	}

	// Determina si el grafo es conexo
	public boolean esConexo()
	{
		inicializarAuxiliares();
		
		while( pendientes.size() != 0 )
		{
			//System.out.println(pendientes.toString());
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
		while( pendientes.size() != 0 )
		{
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
	
	// Inicializa los elementos auxiliares
	private void inicializarAuxiliares()
	{
		pendientes = singleton(0);
		marcados = new boolean[grafo.tamano()]; // Todos false
	}
	private void inicializarAuxiliares(int j)
	{
		pendientes = singleton(j);
		marcados = new boolean[grafo.tamano()]; // Todos false
	}

	// Vecinos no marcados de un vertice
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

	// Construye un set con el parametro como unico elemento
	private Set<Integer> singleton(int elemento)
	{
		Set<Integer> L = new HashSet<Integer>();
		L.add(elemento);
		
		return L;
	}

	// Determina estan todos marcados
	private boolean todosMarcados()
	{
		int i = 0;
		while( i < marcados.length && marcados[i] == true )
			++i;
		
		return i == marcados.length;
	}

	// Obtiene un elemento del set, si no esta vacio
	private int seleccionarPendiente()
	{
		for(Integer elemento: pendientes)
			return elemento;
		
		throw new IllegalArgumentException("El set esta vacio!");
	}
}
