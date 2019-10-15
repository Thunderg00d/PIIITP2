package grafoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Grafo;

public class PromedioVecinosTest {

	@Test
	public void promedioVecinosTest() {
		Grafo grafo=CrearGrafos.grafoConexo1();

		assertEquals(Double.valueOf(1.0),grafo.promedioVecinos(0));
	}
	
	@Test
	public void promedioVecinosTodosAisladosTest() {
		Grafo grafo=CrearGrafos.todosAislados();
		
		assertEquals(Double.valueOf(0.0),grafo.promedioVecinos(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void promedioVecinosIndiceNegativoTest(){
		Grafo grafo=CrearGrafos.grafoConexo1();
		
		grafo.promedioVecinos(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void promedioVecinosIndiceExcedidoTest(){
		Grafo grafo=CrearGrafos.grafoConexo1();
		
		grafo.promedioVecinos(10);
	}
}
