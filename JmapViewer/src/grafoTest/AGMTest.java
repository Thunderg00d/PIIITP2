package grafoTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grafo.AGM;
import grafo.Grafo;

public class AGMTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void AGMGrafoNullTest() {
		Grafo grafo=CrearGrafos.grafoNull();
		AGM agm=new AGM();
		agm.calcularKruskal(grafo);
	}
	
	@Test
	public void AGM1Test() {
		Grafo grafo = CrearGrafos.grafoConexo2();
		
		Grafo esperado = new Grafo(6);
		esperado.agregarArista(0, 2, 1.0);
		esperado.agregarArista(1, 2, 5.0);
		esperado.agregarArista(1, 4, 3.0);
		esperado.agregarArista(2, 5, 4.0);
		esperado.agregarArista(5, 3, 2.0);
		AGM a = new AGM();
		
		Assert.iguales(a.calcularKruskal(grafo), esperado);
	}

	@Test
	public void AGM2Test() {
		Grafo grafo = CrearGrafos.grafoConexo3();

		Grafo esperado = new Grafo(9);
		esperado.agregarArista(0, 1, 4.0);
		esperado.agregarArista(0, 7, 8.0);
		esperado.agregarArista(2, 3, 6.0);
		esperado.agregarArista(2, 5, 4.0);
		esperado.agregarArista(2, 8, 3.0);
		esperado.agregarArista(3, 4, 9.0);
		esperado.agregarArista(5, 6, 3.0);
		esperado.agregarArista(6, 7, 1.0);
		esperado.agregarArista(8, 2, 3.0);

		AGM a = new AGM();
		Assert.iguales(esperado, a.calcularKruskal(grafo));
	}

	@Test
	public void AGMGrafoNoConexoTest() {
		Grafo grafo = CrearGrafos.noConexo();
		AGM agm = new AGM();
		assertEquals(null, agm.calcularKruskal(grafo));
	}

}
