package grafoTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import grafo.Grafo;
import javafx.util.Pair;
import ordenar.Ordenar;

public class OrdenarTest {
	@Test
	public void Ordenartest() {
		Grafo grafo = CrearGrafos.grafoConexo1();
		
		List<Pair<Integer, Integer>> esperados = new ArrayList<Pair<Integer, Integer>>();
		esperados.add(new Pair<Integer, Integer>(0, 2));
		esperados.add(new Pair<Integer, Integer>(3, 5));
		esperados.add(new Pair<Integer, Integer>(1, 4));
		esperados.add(new Pair<Integer, Integer>(2, 5));
		esperados.add(new Pair<Integer, Integer>(1, 2));

		Ordenar ordenar = new Ordenar(grafo);
		Assert.iguales(ordenar.getIndices(), esperados);

	}

	@Test
	public void OrdenarGrafoVacioTest() {
		Grafo grafo = CrearGrafos.todosAislados();
		
		List<Pair<Integer, Integer>> esperados = new ArrayList<Pair<Integer, Integer>>();
		Ordenar ordenar = new Ordenar(grafo);
		
		Assert.iguales(ordenar.getIndices(), esperados);

	}

	@Test(expected = IllegalArgumentException.class)
	public void OrdenarGrafoNullTest() {
		Grafo grafo = CrearGrafos.grafoNull();
		
		@SuppressWarnings("unused")
		Ordenar ordenar = new Ordenar(grafo);
	}

}
