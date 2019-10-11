package clustering;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Cluster {
	private Set<Integer> vertices;
	private Double peso;
	private Color color;
	
	public Cluster(Double p,Set<Integer> v) {
		vertices = v;
		peso = p;
	}

	public Set<Integer> getVertices() {
		Set<Integer> clone = new HashSet<Integer>();
		for (Integer vertice : vertices) {
			clone.add(vertice);
		}
		return clone;
	}
	public void setColor(Color c) {
		color = c;
	}
	public Color getColor() {
		return color;
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
