package principal;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import txtData.LectorTxt;

public class Window {

	private JFrame frame;
	private static JMapViewer mapa;
	private LectorTxt lector;
	private ArrayList<Double> lista;

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
		lector = new LectorTxt();
		lista = new ArrayList<Double>();
		lista = lector.leerTxt("instancia1");
		for(int i = 1; i < lista.size(); i++) {
			MapMarker coordenada = new MapMarkerDot(lista.get(i-1),lista.get(i));
			mapa.addMapMarker(coordenada);
		}
			
	}
}
