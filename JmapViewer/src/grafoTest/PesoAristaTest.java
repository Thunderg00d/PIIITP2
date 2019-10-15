package grafoTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafo.Grafo;

public class PesoAristaTest {
	
	@Test (expected = IllegalArgumentException.class)
	public void PesoSegundoIndiceExcedeTamano() {
		Grafo grafo=new Grafo(4);
		grafo.getArista(0, 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void PesoPrimerIndiceExcedeTamano() {
		Grafo grafo=new Grafo(4);
		grafo.getArista(10, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void PesoPrimerIndiceNegativo() {
		Grafo grafo=new Grafo(4);
		grafo.getArista(-1, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void PesoSegundoIndiceNegativo() {
		Grafo grafo=new Grafo(4);
		grafo.getArista(0, -1);
	}
	
	@Test
	public void PesoAristaExistenteTest() {
		List<Coordinate>coordenadas=new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(6.0, 3.0));
		coordenadas.add(new Coordinate(10.0, 5.0));
		Grafo grafo=new Grafo(coordenadas);
		assertEquals(Double.valueOf(496.29358833444417),grafo.getArista(1, 0));
	}

	@Test 
	public void PesoAristaSinPeso() {
		Grafo grafo=new Grafo(4);
		assertEquals(Double.valueOf(0.0),grafo.getArista(0, 1));
	}
	

}
