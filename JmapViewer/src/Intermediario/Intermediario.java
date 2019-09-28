package Intermediario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import txtData.LectorTxt;

public class Intermediario implements Cloneable{
	private ArrayList<Coordinate> coordenadas;
	private LectorTxt lector;
	
	
	public Intermediario() {
		coordenadas = new ArrayList<Coordinate>();
		lector = new LectorTxt();
	}
	
	public void  setCoordenadas() throws IOException{
		HashSet<Coordinate> temp = new HashSet<Coordinate>();
		String[] archivos = {"instancia3","instancia1","instancia2"};
		ArrayList<String> coordesTemp = new ArrayList<String>();
		HashSet<Coordinate> coordesTemp1 = new HashSet<Coordinate>();
		for(String s : archivos) {
			for(String j : lector.LeerArchivo(s)) {
				coordesTemp.add(j);	
			}
		}
		System.out.println(coordesTemp.size()+"coordestem");
		for(String s : coordesTemp) {
			Coordinate coordenada= new Coordinate(0.0,0.0);
			Coordinate coordenada1= new Coordinate(0.0,0.0);
			String numActual = "";
			Double latitud = 0.0;
			Double longitud = 0.0;
			Double latitud1 = 0.0;
			Double longitud1 = 0.0;
			String[] a = s.split(" ");
		
			if(a.length>1) {
				
				latitud1=Double.valueOf(a[0])*-1;
				longitud1=Double.valueOf(a[1])*-1;
				coordenada1.setLat(latitud1);
				coordenada1.setLon(longitud1);
				coordesTemp1.add(coordenada1);
			
			}
			
			
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
						
						temp.add(coordenada);
					}
				}
			}
		}
		System.out.println(coordesTemp1+"teempp");
		System.out.println(coordesTemp1.size());
		
		
		coordenadas.addAll(temp);
		coordenadas.size();
	}
	public ArrayList<Coordinate> getCoordenadas(){
		return coordenadas;
	}
}
