  
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
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

public class Window {
	private JFrame frame;
	private  Mapa mapa;

	private JTextField pregunta;
	private JTextField numeroClusters;
	private JTextField instanciasDeseadas;
	private List<String>instancias;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (IOException e) {
					//e.printStackTrace();
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
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mapa.getMap());
		frame.getContentPane().setLayout(null);
		ImageIcon ImageIcon = new ImageIcon(getClass().getResource("/Imagenes/mapa.png"));
        Image Image = ImageIcon.getImage();
        frame.setIconImage(Image);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBounds(500, 0, 184, 461);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		pregunta = new JTextField();
		pregunta.setBorder(new EmptyBorder(0, 0, 0, 0));
		pregunta.setHorizontalAlignment(SwingConstants.CENTER);
		pregunta.setSelectionColor(Color.WHITE);
		pregunta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		pregunta.setEditable(false);
		pregunta.setText("N\u00FAmero de clusters:");
		pregunta.setBounds(25, 232, 134, 29);
		pregunta.setColumns(10);
		pregunta.setBackground(Color.LIGHT_GRAY);
		panel.add(pregunta);
		
		numeroClusters = new JTextField();
		numeroClusters.setBounds(25, 260, 134, 20);
		numeroClusters.setColumns(10);
		panel.add(numeroClusters);
		
		instanciasDeseadas = new JTextField();
		instanciasDeseadas.setText("Instancias deseadas:");
		instanciasDeseadas.setBackground(Color.LIGHT_GRAY);
		instanciasDeseadas.setSelectionColor(Color.WHITE);
		instanciasDeseadas.setHorizontalAlignment(SwingConstants.CENTER);
		instanciasDeseadas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		instanciasDeseadas.setEditable(false);
		instanciasDeseadas.setColumns(10);
		instanciasDeseadas.setBorder(new EmptyBorder(0, 0, 0, 0));
		instanciasDeseadas.setBounds(25, 31, 134, 29);
		panel.add(instanciasDeseadas);

		JCheckBox instancia1 = new JCheckBox("Instancia 1");
		instancia1.setBackground(Color.LIGHT_GRAY);
		instancia1.setBounds(35, 67, 97, 23);
		panel.add(instancia1);
		
		JCheckBox instancia2 = new JCheckBox("Instancia 2");
		instancia2.setBackground(Color.LIGHT_GRAY);
		instancia2.setBounds(35, 93, 97, 23);
		panel.add(instancia2);
		
		JCheckBox instancia3 = new JCheckBox("Instancia 3");
		instancia3.setBackground(Color.LIGHT_GRAY);
		instancia3.setBounds(35, 119, 97, 23);
		panel.add(instancia3);
		
		JCheckBox instancia4 = new JCheckBox("Instancia 4");
		instancia4.setBackground(Color.LIGHT_GRAY);
		instancia4.setBounds(35, 145, 97, 23);
		panel.add(instancia4);
		
		JCheckBox instancia5 = new JCheckBox("Instancia 5");
		instancia5.setBackground(Color.LIGHT_GRAY);
		instancia5.setBounds(35, 171, 97, 23);
		panel.add(instancia5);
		
		JButton aceptarInstancia = new JButton("Aceptar");
		aceptarInstancia.setBounds(25, 198, 134, 23);
		panel.add(aceptarInstancia);
		aceptarInstancia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(instancia1.isSelected())
					instancias.add("Instancia1");
				if(instancia2.isSelected())
					instancias.add("Instancia2");
				if(instancia3.isSelected())
					instancias.add("Instancia3");
				if(instancia4.isSelected()) 
					instancias.add("Instancia4");
				if(instancia5.isSelected())
					instancias.add("Instancia5");
					reacciones.aceptarInstancia(instancias);
			}
		});	
		
		JButton eliminarNodo = new JButton("Eliminar Nodo");
		eliminarNodo.setBounds(25, 368, 134, 23);
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
		
		JButton nuevoGrafo = new JButton("Nuevo Grafo");
		nuevoGrafo.setBounds(25, 391, 134, 23);
		panel.add(nuevoGrafo);
		nuevoGrafo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reacciones.reiniciar();
			}
		});
		
		clusterAzar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reacciones.clusterAzar(numeroClusters.getText());
			}
		});
		
	}
}
