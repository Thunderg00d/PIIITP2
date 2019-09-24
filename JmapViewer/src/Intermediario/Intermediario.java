package Intermediario;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;



import txtData.LectorTxt;

public class Intermediario implements Cloneable{
	private LinkedList<Point2D.Double> coordenadas;
	private LectorTxt lector;
	
	
	public Intermediario() {
		coordenadas = new LinkedList<Point2D.Double>();
		lector = new LectorTxt();
	}
	
	private void  setCoordenadas() throws IOException{
		String[] archivos = {"instancia1","instancia2","instancia3","instancia4","instancia5"};
		ArrayList<String> coordesTemp = new ArrayList<String>();
		for(String s : archivos) {
			for(String j : lector.read(s)) {
				coordesTemp.add(j);	
			}
		}
		for(String s : coordesTemp) {
			Point2D.Double coordenada= new Point2D.Double();
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
						coordenada.setLocation(latitud, longitud);
						coordenadas.add(coordenada);
					}
				}
			}
		}
	}
	public LinkedList<Point2D.Double> getCoordenadas() throws IOException{
		setCoordenadas();
		LinkedList<Point2D.Double> temp = new LinkedList<Point2D.Double>();
		temp.addAll(coordenadas);
		return temp;
	}
}
