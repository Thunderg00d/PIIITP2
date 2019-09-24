package principal;

import java.awt.EventQueue;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import Intermediario.Intermediario;

public class Window {

	private JFrame frame;
	private static JMapViewer mapa;
	private Intermediario inter;

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
		inter = new Intermediario();
		for(Point2D.Double p : inter.getCoordenadas()) {
			mapa.addMapMarker(new MapMarkerDot(p.getX(),p.getY()));
		}
		/*DefaultMapController mapController = new DefaultMapController(mapa);
		mapController.setMovementMouseButton(MouseEvent.CLICK);*/
		/*for(Map.Entry<Double, Double> coordenadas : lista.entrySet()) {
			mapa.addMapMarker(new MapMarkerDot(coordenadas.getKey(),coordenadas.getValue()));
		}*/
			
	}
}
