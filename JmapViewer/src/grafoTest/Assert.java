
package grafoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import grafo.Grafo;
import javafx.util.Pair;

public class Assert {
	public static void iguales(Grafo grafo1, Grafo grafo2) {
		assertEquals(grafo1.tamano(), grafo2.tamano());
		for (int i = 0; i < grafo1.tamano(); i++) {
			for (int j = 0; j < grafo1.tamano() && j != i; j++) {
				assertEquals(grafo1.getArista(i, j), grafo2.getArista(i, j));
			}
		}
	}

	public static void iguales(List<Pair<Integer, Integer>> indicesEsperados,
			List<Pair<Integer, Integer>> indicesResultantes) {
		assertEquals(indicesEsperados.size(), indicesResultantes.size());
		for (Pair<Integer, Integer> indice : indicesResultantes) {
			assertTrue(indicesEsperados.contains(indice));
		}
	}

	public static void iguales(int[] expected, Set<Integer> obtained) {
		assertEquals(expected.length, obtained.size());

		for (Integer valor : expected)
			assertTrue(obtained.contains(valor));
	}

}
