package grafoTest;

import grafo.Grafo;

public class CrearGrafos {
	
	public static Grafo grafoConexo1() {
		Grafo grafo=new Grafo(6);
		
		grafo.agregarArista(0, 2, 1.0);	
		grafo.agregarArista(1, 2, 5.0);	
		grafo.agregarArista(1, 4, 3.0);
		grafo.agregarArista(2, 5, 4.0);
		grafo.agregarArista(5, 3, 2.0);
		
		return grafo;
	}
	
	public static Grafo verticeAislado() {
		Grafo grafo = new Grafo(4);
		
		grafo.agregarArista(0, 1, 2.0);
		grafo.agregarArista(0, 2,1.0);
		grafo.agregarArista(1, 2,4.0);
		
		return grafo;
	}
	
	public static Grafo grafoConexo2() {
		Grafo grafo=new Grafo(6);
		
		grafo.agregarArista(0, 1, 6.0);
		grafo.agregarArista(0, 2, 1.0);
		grafo.agregarArista(0, 3, 5.0); 
		grafo.agregarArista(1, 2, 5.0);
		grafo.agregarArista(1, 4, 3.0);
		grafo.agregarArista(2, 3, 5.0); 
		grafo.agregarArista(2, 5, 4.0);
		grafo.agregarArista(2, 4, 6.0);
		grafo.agregarArista(5, 4, 6.0); 
		grafo.agregarArista(5,3, 2.0);
		
		return grafo;
	}
	
	public static Grafo grafoConexo3() {
		Grafo grafo=new Grafo(9);
		
		grafo.agregarArista(0, 1, 4.0); 
		grafo.agregarArista(0, 7, 8.0); 
		grafo.agregarArista(1, 2, 8.0); 
		grafo.agregarArista(1, 7, 12.0); 
		grafo.agregarArista(2, 3, 6.0); 
		grafo.agregarArista(2, 8, 3.0); 
		grafo.agregarArista(2,5 , 4.0); 
		grafo.agregarArista(3, 4, 9.0); 
		grafo.agregarArista(3, 5, 13.0); 
		grafo.agregarArista(4, 5, 10.0);
		grafo.agregarArista(5, 6, 3.0); 
		grafo.agregarArista(6, 8, 5.0); 
		grafo.agregarArista(6, 7, 1.0); 
		grafo.agregarArista(7, 8, 6.0); 
		grafo.agregarArista(7, 1, 12.0);
		
		return grafo;
	}
	
	public static Grafo cuadradoMasPunto() {
		Grafo grafo = new Grafo(5);
		
		grafo.agregarArista(1, 2, 1.0);
		grafo.agregarArista(1, 3, 2.0);
		grafo.agregarArista(2, 4, 2.0);
		grafo.agregarArista(3, 4, 3.0);

		return grafo;
	}
	
	public static Grafo cuatroRueda() {
		Grafo grafo = new Grafo(5);
		
		grafo.agregarArista(0, 1, 2.0);
		grafo.agregarArista(0, 2, 2.0);
		grafo.agregarArista(0, 3, 3.0);
		grafo.agregarArista(0, 4, 2.0);
		grafo.agregarArista(1, 2, 2.0);
		grafo.agregarArista(2, 3, 2.0);
		grafo.agregarArista(3, 4, 2.0);
		grafo.agregarArista(1, 4, 1.0);

		return grafo;
	}
	
	public static Grafo todosAislados() {
		Grafo grafo=new Grafo(5);
		
		return grafo;
	}
	
	public static Grafo grafoNull() {
		return null;
	}
	
	public static Grafo noConexo() {
		Grafo grafo=new Grafo(5);
		
		grafo.agregarArista(0, 1, 3.0);
		grafo.agregarArista(2, 3,2.2);
		
		return grafo;
	}
}
