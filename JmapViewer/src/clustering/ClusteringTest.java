package clustering;

import org.junit.Test;

import grafo.Grafo;
import grafoTest.Assert;

public class ClusteringTest {

	/*@Test
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
		
		Clustering c=new Clustering(grafo);
		c.clusteringAristasMasPesadas(2);
		Assert.iguales(esperado, grafo);	
	}*/
	@Test
	public void testClusteringPromedio() {
		/*
		 * Promedio 0= (2.0+2.0+3.0+2.0) /4 = 2.25
		 * Promedio 1= (2.0+5.0+1.0) /3 = 2.66
		 * Promedio 2= (2.0+5.0+2.0) /3 = 3
		 * Promedio 3= (3.0+3.0+2.0) /3 = 2.33
		 * Promedio 4= (2.0+2.0+1.0) /3 = 1.66
		 * 
		 * Mayor vecino del 2= (1,2,5.0)
		 * Mayor vecino del 1= (0,1,2.0)
		 */
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1, 2.0);
		grafo.agregarArista(0, 2, 2.0);
		grafo.agregarArista(0, 3, 3.0);
		grafo.agregarArista(0, 4, 2.0);
		grafo.agregarArista(1, 2, 5.0);
		grafo.agregarArista(1, 4, 1.0);
		grafo.agregarArista(2, 3, 2.0);
		grafo.agregarArista(3, 4, 2.0);
		
		Grafo esperado=new Grafo(5);

		esperado.agregarArista(0, 2, 2.0);
		esperado.agregarArista(0, 4, 2.0);
		esperado.agregarArista(0, 3, 3.0);
		esperado.agregarArista(1, 4, 1.0);
		esperado.agregarArista(2, 3, 2.0);
		esperado.agregarArista(3, 4, 2.0);
		
		
		Clustering c=new Clustering(grafo);
		c.clusteringVecinosMasPesados(3);
		
		Assert.iguales(esperado, grafo);	
	}
	/*@Test
	public void testClusteringPromedio2() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(0, 1, 2.0);
		grafo.agregarArista(0, 2, 2.0);
		grafo.agregarArista(0, 3, 5.0);
		grafo.agregarArista(0, 4, 2.0);
		grafo.agregarArista(1, 2, 5.0); 
		grafo.agregarArista(1, 4, 1.0); 
		grafo.agregarArista(2, 3, 2.0); 
		grafo.agregarArista(3, 4, 112.0); 
		
		
		Grafo esperado=new Grafo(5);
		esperado.agregarArista(0, 1, 2.0);
		esperado.agregarArista(0, 2, 2.0);
		esperado.agregarArista(0, 3, 5.0);
		esperado.agregarArista(1, 4, 1.0); 
		esperado.agregarArista(2, 3, 2.0); 
		
		Clustering c=new Clustering(grafo);
		c.clusteringVecinosMasPesados(4);
		Assert.iguales(esperado, grafo);	
	}*/
	
	
}
