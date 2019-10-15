package grafoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.Grafo;
import javafx.util.Pair;

public class VecinoMasPesadoTest {

	@Test
	public void vecinoMasPesadoTest() {
		Grafo grafo= CrearGrafos.grafoConexo1();
	
		assertEquals(new Pair<Integer,Integer>(1,2),grafo.vecinoMasPesado(1));
	}
	
	@Test
	public void vecinoMasPesadoGrafoNoConexoTest() {
		Grafo grafo= CrearGrafos.noConexo();
		
		assertEquals(new Pair<Integer,Integer>(0,0),grafo.vecinoMasPesado(4));
	}
	
	@Test
	public void vecinoMasPesadoTodosAisladosTest() {
		Grafo grafo= CrearGrafos.todosAislados();
		
		assertEquals(new Pair<Integer,Integer>(0,0),grafo.vecinoMasPesado(4));
	}
}
