package Intermediario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import txtData.LectorTxt;

public class Intermediario implements Cloneable {
	private List<Coordinate> coordenadas;

	public Intermediario() {
		coordenadas = new ArrayList<Coordinate>();
	}

	public Intermediario(List<Double> value) {
		coordenadas = new ArrayList<Coordinate>();
		
		for (int i = 0; i < value.size(); i += 2) {
			coordenadas.add(new Coordinate(value.get(i), value.get(i + 1)));
		}
	}

	public void castear(String nombreArchivo) throws IOException {
		LectorTxt lector = new LectorTxt();
		List<String> lineas = lector.LeerArchivo(nombreArchivo);
		for (String s : lineas) {
			int espacio = s.indexOf(" ", 1);
			if (espacio != -1) {
				try {
					Double lat = Double.parseDouble(s.substring(0, espacio));
					Double lon = Double.parseDouble(s.substring(espacio + 1, s.length()));
					coordenadas.add(new Coordinate(lat, lon));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public List<Coordinate> getCoordenadas() {
		return coordenadas;
	}

	public void agregar(List<Coordinate> otrasCoordenadas) {
		coordenadas.addAll(otrasCoordenadas);
	}

	public List<Double> getDoubles() {
		List<Double> ret = new ArrayList<Double>();
		for (Coordinate coordenada : coordenadas) {
			ret.add(coordenada.getLat());
			ret.add(coordenada.getLon());
		}
		return ret;
	}

	public void eliminarUltimaCoordenada() {
		if (coordenadas.size() > 0) {
			coordenadas.remove(coordenadas.size() - 1);
		}
	}

	public void reiniciarValores() {
		coordenadas.clear();
	}

}
