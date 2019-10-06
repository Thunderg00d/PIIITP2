
	package grafoTest;

	import static org.junit.Assert.assertEquals;

import java.util.List;

import grafo.Grafo;
import javafx.util.Pair;
import ordenar.Ordenar;

	public class Assert
	{
		public static void iguales(Grafo grafo1,Grafo grafo2)
		{
			assertEquals(grafo1.tamano(), grafo2.tamano());
			for(int i=0;i<grafo1.tamano();i++) {
				for(int j=0;j<grafo1.tamano() && j!=i;j++) {
					assertEquals(grafo1.getArista(i,j),grafo2.getArista(i, j));
				}
			}
		}
		public static void iguales(Ordenar ordenar, List<Pair<Integer,Integer>> indicesResultantes,Double[]distancias) {
			assertEquals(ordenar.tamano(),indicesResultantes.size(),distancias.length);
			for(int i=0;i<ordenar.tamano();i++) {
				assertEquals(ordenar.getDistancia(i),distancias[i]);
				assertEquals(ordenar.indices(i),indicesResultantes.get(i));
			}
		}
	}


