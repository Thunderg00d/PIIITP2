package principal;

import java.io.Serializable;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Coordinate> coordenadas;
	
	public Estado() {
		coordenadas = new ArrayList<Coordinate>();
	}
	
	public void SetEstado(Coordinate c) {		
		coordenadas.add(c);	
	}

	public ArrayList<Coordinate> getCoordenadas(){
		return coordenadas;
	}
	
	
}