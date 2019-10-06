package grafoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafo.Grafo;

public class PesoAristaTest {

	@Test
	public void PesoTest() {
		ArrayList<Coordinate>coordenadas=new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(6.0, 3.0));
		coordenadas.add(new Coordinate(10.0, 5.0));
		Grafo grafo=new Grafo(coordenadas);
		assertEquals(Double.valueOf(496.29358833444417),grafo.getArista(1, 0));
	}

	@Test
	public void PesoTest2() {
		ArrayList<Coordinate>coordenadas=new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(10.0, 5.0));
		coordenadas.add(new Coordinate(6.0, 3.0));
		Grafo grafo=new Grafo(coordenadas);
		assertEquals(Double.valueOf(496.29358833444417),grafo.getArista(1, 0));
	}

}
