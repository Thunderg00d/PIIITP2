package principal;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import Intermediario.Intermediario;
import grafo.AGM;
import grafo.Grafo;
import javafx.util.Pair;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class Window {

	private JFrame frame;
	private JPanel panel;
	private static JMapViewer mapa;
	private Intermediario intermediario;
	private ArrayList<Coordinate> coordenadas;
	private Grafo grafo;
	private AGM agm;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
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
		valoresVentana();
		inicializarPanel();
		crearMapa();
		agregarTexto();	
	}

	private void agregarTexto() {
		JTextPane opcion = new JTextPane();
		opcion.setFont(new Font("Sitka Text", Font.BOLD, 12));
		opcion.setBackground(Color.LIGHT_GRAY);
		opcion.setEditable(false);
		opcion.setText("Cantidad de clusters: ");
		opcion.setBounds(452, 11, 137, 20);
		panel.add(opcion);
		textField = new JTextField();
		textField.setBounds(503, 31, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
	}
	
	private void crearMapa() throws IOException {
		intermediario=new Intermediario();
		grafo = new Grafo(intermediario.getCoordenadas());
		agm = new AGM();
		grafo = agm.calcularKruskal(grafo);
		
		mapa = new JMapViewer();
		
		Coordinate bsAs = new Coordinate (-34.5237,-58.7038);
		mapa.setDisplayPosition(bsAs, 10);
		intermediario = new Intermediario();
		intermediario.setCoordenadas();
		
		mapa.setBounds(0, 0, 450, 500);
		coordenadas = intermediario.getCoordenadas();
		for(Coordinate vertice : coordenadas) {
			mapa.addMapMarker(new MapMarkerDot(vertice.getLat(),vertice.getLon()));
		}
		panel.add(mapa);
		frame.getContentPane().add(panel);
		List<Pair<Integer,Integer>>indices=grafo.getIndices();
		
		for(int i=0;i<indices.size();i++) {
			dibujarLinea(indices.get(i).getKey(),indices.get(i).getValue());
		}
	}
	
	private void inicializarPanel() {
		panel=new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(null);
		panel.setBounds(0,0,500,500);
		panel.setLayout(null);
	}
	
	private void valoresVentana() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void dibujarLinea(int i, int j) {
		mapa.addMapPolygon(
				new MapPolygonImpl( 
				new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon()),
						new Coordinate(coordenadas.get(j).getLat(),coordenadas.get(j).getLon()),
								new Coordinate(coordenadas.get(i).getLat(),coordenadas.get(i).getLon())));
	}
}
