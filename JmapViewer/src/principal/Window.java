  
package principal;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import Intermediario.Intermediario;
import clustering.Clustering;
import grafo.AGM;
import grafo.Grafo;
import javafx.util.Pair;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Window {
	private JFrame frame;
	private static Mapa mapa;
	private Grafo grafo;
	private AGM agm;
	private JTextField pregunta;
	private JTextField numeroClusters;
	private Clustering cluster;
	private JTextField txtInstaciasDeseadas;
	Intermediario intermediario;


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
		dibujarMapa();
		inicializarValoresPantalla();
		
	}
	private void dibujarMapa() {
		mapa = new Mapa();
	}
	private void dibujarInstancias(ArrayList<String> instancias) throws IOException {
		mapa.borrarGrafo();
		intermediario=new Intermediario();
		intermediario.setCoordenadas(instancias);
		List<Coordinate>coordenadas=intermediario.getCoordenadas();
		grafo=new Grafo(coordenadas);
		agm=new AGM();
		grafo = agm.calcularKruskal(grafo);
		mapa.agregarMarcas(coordenadas);
		
		dibujarAristas();
	}

	private void dibujarAristas() {
		List<Coordinate>coordenadas=intermediario.getCoordenadas();
		List<Pair<Integer,Integer>>indices=grafo.getIndices();
		for(int i=0;i<indices.size();i++) {
			mapa.dibujarLinea(coordenadas,indices.get(i).getKey(),indices.get(i).getValue());
		}
	}

	private void inicializarValoresPantalla() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mapa.getMap());
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(500, 0, 184, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton aceptarCantidadClusters = new JButton("Aceptar");
		aceptarCantidadClusters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		aceptarCantidadClusters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					cluster = new Clustering();
					cluster.hacer_Clustering(grafo, Integer.valueOf(numeroClusters.getText()));
					mapa.borrarGrafo();
					mapa.agregarMarcas(intermediario.getCoordenadas());
					dibujarAristas();
				}
				catch(Exception e) {
				}
			}
		});
		aceptarCantidadClusters.setBounds(25, 291, 134, 23);
		panel.add(aceptarCantidadClusters);
		
		pregunta = new JTextField();
		pregunta.setBorder(new EmptyBorder(0, 0, 0, 0));
		pregunta.setHorizontalAlignment(SwingConstants.CENTER);
		pregunta.setSelectionColor(Color.WHITE);
		pregunta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		pregunta.setEditable(false);
		pregunta.setText("N\u00FAmero de clusters:");
		pregunta.setBounds(25, 232, 134, 29);
		panel.add(pregunta);
		pregunta.setColumns(10);
		
		numeroClusters = new JTextField();
		numeroClusters.setBounds(25, 260, 134, 20);
		panel.add(numeroClusters);
		numeroClusters.setColumns(10);
		
		txtInstaciasDeseadas = new JTextField();
		txtInstaciasDeseadas.setText("Instacias deseadas:");
		txtInstaciasDeseadas.setSelectionColor(Color.WHITE);
		txtInstaciasDeseadas.setHorizontalAlignment(SwingConstants.CENTER);
		txtInstaciasDeseadas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		txtInstaciasDeseadas.setEditable(false);
		txtInstaciasDeseadas.setColumns(10);
		txtInstaciasDeseadas.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtInstaciasDeseadas.setBounds(25, 31, 134, 29);
		panel.add(txtInstaciasDeseadas);
		
		
		JCheckBox chckbxInstancia1 = new JCheckBox("Instancia 1");
		chckbxInstancia1.setBounds(35, 67, 97, 23);
		panel.add(chckbxInstancia1);
		
		JCheckBox chckbxInstancia2 = new JCheckBox("Instancia 2");
		chckbxInstancia2.setBounds(35, 93, 97, 23);
		panel.add(chckbxInstancia2);
		
		JCheckBox chckbxInstancia3 = new JCheckBox("Instancia 3");
		chckbxInstancia3.setBounds(35, 119, 97, 23);
		panel.add(chckbxInstancia3);
		
		JCheckBox chckbxInstancia4 = new JCheckBox("Instancia 4");
		chckbxInstancia4.setBounds(35, 145, 97, 23);
		panel.add(chckbxInstancia4);
		
		JCheckBox chckbxInstancia5 = new JCheckBox("Instancia 5");
		chckbxInstancia5.setBounds(35, 171, 97, 23);
		panel.add(chckbxInstancia5);
		
		JButton aceptarInstancia = new JButton("Aceptar");

		aceptarInstancia.setBounds(25, 198, 134, 23);
		panel.add(aceptarInstancia);
		aceptarInstancia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<String> instancias = new ArrayList<String>(); 
				if(chckbxInstancia1.isSelected())
					instancias.add("Instancia1");
				if(chckbxInstancia2.isSelected())
					instancias.add("Instancia2");
				if(chckbxInstancia3.isSelected())
					instancias.add("Instancia3");
				if(chckbxInstancia4.isSelected()) 
					instancias.add("Instancia4");
				if(chckbxInstancia5.isSelected())
					instancias.add("Instancia5");
				try {
					dibujarInstancias(instancias);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
		
		
		
	}
}
