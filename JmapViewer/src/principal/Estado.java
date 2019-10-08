package principal;

import java.io.Serializable;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	private String estadoOps;
	private ArrayList<Coordinate> cantcaracteres;
	
	public Estado() {
		this.estadoOps="";
		this.cantcaracteres = new ArrayList<Coordinate>();
		
	}
	
	public void SetEstado(Coordinate c) {
		
		this.cantcaracteres.add(c);
		
	}
	

	public String getEstadoNums() {
		
		return this.estadoOps;
		
	}
	
	public ArrayList<Coordinate> getCantCaract(){
		return this.cantcaracteres;
	}
	
	
}