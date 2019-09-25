package grafoTest;

import static org.junit.Assert.*;
import grafo.Grafo;

import java.util.ArrayList;

import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class GrafoTest {

	@Test
	public void PesoTest() {
		ArrayList<Coordinate>coordenadas=new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(6.0, 3.0));
		coordenadas.add(new Coordinate(10.0, 5.0));
		Grafo grafo=new Grafo(coordenadas);
		Double deltaX=Math.abs(6.0-3.0);
		Double deltaY=Math.abs(10.0-5.0);
		Double resultado=Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
		assertEquals(resultado,grafo.getArista(1, 0));
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void PesoMismoVerticeTest() {
		ArrayList<Coordinate>coordenadas=new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(6.0, 3.0));
		coordenadas.add(new Coordinate(10.0, 5.0));
		Grafo grafo=new Grafo(coordenadas);
		grafo.getArista(1,1);
	}

}
