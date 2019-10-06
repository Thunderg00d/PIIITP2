package grafoTest;



import java.util.ArrayList;

import org.junit.Test;

import javafx.util.Pair;
import ordenar.Ordenar;

public class OrdenarTest {
	@Test
	public void test() {
		Double[]dis=new Double[4];
		dis[0]=2.0;
		dis[1]=2.2;
		dis[2]=1.0;
		dis[3]=2.3;
		
		ArrayList<Pair<Integer,Integer>> indices=new ArrayList<Pair<Integer,Integer>>();
		
		indices.add(new Pair<Integer,Integer>(0,1));
		indices.add(new Pair<Integer,Integer>(1,2));
		indices.add(new Pair<Integer,Integer>(2,3));
		indices.add(new Pair<Integer,Integer>(3,0));
		Ordenar ord=new Ordenar(dis,indices);
		
		Double[]esperado=new Double[4];
		esperado[0]=1.0;
		esperado[1]=2.0;
		esperado[2]=2.2;
		esperado[3]=2.3;
		
		ArrayList<Pair<Integer,Integer>> indiceEsperado=new ArrayList<Pair<Integer,Integer>>();
		indiceEsperado.add(new Pair<Integer,Integer>(2,3));
		indiceEsperado.add(new Pair<Integer,Integer>(0,1));
		indiceEsperado.add(new Pair<Integer,Integer>(1,2));
		indiceEsperado.add(new Pair<Integer,Integer>(3,0));
		
		Assert.iguales(ord, indiceEsperado,esperado);
		
	}

}
