package grafoTest;

import static org.junit.Assert.*;

import grafo.AGM;
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
		Double deltaX=6.0-10.0;
		Double deltaY=3.0-5.0;
		Double resultado=Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
		assertEquals(resultado,grafo.getArista(1, 0));
	}

	@Test
	public void PesoTest2() {
		ArrayList<Coordinate>coordenadas=new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(10.0, 5.0));
		coordenadas.add(new Coordinate(6.0, 3.0));
		Grafo grafo=new Grafo(coordenadas);
		Double deltaX=6.0-10.0;
		Double deltaY=3.0-5.0;
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
	@Test
	public void AGMTest() {
		Grafo grafo=new Grafo(6);
		grafo.agregarArista(0, 1, 6.1); //1
		grafo.agregarArista(0, 2, 1.0);
		grafo.agregarArista(0, 3, 5.2); //2
		
		grafo.agregarArista(1, 2, 5.0);
		grafo.agregarArista(1, 4, 3.0);
		
		grafo.agregarArista(2, 3, 5.3); //3
		grafo.agregarArista(2, 5, 4.0);
		grafo.agregarArista(2, 4, 6.0);
		
		grafo.agregarArista(5, 4, 6.2); //2
		grafo.agregarArista(5,3, 2.0);
		
		Grafo esperado=new Grafo(6);
		esperado.agregarArista(0, 2, 1.0);
		
		esperado.agregarArista(1, 2, 5.0);
		
		esperado.agregarArista(1, 4, 3.0);
		
		esperado.agregarArista(2, 5, 4.0);
		
		esperado.agregarArista(5, 3, 2.0);
		AGM a=new AGM();
		Grafo ret=a.calcularKruskal(grafo);
		System.out.println("____________________________________");
		ret.imprimir();
		System.out.println("____________________________________");
		esperado.imprimir();
		//grafo.imprimir();
		//System.out.println(esperado.equals(ret));
		assertEquals(true,esperado.equals(ret));
	}
	

}
