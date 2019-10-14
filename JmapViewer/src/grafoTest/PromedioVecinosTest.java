package grafoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Grafo;

public class PromedioVecinosTest {

	@Test
	public void promedioVecinosTest() {
		Grafo grafo=CrearGrafos.grafoConexo1();
		/* Aristas del grafo 
	 	E(0, 2)= 1.0 
	 	E(1, 2)=5.0 
	 	E(1, 4)=3.0 
	 	E(2, 5)=4.0 
	 	E(5, 3)=2.0     */
		assertEquals(Double.valueOf(1.0),grafo.promedioVecinos(0));
	}
	
	@Test
	public void promedioVecinosTodosAisladosTest() {
		Grafo grafo=CrearGrafos.todosAislados();
		assertEquals(Double.valueOf(0.0),grafo.promedioVecinos(0));
	}
}
