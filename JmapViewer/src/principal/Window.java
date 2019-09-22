package principal;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;


public class Window {

	private JFrame frame;
	private static JMapViewer map;
	private  txtData.ReaderTxt txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Window window = new Window();
					window.frame.setVisible(true);
					window.frame.add(map);
					//Coordinate bsAs = new Coordinate (59.2238,34.2237);
 					//mapa.setDisplayPosition(bsAs,3);
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map = new JMapViewer(); 
		txt = new txtData.ReaderTxt();
		
		System.out.println(txt.read("instancia1"));
		
	}
	private void setPoint() {	
	}

}
