package Intermediario;

import java.util.List;

import grafo.Grafo;
import javafx.util.Pair;

//Componente que almacena el estado del Originator en un momento determinado.
public class Memento {
	private Pair<Grafo, List<Double>> estado;

	public Memento(Pair<Grafo, List<Double>> estadoAGuardar) {
		estado = estadoAGuardar;
	}

	public Pair<Grafo, List<Double>> getEstado() {
		return estado;
	}

}
