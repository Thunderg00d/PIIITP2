package ordenar;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.util.Pair;

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
		
		assertEquals(ord.getDistancia(0),Double.valueOf(1.0));
		assertEquals(ord.indices(0),new Pair<Integer,Integer>(2,3));
		
		assertEquals(ord.getDistancia(1),Double.valueOf(2.0));
		assertEquals(ord.indices(1),new Pair<Integer,Integer>(0,1));
		
		assertEquals(ord.getDistancia(2),Double.valueOf(2.2));
		assertEquals(ord.indices(2),new Pair<Integer,Integer>(1,2));
		
		assertEquals(ord.getDistancia(3),Double.valueOf(2.3));
		assertEquals(ord.indices(3),new Pair<Integer,Integer>(3,0));
		
		
		
	}

}
