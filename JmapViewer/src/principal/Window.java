  
package principal;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import Intermediario.Intermediario;
import clustering.Clustering;
import grafo.AGM;

import grafo.Grafo;
import javafx.util.Pair;

public class Window {

	private JFrame frame;
	private static Mapa mapa;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
					window.frame.add(mapa.getMap());
					Coordinate bsAs = new Coordinate (-34.5237,-58.7038);
 					mapa.getMap().setDisplayPosition(bsAs, 10);
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
		mapa = new Mapa();
	}
}
