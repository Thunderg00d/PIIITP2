package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import Intermediario.Intermediario;
import clustering.Clustering;
import grafo.AGM;
import grafo.Grafo;
import javafx.util.Pair;

public class Mapa {
	private JPanel frame;
	private static JMapViewer mapa;
	private Intermediario intermediario;
	private ArrayList<Coordinate> coordenadas;
	private Grafo grafo;
	private AGM agm;
	private Clustering c;

	public Mapa() throws IOException {
		c=new Clustering();
		frame = new JPanel();
		frame.setBounds(100, 100, 650, 500);
		mapa = new JMapViewer();
		intermediario = new Intermediario();
		intermediario.setCoordenadas();
		coordenadas = intermediario.getCoordenadas();
		grafo = new Grafo(intermediario.getCoordenadas());
		agm = new AGM();
		grafo = agm.calcularKruskal(grafo);
		separarClusters();
		agregarCoordes();
		dibujarAristas();



	}

	private void dibujarLinea(int i, int j) {
		mapa.addMapPolygon(
				new MapPolygonImpl( 
						new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon()),
						new Coordinate(coordenadas.get(j).getLat(),coordenadas.get(j).getLon()),
						new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon())));
	}

	public JMapViewer getMap() {
		return mapa;
	}
	private void agregarCoordes() {
		for(Coordinate vertice : coordenadas) {
			mapa.addMapMarker(new MapMarkerDot(vertice.getLat(),vertice.getLon()));
		}
	}
	private void dibujarAristas() {
		List<Pair<Integer,Integer>>indices=grafo.getIndices();

		for(int i=0;i<indices.size();i++) {
			dibujarLinea(indices.get(i).getKey(),indices.get(i).getValue());
		}


	}
	private void separarClusters() {
		grafo.setGrafo(c.hacer_Clustering(grafo, 3).getMatriz());
	}

}




