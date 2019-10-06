package grafoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import grafo.Grafo;

public class AgregarAristaTest {
	
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(-1, 4,3.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(5, 4, 3.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(3, -1, 3.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 5, 3.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticesIgualesTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 2, 3.0);
	}

	@Test
	public void aristaExistenteTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 4, 3.0);
		assertEquals(Double.valueOf(3.0),grafo.getArista(2, 4));
	}

	@Test
	public void aristaRepetidaTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 4, 3.0);
		
		grafo.agregarArista(2, 4, 3.0);
		assertEquals(Double.valueOf(3.0),grafo.getArista(2, 4));
	}
	
	@Test
	public void aristaInvertidaTest()
	{
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3, 3.0);
		assertEquals(Double.valueOf(3.0),grafo.getArista(2, 3));
	}
	
	@Test
	public void aristaInexistenteTest()
	{
		Grafo grafo = new Grafo(5);
		assertFalse( grafo.existeArista(2, 0) );
	}


}
