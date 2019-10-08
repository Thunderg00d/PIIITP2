package grafoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Grafo;
import javafx.util.Pair;

public class VecinoMasPesadoTest {

	@Test
	public void test() {
		Grafo grafo=new Grafo(5);
		grafo.agregarArista(0, 1, 3.0);
		grafo.agregarArista(0, 2, 5.0);
		grafo.agregarArista(0, 3,22.0);
		assertEquals(new Pair<Integer,Integer>(0,3),grafo.vecinoMasPesado(0));
	}


}
