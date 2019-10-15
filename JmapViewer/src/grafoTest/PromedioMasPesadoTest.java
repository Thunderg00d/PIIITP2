package grafoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Grafo;
import javafx.util.Pair;

public class PromedioMasPesadoTest {

	@Test
	public void promedioVecinosTest() {
		Grafo grafo=CrearGrafos.grafoConexo1();
		
		assertEquals(new Pair<Integer,Integer>(1,2),grafo.promedioMasPesado());
	}
	
	@Test
	public void promedioVecinosTodosAisladosTest() {
		Grafo grafo=CrearGrafos.todosAislados();
		
		assertEquals(new Pair<Integer,Integer>(0,0),grafo.promedioMasPesado());
	}

}
