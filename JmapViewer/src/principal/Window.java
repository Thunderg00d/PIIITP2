  
package principal;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import Intermediario.CareTaker;
import Intermediario.Intermediario;
import clustering.Cluster;
import clustering.Clustering;
import grafo.AGM;
import grafo.Grafo;
import javafx.util.Pair;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	private CareTaker care;
	private Estado estado;
	
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


	public Window() throws IOException {
		initialize();
	}


	private void initialize() throws IOException {
		dibujarMapa();
		inicializarValoresPantalla();
		mapa.getMap().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				ICoordinate obtenerPosicion = mapa.getMap().getPosition(new Point(e.getPoint().x,e.getPoint().y));
				Coordinate coordenadaClickeada = new Coordinate(obtenerPosicion.getLat(),obtenerPosicion.getLon());
				estado.SetEstado(coordenadaClickeada);
				mapa.agregarMacador(coordenadaClickeada);
			
				try {
					care.setMemoria(estado.getCoordenadas());
					
				
	
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		});
	}
	private void dibujarMapa() {
		mapa = new Mapa();
	}
	private void dibujarInstancias(List<String> instancias) throws IOException {
		mapa.borrarGrafo();
		care= new CareTaker();
		intermediario.setCoordenadas(instancias,care.getNumerosDMemoria(0));
		List<Coordinate>coordenadas=intermediario.getCoordenadas();
		coordenadas.addAll(estado.getCoordenadas()); 
		grafo=new Grafo(coordenadas);
		agm=new AGM();
		estado = new Estado();
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
		intermediario=new Intermediario();
		estado=new Estado();
		care=new CareTaker();
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
		
		
		JButton clusterMayorArista = new JButton("Mayores");
		clusterMayorArista.setBounds(25, 307, 134, 23);
		panel.add(clusterMayorArista);
		clusterMayorArista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					cluster=new Clustering(grafo);
					cluster.clusteringAristasMasPesadas(Integer.valueOf(numeroClusters.getText()));
					mapa.borrarGrafo();
					mapa.agregarMarcas(intermediario.getCoordenadas(),cluster.getClusters());
					dibujarAristas();
					}
				catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});
		JButton clusterPromedio = new JButton("Promedio");
		clusterPromedio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					cluster=new Clustering(grafo);
					//cluster.clusteringVecinosMasPesados(Integer.valueOf(numeroClusters.getText()));
					mapa.borrarGrafo();
					mapa.agregarMarcas(intermediario.getCoordenadas());
					dibujarAristas();
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});
		clusterPromedio.setBounds(25, 281, 134, 23);
		panel.add(clusterPromedio);
		
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
		
		JButton eliminarNodo = new JButton("Eliminar Nodo");
		eliminarNodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(estado.getCoordenadas().size()>0 && grafo==null) {
					mapa.eliminarMarcador(estado.getCoordenadas().get(estado.getCoordenadas().size()-1));
					estado.eliminarUltimaCoordenada();
					try {
						care.setMemoria(estado.getCoordenadas());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		eliminarNodo.setBounds(25, 357, 134, 23);
		panel.add(eliminarNodo);
		
		JButton btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String estadisticas = "";
				estadisticas += "Cant. clusters: " +
								String.valueOf(cluster.cantClusters()) + "\n";
				int i = 0;
				for(Cluster clus : cluster.getClusters()) {
					i++;
					estadisticas += "Peso cluster N°" + i +" color"+ cluster.getClusters().get(i-1).getColor().toString().substring(14)+ ": ";
					estadisticas += clus.getPeso();
					estadisticas += "\n";
				}
				JOptionPane.showMessageDialog(null, estadisticas);
			}
		});
		btnEstadisticas.setBounds(25, 404, 134, 23);
		panel.add(btnEstadisticas);
		
		
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
