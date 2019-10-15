package grafoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import grafo.BFS;
import grafo.Grafo;

public class BFSTest {

	@Test(expected = IllegalArgumentException.class)
	public void verticeNullTest() {
		Grafo grafo = CrearGrafos.grafoNull();
		@SuppressWarnings("unused")
		BFS bfs = new BFS(grafo);
	}

	@Test
	public void todosAisladosTest() {
		Grafo grafo = CrearGrafos.todosAislados();
		assertFalse(new BFS(grafo).esConexo());
	}

	@Test
	public void trianguloTest() {
		Grafo grafo = CrearGrafos.grafoConexo2();
		assertTrue(new BFS(grafo).esConexo());
	}

	@Test
	public void dosTriangulosTest() {
		Grafo grafo = CrearGrafos.noConexo();
		assertFalse(new BFS(grafo).esConexo());
	}

	@Test
	public void verticeAisladoTest() {
		Grafo grafo = CrearGrafos.verticeAislado();
		assertFalse(new BFS(grafo).esConexo());
	}

	@Test
	public void pesoSubgrafoTest() {
		Grafo grafo = CrearGrafos.grafoConexo2();

		BFS bfs = new BFS(grafo);
		assertEquals(Double.valueOf(bfs.pesoSubgrafo(0)), Double.valueOf(43.0));
	}

	@Test
	public void verticesSubgrafoTest() {
		Grafo grafo = CrearGrafos.grafoConexo2();

		int[] verticesDelSubgrafo = new int[6];
		for (int i = 0; i < 6; i++)
			verticesDelSubgrafo[i] = i;
		BFS bfs = new BFS(grafo);
		
		Assert.iguales(verticesDelSubgrafo, bfs.verticesDelSubgrafo(0));
	}

}
