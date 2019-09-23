package grafoTest;

import static org.junit.Assert.*;
import grafo.Grafo;

import java.util.HashMap;

import org.junit.Test;

public class GrafoTest {

	@Test
	public void PesoTest() {
		HashMap<Double,Double>coordenadas=new HashMap<Double,Double>();
		coordenadas.put(6.0, 3.0);
		coordenadas.put(10.0, 5.0);
		Grafo grafo=new Grafo(coordenadas);
		Double deltaX=Math.abs(6.0-3.0);
		Double deltaY=Math.abs(10.0-5.0);
		Double resultado=Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
		assertEquals(resultado,grafo.getArista(0, 1));
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void PesoMismoVerticeTest() {
		HashMap<Double,Double>coordenadas=new HashMap<Double,Double>();
		coordenadas.put(6.0, 3.0);
		coordenadas.put(10.0, 5.0);
		Grafo grafo=new Grafo(coordenadas);
		grafo.getArista(1,1);
	}

}
