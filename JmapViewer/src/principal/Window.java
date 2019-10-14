  
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
import Intermediario.Originator;
import clustering.Cluster;
import clustering.Clustering;
import grafo.AGM;
import grafo.Grafo;
import javafx.util.Pair;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
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
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {
	private JFrame frame;
	private static Mapa mapa;
	private Grafo grafo;
	private AGM agm;
	private JTextField pregunta;
	private JTextField numeroClusters;
	private Clustering cluster;
	private JTextField txtInstaciasDeseadas;
	private List<Coordinate>coordenadasClickeadas;
	private List<String>instancias;
	private Intermediario intermediario;
	private CareTaker care;
	private Originator originator;
	
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
					ICoordinate obtenerPosicion = mapa.getMap().getPosition(new Point(e.getPoint().x, e.getPoint().y));
					Coordinate coordenadaClickeada = new Coordinate(obtenerPosicion.getLat(), obtenerPosicion.getLon());
					mapa.agregarMacador(coordenadaClickeada);
					coordenadasClickeadas.add(coordenadaClickeada);
				}
		});
	}
	private void dibujarMapa() {
		mapa = new Mapa();
	}
	private void dibujarInstancias() throws IOException {
		agregarInstancias();
		actualizarEstado();
		actualizarMapa();
	}
	private void actualizarMapa() {
		mapa.borrarGrafo();
		mapa.agregarMarcas(intermediario.getCoordenadas());
		dibujarAristas();
		coordenadasClickeadas.clear();
	}

	private void actualizarEstado() {
		originator.set(new Pair<Grafo, List<Double>>(grafo, intermediario.getDoubles()));
		care.addMemento(originator.guardarAMemento());
	}

	private void agregarInstancias() throws IOException {
		for (String instancia : instancias) {
			intermediario.castear(instancia);
		}
		intermediario.agregar(coordenadasClickeadas);
		grafo = new Grafo(intermediario.getCoordenadas());
		agm = new AGM();
		grafo = agm.calcularKruskal(grafo);
	}
	
	private void dibujarAristas() {
		List<Coordinate>coordenadas=intermediario.getCoordenadas();
		List<Pair<Integer,Integer>>indices=grafo.getIndices();
		for(int i=0;i<indices.size();i++) {
			mapa.dibujarLinea(coordenadas,indices.get(i).getKey(),indices.get(i).getValue());
		}
	}

	private void inicializarValoresPantalla() {
		coordenadasClickeadas=new ArrayList<Coordinate>();
		instancias=new ArrayList<String>();
		intermediario=new Intermediario();
		originator=new Originator(null);
		care=new CareTaker();
		frame = new JFrame("Mapa");
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
					cluster = new Clustering(grafo,Integer.valueOf(numeroClusters.getText()));
					if(cluster.aristasMasPesadas()){
					grafo = new Grafo(cluster.getGrafo());
					mapa.borrarGrafo();
					actualizarEstado();
					mapa.agregarMarcas(intermediario.getCoordenadas(), cluster.getClusters());
					dibujarAristas();
					}
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		});
		JButton clusterAzar = new JButton("Azar");
		clusterAzar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					cluster = new Clustering(grafo,Integer.valueOf(numeroClusters.getText()));
					if(cluster.azar()){
					grafo = new Grafo(cluster.getGrafo());
					mapa.borrarGrafo();
					actualizarEstado();
					mapa.agregarMarcas(intermediario.getCoordenadas(), cluster.getClusters());
					dibujarAristas();
				}
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});
		clusterAzar.setBounds(25, 281, 134, 23);
		panel.add(clusterAzar);
		
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
		aceptarInstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		aceptarInstancia.setBounds(25, 198, 134, 23);
		panel.add(aceptarInstancia);
		
		JButton eliminarNodo = new JButton("Eliminar Nodo");
		eliminarNodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (coordenadasClickeadas.size() > 0) {
					mapa.eliminarMarcador(coordenadasClickeadas.get(coordenadasClickeadas.size() - 1));
				}
			}
		});
		eliminarNodo.setBounds(25, 393, 134, 23);
		panel.add(eliminarNodo);

		
		JButton deshacer = new JButton("");
		deshacer.setIcon(new ImageIcon(Window.class.getResource("/Imagenes/deshacer.png")));
		deshacer.setBounds(0, 0, 97, 29);
		panel.add(deshacer);

		JButton rehacer = new JButton("");
		rehacer.setIcon(new ImageIcon(Window.class.getResource("/Imagenes/rehacer.png")));
		rehacer.setBounds(94, 0, 90, 29);

		panel.add(rehacer);
		rehacer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (care.estadoActual() + 1 < care.tamano()) {
						Pair<Grafo, List<Double>> estado = care.getMemento(care.estadoActual() + 1).getEstado();
						grafo = new Grafo(estado.getKey());
						intermediario = new Intermediario(estado.getValue());

						actualizarMapa();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		deshacer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (care.estadoActual() > 0) {
						Pair<Grafo, List<Double>> estado = care.getMemento(care.estadoActual() - 1).getEstado();
						grafo = new Grafo(estado.getKey());
						intermediario = new Intermediario(estado.getValue());

						actualizarMapa();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
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
					estadisticas += "Peso cluster N°" + i +" color "+ cluster.getClusters().get(i-1).getNombreColor()+ ": ";
					estadisticas += clus.getPeso();
					estadisticas += "\n";
				}
				JOptionPane.showMessageDialog(null, estadisticas);
			}
		});
		btnEstadisticas.setBounds(25, 427, 134, 23);
		panel.add(btnEstadisticas);
		
		JButton promedio = new JButton("Promedio");
		promedio.setBounds(25, 334, 134, 23);
		panel.add(promedio);
		promedio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					cluster = new Clustering(grafo,Integer.valueOf(numeroClusters.getText()));
					if(cluster.promedio()){
					grafo = new Grafo(cluster.getGrafo());
					mapa.borrarGrafo();
					actualizarEstado();
					mapa.agregarMarcas(intermediario.getCoordenadas(), cluster.getClusters());
					dibujarAristas();
				}
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});
		
		aceptarInstancia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
					intermediario.reiniciarValores();
					dibujarInstancias();
					instancias.clear();
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});	
		
		
	}
}
