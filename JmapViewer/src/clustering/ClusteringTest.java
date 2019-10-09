package clustering;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


import grafo.Grafo;
import grafoTest.Assert;

public class ClusteringTest {

	@Test
	public void testClustering() {
		Grafo grafo=new Grafo(6);
		grafo.agregarArista(0, 2, 1.0);	
		grafo.agregarArista(1, 2, 5.0);	
		grafo.agregarArista(1, 4, 3.0);
		grafo.agregarArista(2, 5, 4.0);
		grafo.agregarArista(5, 3, 2.0);
		
		Grafo esperado=new Grafo(6);
		esperado.agregarArista(0, 2, 1.0);	
		esperado.agregarArista(1, 4, 3.0);
		esperado.agregarArista(2, 5, 4.0);
		esperado.agregarArista(5, 3, 2.0);
		Clustering c=new Clustering(grafo);
		c.clusteringAristasMasPesadas(2);
		Assert.iguales(esperado, grafo);	
	}
	@Test
	public void testClusteringPeso() {
		Grafo grafo=new Grafo(6);
		// 0 2 3 5 ( 1+4+2 )=7
		// 1 4 (3)=3
		grafo.agregarArista(0, 2, 1.0);	
		grafo.agregarArista(1, 2, 5.0);	
		grafo.agregarArista(1, 4, 3.0);
		grafo.agregarArista(2, 5, 4.0);
		grafo.agregarArista(5, 3, 2.0);
		
		Set<Integer>vertices1= new HashSet<Integer>();
		vertices1.add(0);
		vertices1.add(5);
		vertices1.add(3);
		vertices1.add(2);
		Double peso1=7.0; 
		Cluster c1=new Cluster(peso1,vertices1);
		Set<Integer>vertices2= new HashSet<Integer>();
		vertices2.add(4);
		vertices2.add(1);
		Double peso2=3.0;
		Cluster c2=new Cluster(peso2,vertices2);
		
		boolean ret=true;
		Clustering c=new Clustering(grafo);
		c.clusteringAristasMasPesadas(2);
		List<Cluster>clusters=c.getClusters();
		
		
		for(Cluster cluster: clusters) {
			System.out.println(cluster.getVertices().toString()+" "+cluster.getPeso().toString());
		}
		
		
		ret = ret && clusters.contains(c1) && clusters.contains(c2);
		
		
		assertEquals(true,ret);
	}

	
}
