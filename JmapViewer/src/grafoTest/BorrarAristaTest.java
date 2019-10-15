package grafoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Grafo;

public class BorrarAristaTest {

	@Test
	public void borrarAristaExistenteTest() {
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(0, 1, 3.0);
		grafo.borrarArista(0, 1);
		assertFalse(grafo.existeArista(0, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.borrarArista(-1, 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		grafo.borrarArista(4, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.borrarArista(5, 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		grafo.borrarArista(2, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticesIgualesTest() {
		Grafo grafo = new Grafo(5);
		grafo.borrarArista(2, 2);
	}
	
	@Test
	public void aristaRepetidaTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 4, 3.0);
		grafo.borrarArista(2, 4);
		grafo.borrarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}
	
	@Test
	public void aristaInexistenteTest() {
		Grafo grafo = new Grafo(5);
		grafo.borrarArista(0, 1);
		assertFalse(grafo.existeArista(0, 1));
	}

	@Test
	public void aristaInvertidaTest() {
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(2, 3, 3.0);

		grafo.borrarArista(2, 3);
		assertFalse(grafo.existeArista(2, 3));
	}

}
