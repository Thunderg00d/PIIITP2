package grafoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Grafo;
import javafx.util.Pair;

public class VecinoMasPesadoTest {

	@Test
	public void vecinoMasPesadoTest() {
		Grafo grafo= CrearGrafos.grafoConexo1();
		/* Aristas del grafo 
	 	E(0, 2)= 1.0 
	 	E(1, 2)=5.0 
	 	E(1, 4)=3.0 
	 	E(2, 5)=4.0 
	 	E(5, 3)=2.0     */
		assertEquals(new Pair<Integer,Integer>(1,2),grafo.vecinoMasPesado(1));
	}
	
	@Test
	public void vecinoMasPesadoGrafoNullTest() {
		Grafo grafo= CrearGrafos.noConexo();
		assertEquals(new Pair<Integer,Integer>(0,0),grafo.vecinoMasPesado(4));
	}

}
