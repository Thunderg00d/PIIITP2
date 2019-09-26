package grafo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BFS
{
	// Grafo
	private Grafo grafo;
	
	// Auxiliares para BFS
	private Set<Integer> pendientes;
	private boolean[] marcados;
	
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
			int i = seleccionarPendiente();
			marcados[i] = true;
			
			pendientes.addAll(vecinosNoMarcados(i));
			pendientes.remove(i);
		}
			
		return todosMarcados();
	}
	Boolean isCyclicUtil(int v, Boolean marcados[], int parent) 
    { 
        // Mark the current node as visited 
        marcados[v] = true; 
        Integer i; 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> it = grafo.vecinos(v).iterator(); 
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
        Boolean marcados[] = new Boolean[grafo.tamano()]; 
        for (int i = 0; i < grafo.tamano(); i++) 
            marcados[i] = false; 
  
        // Call the recursive helper function to detect cycle in 
        // different DFS trees 
        for (int u = 0; u < grafo.tamano(); u++) 
            if (!marcados[u]) // Don't recur for u if already visited 
                if (isCyclicUtil(u, marcados, -1)) 
                    return true; 
  
        return false; 
    } 
	public boolean tieneComponenteConexa() {
		inicializarAuxiliares();
		while(pendientes.size() != 0) {
			int i = seleccionarPendiente();
				marcados[i] = true;
			
			pendientes.addAll(vecinosNoMarcados(i));
			pendientes.remove(i);
		}
		return todosMarcados();
	}
	
	// Inicializa los elementos auxiliares
	private void inicializarAuxiliares()
	{
		pendientes = singleton(0);
		marcados = new boolean[grafo.tamano()]; // Todos false
	}

	// Vecinos no marcados de un vertice
	private Set<Integer> vecinosNoMarcados(int i)
	{
		Set<Integer> ret = new HashSet<Integer>();
		for(Integer j: grafo.vecinos(i) ) if( marcados[j] == false )
			ret.add(j);
		
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








