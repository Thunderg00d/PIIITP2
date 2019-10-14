package clustering;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javafx.util.Pair;

public class Cluster {
	private Set<Integer> vertices;
	private Double peso;
	private Color color;
	private List<Pair<Color,String>> opciones;
	//private Color opciones[] = {Color.BLACK,Color.BLUE,Color.DARK_GRAY,Color.MAGENTA,Color.RED,Color.WHITE,Color.YELLOW};
	public Cluster(Double p,Set<Integer> v) {
		vertices = v;
		peso = p;
		inicializarOpciones();
		color=generarColor();
	}
	
	private void inicializarOpciones() {
		opciones=new ArrayList<Pair<Color,String>>();
		opciones.add(new Pair<Color,String>(Color.BLACK,"Negro"));
		opciones.add(new Pair<Color,String>(Color.RED,"Rojo"));
		opciones.add(new Pair<Color,String>(Color.GREEN,"Verde"));
		opciones.add(new Pair<Color,String>(Color.YELLOW,"Amarillo"));
		opciones.add(new Pair<Color,String>(Color.MAGENTA,"Magenta"));
		opciones.add(new Pair<Color,String>(Color.WHITE,"Blanco"));
		opciones.add(new Pair<Color,String>(Color.DARK_GRAY,"Gris"));
		opciones.add(new Pair<Color,String>(Color.BLUE,"Azul"));
		opciones.add(new Pair<Color,String>(Color.PINK,"Rosa"));
	}

	public Set<Integer> getVertices() {
		Set<Integer> clone = new HashSet<Integer>();
		for (Integer vertice : vertices) {
			clone.add(vertice);
		}
		return clone;
	}
	public Color generarColor() {
		Random r=new Random();
		int random = r.nextInt(opciones.size());
		return opciones.get(random).getKey();
	}
	public Color getColor() {
		return color;
	}
	public String getNombreColor() {
		for (Pair<Color,String>opcionColor: opciones) {
			if(opcionColor.getKey().equals(color))
				return opcionColor.getValue();
		}
		return "";
	}
	
	
	public int tamano() {
		return vertices.size();
	}

	public Double getPeso() {
		return peso;
	}

	@Override
	public boolean equals(Object o) {
		if (o.getClass() != getClass())
			return false;

		Cluster c = (Cluster) o;
		if (c.tamano() != tamano())
			return false;
		boolean ret = true;
		for (Integer vertice : c.getVertices()) {
			ret = ret && getVertices().contains(vertice);
		}
		ret = ret && getPeso().equals(c.getPeso());
		return ret;
	}

}
