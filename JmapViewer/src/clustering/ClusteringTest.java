package clustering;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Grafo;

public class ClusteringTest {

	@Test
	public void testClustering() {
		Grafo grafo=new Grafo(6);
		grafo.agregarArista(0, 2, 1.0);
		grafo.agregarArista(1, 2, 5.0);
		grafo.agregarArista(1, 4, 3.0);
		grafo.agregarArista(2, 5, 4.0);
		grafo.agregarArista(3, 5, 2.0);
		
		Grafo esperado=new Grafo(6);
		esperado.agregarArista(0, 2, 1.0);
		esperado.agregarArista(1, 4, 3.0);
		esperado.agregarArista(2, 5, 4.0);
		esperado.agregarArista(3, 5, 2.0);
		
		Clustering c=new Clustering();
	
		assertEquals(c.hacer_Clustering(grafo, 2),esperado);
		
		
	}

}
