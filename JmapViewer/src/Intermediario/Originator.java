package Intermediario;

import java.io.Serializable;
import java.util.List;

import grafo.Grafo;
import javafx.util.Pair;

// Componente que cambia de estado.
public class Originator implements Serializable {

	private Pair<Grafo, List<Double>> estado;
	private static final long serialVersionUID = 1L;

	public Pair<Grafo, List<Double>> getEstado() {
		return estado;
	}

	public void setEstado(Pair<Grafo, List<Double>> estado) {
		this.estado = estado;
		System.out.println(estado.toString());
	}

	public Originator(Pair<Grafo, List<Double>> estado) {
		super();
		this.estado = estado;
	}

	public void set(Pair<Grafo, List<Double>> state) {
		estado = state;
	}

	public Memento guardarAMemento() {
		return new Memento(estado);
	}

	public void obtenerDeMemento(Memento m) {
		estado = m.getEstado();
	}
}
