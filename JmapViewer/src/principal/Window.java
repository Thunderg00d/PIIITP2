package principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import Intermediario.Intermediario;
import grafo.AGM;
import grafo.Grafo;

public class Window {

	private JFrame frame;
	private static JMapViewer mapa;
	private Intermediario intermediario;
	private ArrayList<Coordinate> coordenadas;
	private Grafo grafo;
	private AGM agm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
					window.frame.add(mapa);
					Coordinate bsAs = new Coordinate (-34.5237,-58.7038);
 					mapa.setDisplayPosition(bsAs, 10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Window() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapa = new JMapViewer();
		intermediario = new Intermediario();
		intermediario.setCoordenadas();
		coordenadas = intermediario.getCoordenadas();
		for(Coordinate vertice : coordenadas) {
			mapa.addMapMarker(new MapMarkerDot(vertice.getLat(),vertice.getLon()));
		}
		grafo = new Grafo(intermediario.getCoordenadas());
		agm = new AGM();
		grafo = agm.calcularKruskal(grafo,coordenadas);
		for (int i = 0; i < grafo.tamano() - 1; i++) {
			for (int j = 1 + i; j < grafo.tamano(); j++) {
				if(grafo.getArista(i, j)!= 0.0) {
					mapa.addMapPolygon(
							new MapPolygonImpl( 
									new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon()),
									new Coordinate(coordenadas.get(j).getLat(),coordenadas.get(j).getLon()),
									new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon())));
				}
			}
		}
		/*DefaultMapController mapController = new DefaultMapController(mapa);
		mapController.setMovementMouseButton(MouseEvent.CLICK);*/
		/*for(Map.Entry<Double, Double> coordenadas : lista.entrySet()) {
			mapa.addMapMarker(new MapMarkerDot(coordenadas.getKey(),coordenadas.getValue()));
		}*/

	}
}
