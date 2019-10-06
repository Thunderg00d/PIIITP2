
	package grafoTest;

	import static org.junit.Assert.assertEquals;
import grafo.Grafo;

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
	}


