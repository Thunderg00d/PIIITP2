package Intermediario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import txtData.LectorTxt;

public class Intermediario implements Cloneable{
	private ArrayList<Coordinate> coordenadas;
	private LectorTxt lector;
	
	
	public Intermediario() {
		coordenadas = new ArrayList<Coordinate>();
		lector = new LectorTxt();
	}
	
	public void  setCoordenadas(ArrayList<String> instancias) throws IOException{
		ArrayList<String> coordesTemp = new ArrayList<String>();
		for(String s : instancias) {
			for(String j : lector.LeerArchivo(s)) {
				coordesTemp.add(j);	
			}
		}
		for(String s : coordesTemp) {
			Coordinate coordenada= new Coordinate(0.0,0.0);
			String numActual = "";
			Double latitud = 0.0;
			Double longitud = 0.0;
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '-' || s.charAt(j) == ' ') {
					if (numActual.length() > 0) {
						latitud = Double.valueOf(numActual) * -1;
						numActual = "";
					}
				} else {
					numActual += s.charAt(j);
					if (s.length() == j + 1) {
						longitud = Double.valueOf(numActual) * -1;
						coordenada.setLat(latitud);
						coordenada.setLon(longitud);
						coordenadas.add(coordenada);
					}
				}
			}
		}
	}
	public List<Coordinate> getCoordenadas(){
		return coordenadas;
	}
}
