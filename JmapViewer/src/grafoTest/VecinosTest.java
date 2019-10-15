package grafoTest;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import grafo.Grafo;

public class VecinosTest {

	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest() {
		Grafo grafo = new Grafo(5);
		
		grafo.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticeExcedidoTest() {
		Grafo grafo = new Grafo(5);
		
		grafo.vecinos(5);
	}

	@Test
	public void grafoSinAristasTest() {
		Grafo grafo = new Grafo(5);
		
		assertEquals(0, grafo.vecinos(2).size());
	}

	@Test
	public void verticeAisladoTest() {
		Grafo grafo = CrearGrafos.cuadradoMasPunto();
		
		assertEquals(0, grafo.vecinos(0).size());
	}

	@Test
	public void dosVecinosTest() {
		Grafo grafo = CrearGrafos.cuadradoMasPunto();
		
		Set<Integer> vecinos = grafo.vecinos(2);

		int[] esperado = { 1, 4 };
		Assert.iguales(esperado, vecinos);
	}

	@Test
	public void verticeUniversalTest() {
		Grafo grafo = CrearGrafos.cuatroRueda();
		
		Set<Integer> vecinos = grafo.vecinos(0);

		int[] esperado = { 1, 2, 3, 4 };
		Assert.iguales(esperado, vecinos);
	}

	
	

}
