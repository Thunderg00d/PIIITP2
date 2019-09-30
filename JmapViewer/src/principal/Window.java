package principal;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import Intermediario.Intermediario;
import grafo.AGM;
import grafo.Grafo;
import javafx.util.Pair;

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
		grafo = agm.calcularKruskal(grafo);
		
		ArrayList<Pair<Integer,Integer>>indices=grafo.getIndices();
		
		for(int i=0;i<indices.size();i++) {
			dibujarLinea(indices.get(i).getKey(),indices.get(i).getValue());
		}
		/*for (int i = 0; i < grafo.tamano() ; i++) { // Antes: Tamano()-1???
			for (int j = 0; j < grafo.tamano() && j!=i; j++) {
				if(grafo.getArista(i, j)!= 0.0) {
					dibujarLinea(i, j);
				}
			}
		}*/
			
	}

	private void dibujarLinea(int i, int j) {
		mapa.addMapPolygon(
				new MapPolygonImpl( 
				new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon()),
						new Coordinate(coordenadas.get(j).getLat(),coordenadas.get(j).getLon()),
								new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon())));
	}
}
