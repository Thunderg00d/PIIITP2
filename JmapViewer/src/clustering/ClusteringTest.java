package clustering;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


import grafo.Grafo;
import grafoTest.Assert;
import grafoTest.CrearGrafos;

public class ClusteringTest {
	@Test(expected = IllegalArgumentException.class)
	public void ClusteringGrafoNullTest() {
		Grafo grafo=CrearGrafos.grafoNull();
		
		@SuppressWarnings("unused")
		Clustering c=new Clustering(grafo,3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ClusteringMasClustersQueAristas() {
		Grafo grafo=CrearGrafos.grafoConexo3();
		
		@SuppressWarnings("unused")
		Clustering c=new Clustering(grafo,100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ClusteringUnCluster() {
		Grafo grafo=CrearGrafos.grafoConexo3();
		
		@SuppressWarnings("unused")
		Clustering c=new Clustering(grafo,1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ClusteringCantidadClustersNegativa() {
		Grafo grafo=CrearGrafos.grafoConexo3();
		
		@SuppressWarnings("unused")
		Clustering c=new Clustering(grafo,-1);
	}
	
	@Test
	public void ClusteringMasPesadasGrafoNoConexo() {
		Grafo grafo=CrearGrafos.noConexo();
		Clustering c=new Clustering(grafo,2);
		assertEquals(c.aristasMasPesadas(),false);
	}
	
	@Test
	public void ClusteringPromedioGrafoNoConexo() {
		Grafo grafo=CrearGrafos.noConexo();
		Clustering c=new Clustering(grafo,2);
		assertEquals(c.promedio(),false);
	}
	
	@Test
	public void ClusteringAzarGrafoNoConexo() {
		Grafo grafo=CrearGrafos.noConexo();
		Clustering c=new Clustering(grafo,2);
		assertEquals(c.azar(),false);
	}
	
	@Test
	public void ClusteringMasPesadasTest() {
		Grafo grafo = CrearGrafos.grafoConexo1();	
		
		Grafo esperado=new Grafo(6);
		esperado.agregarArista(0, 2, 1.0);	
		esperado.agregarArista(1, 4, 3.0);
		esperado.agregarArista(2, 5, 4.0);
		esperado.agregarArista(5, 3, 2.0);
		Clustering c=new Clustering(grafo,2);
		c.aristasMasPesadas();
		
		Assert.iguales(esperado, c.getGrafo());	
	}

	@Test
	public void ClusteringMasPesadasPesoClustersTest() {
		Grafo grafo = CrearGrafos.grafoConexo1();

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
		
		Clustering c=new Clustering(grafo,2);
		c.aristasMasPesadas();
		
		List<Cluster>clusters=c.getClusters();
		
		assertTrue(clusters.contains(c1) && clusters.contains(c2));
	}
	
	@Test
	public void ClusteringMasPromedioPesoClustersTest() {
		Grafo grafo = CrearGrafos.grafoConexo1();
		
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
		
		Clustering c=new Clustering(grafo,2);
		c.promedio();
		
		List<Cluster>clusters=c.getClusters();
		
		assertTrue(clusters.contains(c1) && clusters.contains(c2));
	}
	
	
	@Test
	public void ClusteringAzarTest() {
		Grafo grafo = CrearGrafos.grafoConexo3();
		Clustering c=new Clustering(grafo,3);
		c.azar();
		
		assertEquals(c.getGrafo().cantAristas(),grafo.cantAristas()-2);
	}
	
	@Test
	public void ClusteringPromedioTest() {
		Grafo grafo = CrearGrafos.grafoConexo1();
		
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
	
		Clustering c=new Clustering(grafo,2);
		c.aristasMasPesadas();
		List<Cluster>clusters=c.getClusters();

		assertTrue(clusters.contains(c1) && clusters.contains(c2));
	}
	
}
