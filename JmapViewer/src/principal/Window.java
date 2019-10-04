  
package principal;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import Intermediario.Intermediario;
import grafo.AGM;
import grafo.Grafo;
import javafx.util.Pair;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class Window {
	private JFrame frame;
	private static Mapa mapa;
	private Grafo grafo;
	private AGM agm;
	private JTextField pregunta;
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
		dibujarMapaYGrafo(); 
		inicializarValoresPantalla();
		
	}

	private void dibujarMapaYGrafo() throws IOException {
		Intermediario intermediario=new Intermediario();
		intermediario.setCoordenadas();
		List<Coordinate>coordenadas=intermediario.getCoordenadas();
		grafo=new Grafo(coordenadas);
		mapa = new Mapa();
		agm=new AGM();
		grafo = agm.calcularKruskal(grafo);
		mapa.agregarMarcas(coordenadas);
		
		List<Pair<Integer,Integer>>indices=grafo.getIndices();
		for(int i=0;i<indices.size();i++) {
			mapa.dibujarLinea(coordenadas,indices.get(i).getKey(),indices.get(i).getValue());
		}
	}

	private void inicializarValoresPantalla() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mapa.getMap());
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(500, 0, 134, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton aceptar = new JButton("aceptar");
		aceptar.setBounds(0, 46, 134, 23);
		panel.add(aceptar);
		
		JButton cancelar = new JButton("cancelar");
		cancelar.setBounds(0, 101, 134, 23);
		panel.add(cancelar);
		
		pregunta = new JTextField();
		pregunta.setSelectionColor(Color.WHITE);
		pregunta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		pregunta.setEditable(false);
		pregunta.setText("N\u00FAmero de clusters:");
		pregunta.setBounds(0, 0, 118, 29);
		panel.add(pregunta);
		pregunta.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(500, 27, 134, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
