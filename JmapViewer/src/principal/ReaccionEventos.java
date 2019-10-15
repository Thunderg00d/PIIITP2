package principal;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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

public class ReaccionEventos {
	private Mapa mapa;
	private Grafo grafo;
	private CareTaker careTaker;
	private Originator originator;
	private Intermediario intermediario;
	private List<Coordinate> coordenadasClickeadas;
	private Clustering cluster;
	private String[] opciones = { "Numero ingresado no valido", "No se ha seleccionado ninguna instancia" };

	public ReaccionEventos(Mapa m) throws FileNotFoundException {
		mapa = m;
		careTaker = new CareTaker();
		careTaker = careTaker.set();
		intermediario = new Intermediario();
		originator = new Originator(null);
		coordenadasClickeadas = new ArrayList<Coordinate>();
	}

	public void agregarCoordenada(MouseEvent e) {
		ICoordinate obtenerPosicion = mapa.getMap().getPosition(new Point(e.getPoint().x, e.getPoint().y));
		Coordinate coordenadaClickeada = new Coordinate(obtenerPosicion.getLat(), obtenerPosicion.getLon());
		mapa.agregarMacador(coordenadaClickeada);
		coordenadasClickeadas.add(coordenadaClickeada);
	}

	private void dibujarInstancias(List<String> instancias) throws IOException {
		agregarInstancias(instancias);
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
		careTaker.addMemento(originator.guardarAMemento());
	}

	private void agregarInstancias(List<String> instancias) throws IOException {
		for (String instancia : instancias) {
			intermediario.castear(instancia);
		}
		intermediario.agregar(coordenadasClickeadas);
		grafo = new Grafo(intermediario.getCoordenadas());
		AGM agm = new AGM();
		grafo = agm.calcularKruskal(grafo);
	}

	private void dibujarAristas() {
		List<Coordinate> coordenadas = intermediario.getCoordenadas();
		List<Pair<Integer, Integer>> indices = grafo.getIndices();
		for (int i = 0; i < indices.size(); i++) {
			mapa.dibujarLinea(coordenadas, indices.get(i).getKey(), indices.get(i).getValue());
		}
	}

	public void clusterMayorArista(String cantidadClusters) {
		if (esNumero(cantidadClusters)) {
			try {
				cluster = new Clustering(grafo, Integer.valueOf(cantidadClusters));
				if (cluster.aristasMasPesadas()) {
					grafo = new Grafo(cluster.getGrafo());
					mapa.borrarGrafo();
					actualizarEstado();
					mapa.agregarMarcas(intermediario.getCoordenadas(), cluster.getClusters());
					dibujarAristas();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, opciones[0]);
		}
	}

	public void clusterAzar(String cantidadClusters) {
		if (esNumero(cantidadClusters)) {
			try {
				cluster = new Clustering(grafo, Integer.valueOf(cantidadClusters));
				if (cluster.azar()) {
					grafo = new Grafo(cluster.getGrafo());
					mapa.borrarGrafo();
					actualizarEstado();
					mapa.agregarMarcas(intermediario.getCoordenadas(), cluster.getClusters());
					dibujarAristas();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, opciones[0]);
		}
	}

	public void clusterPromedio(String cantidadClusters) {
		if (esNumero(cantidadClusters)) {
			try {
				cluster = new Clustering(grafo, Integer.valueOf(cantidadClusters));
				if (cluster.promedio()) {
					grafo = new Grafo(cluster.getGrafo());
					mapa.borrarGrafo();
					actualizarEstado();
					mapa.agregarMarcas(intermediario.getCoordenadas(), cluster.getClusters());
					dibujarAristas();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, opciones[0]);
		}
	}

	public void eliminarNodo() {
		if (coordenadasClickeadas.size() > 0) {
			mapa.eliminarMarcador(coordenadasClickeadas.get(coordenadasClickeadas.size() - 1));
			coordenadasClickeadas.remove(coordenadasClickeadas.size() - 1);
		}
	}

	public void aceptarInstancia(List<String> instancias) {
		if (!instancias.isEmpty()) {
			try {
				intermediario.reiniciarValores();
				dibujarInstancias(instancias);
				instancias.clear();
				careTaker.guardar();
				careTaker = careTaker.set();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (!coordenadasClickeadas.isEmpty()) {
				intermediario.agregar(coordenadasClickeadas);
				grafo = new Grafo(intermediario.getCoordenadas());
				AGM agm = new AGM();
				grafo = agm.calcularKruskal(grafo);
				actualizarEstado();
				actualizarMapa();
			} else {
				JOptionPane.showMessageDialog(null, opciones[1]);
			}
		}
	}

	public void rehacer() {
		if (careTaker.estadoActual() + 1 < careTaker.tamano()) {
			Pair<Grafo, List<Double>> estado = careTaker.getMemento(careTaker.estadoActual() + 1).getEstado();
			grafo = new Grafo(estado.getKey());
			intermediario = new Intermediario(estado.getValue());
			actualizarMapa();
		}
	}

	public void deshacer() {
		if (careTaker.estadoActual() > 0) {
			Pair<Grafo, List<Double>> estado = careTaker.getMemento(careTaker.estadoActual() - 1).getEstado();
			grafo = new Grafo(estado.getKey());
			intermediario = new Intermediario(estado.getValue());
			actualizarMapa();
		}
	}

	public void agregarEstadisticas() {
		if (cluster != null) {
			String estadisticas = "";
			estadisticas += "Cant. clusters: " + String.valueOf(cluster.cantClusters()) + "\n";
			int i = 0;
			for (Cluster clus : cluster.getClusters()) {
				i++;
				estadisticas += "Peso cluster N°" + i + " color " + cluster.getClusters().get(i - 1).getNombreColor()
						+ ": ";
				estadisticas += clus.getPeso();
				estadisticas += "\n";
			}
			JOptionPane.showMessageDialog(null, estadisticas);
		}
	}

	private boolean esNumero(String s) {
		try {
			Integer.valueOf(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void reiniciar() {
		grafo = new Grafo(0);
		intermediario.reiniciarValores();
		mapa.borrarGrafo();
		coordenadasClickeadas.clear();
	}
}
