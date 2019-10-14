  
package principal;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

public class Window {
	private JFrame frame;
	private static Mapa mapa;

	private JTextField pregunta;
	private JTextField numeroClusters;
	private JTextField txtInstaciasDeseadas;
	private List<String>instancias;
	
	
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
		
	}
	private void dibujarMapa() {
		mapa = new Mapa();
	}
	

	private void inicializarValoresPantalla() throws FileNotFoundException {
		
		ReaccionEventos reacciones=new ReaccionEventos(mapa);
		mapa.getMap().addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
					reacciones.agregarCoordenada(e);	
				}
		});
		
		instancias=new ArrayList<String>();
		
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
		
		pregunta = new JTextField();
		pregunta.setBorder(new EmptyBorder(0, 0, 0, 0));
		pregunta.setHorizontalAlignment(SwingConstants.CENTER);
		pregunta.setSelectionColor(Color.WHITE);
		pregunta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		pregunta.setEditable(false);
		pregunta.setText("N\u00FAmero de clusters:");
		pregunta.setBounds(25, 232, 134, 29);
		pregunta.setColumns(10);
		panel.add(pregunta);
		
		numeroClusters = new JTextField();
		numeroClusters.setBounds(25, 260, 134, 20);
		numeroClusters.setColumns(10);
		panel.add(numeroClusters);
		
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
					reacciones.aceptarInstancia(instancias);
				
			}
		});	
		
		JButton eliminarNodo = new JButton("Eliminar Nodo");
		eliminarNodo.setBounds(25, 393, 134, 23);
		panel.add(eliminarNodo);
		eliminarNodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reacciones.eliminarNodo();
			}
		});
		

		
		JButton deshacer = new JButton("");
		deshacer.setIcon(new ImageIcon(Window.class.getResource("/Imagenes/deshacer.png")));
		deshacer.setBounds(0, 0, 97, 29);
		panel.add(deshacer);
		deshacer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reacciones.deshacer();
			}
		});
		
		JButton rehacer = new JButton("");
		rehacer.setIcon(new ImageIcon(Window.class.getResource("/Imagenes/rehacer.png")));
		rehacer.setBounds(94, 0, 90, 29);
		panel.add(rehacer);
		rehacer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reacciones.rehacer();
			}
		});

		JButton btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.setBounds(25, 427, 134, 23);
		panel.add(btnEstadisticas);
		btnEstadisticas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			reacciones.agregarEstadisticas();
			}
		});
		
		JButton promedio = new JButton("Promedio");
		promedio.setBounds(25, 334, 134, 23);
		panel.add(promedio);
		promedio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reacciones.clusterPromedio(numeroClusters.getText());
			}
		});
		
		JButton clusterMayorArista = new JButton("Mayores");
		clusterMayorArista.setBounds(25, 307, 134, 23);
		panel.add(clusterMayorArista);
		clusterMayorArista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reacciones.clusterMayorArista(numeroClusters.getText());
			}
		});
		
		JButton clusterAzar = new JButton("Azar");
		clusterAzar.setBounds(25, 281, 134, 23);
		panel.add(clusterAzar);
		clusterAzar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reacciones.clusterAzar(numeroClusters.getText());
			}
		});
		
	}
}
