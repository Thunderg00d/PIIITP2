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
		grafo.agregarArista(0, 1, 6.0);
		grafo.agregarArista(0, 2, 1.0);
		grafo.agregarArista(0, 3, 5.0); 
		
		grafo.agregarArista(1, 2, 5.0);
		grafo.agregarArista(1, 4, 3.0);
		
		grafo.agregarArista(2, 3, 5.0); 
		grafo.agregarArista(2, 5, 4.0);
		grafo.agregarArista(2, 4, 6.0);
		
		grafo.agregarArista(5, 4, 6.0); 
		grafo.agregarArista(5,3, 2.0);
		
		Grafo esperado=new Grafo(6);
		esperado.agregarArista(0, 2, 1.0);
		
		esperado.agregarArista(1, 2, 5.0);
		
		esperado.agregarArista(1, 4, 3.0);
		
		esperado.agregarArista(2, 5, 4.0);
		
		esperado.agregarArista(5, 3, 2.0);
		AGM a=new AGM();
		assertEquals(a.calcularKruskal(grafo),esperado);
	}
	
	@Test
	public void AGM2Test() {
		Grafo grafo=new Grafo(9);
		//A
		grafo.agregarArista(0, 1, 4.0); //AB
		grafo.agregarArista(0, 7, 8.0); //AH
		
		//B
		grafo.agregarArista(1, 2, 8.0); //BC
		grafo.agregarArista(1, 7, 12.0); //BH
		
		//C
		grafo.agregarArista(2, 3, 6.0); //CD
		grafo.agregarArista(2, 8, 3.0); //CI
		grafo.agregarArista(2,5 , 4.0); //CF
		
		//D
		grafo.agregarArista(3, 4, 9.0); //DE
		grafo.agregarArista(3, 5, 13.0); //DF
		
		//E
		grafo.agregarArista(4, 5, 10.0);//EF
		
		//F
		grafo.agregarArista(5, 6, 3.0); //FG
		
		//G
		grafo.agregarArista(6, 8, 5.0); //GI
		grafo.agregarArista(6, 7, 1.0); //GH
		
		//H
		grafo.agregarArista(7, 8, 6.0); //HI
		grafo.agregarArista(7, 1, 12.0); //HB
		
		
		Grafo esperado=new Grafo(9);
		//A
		esperado.agregarArista(0, 1, 4.0);//AB
		esperado.agregarArista(0, 7, 8.0); //AH
	
		//C
		esperado.agregarArista(2, 3, 6.0); //CD
		esperado.agregarArista(2, 5, 4.0); //CF
		esperado.agregarArista(2, 8, 3.0); //CI
		
		//D
		esperado.agregarArista(3, 4, 9.0); //DE
		
		//F
		esperado.agregarArista(5, 6, 3.0); //FG
		
		//G
		esperado.agregarArista(6, 7, 1.0); //GH
		
		//I
		esperado.agregarArista(8, 2, 3.0); //IC
		AGM a=new AGM();
		assertEquals(a.calcularKruskal(grafo),esperado);
	}
	
	
	
	
	
	

}
