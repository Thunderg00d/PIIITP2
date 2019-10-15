package Intermediario;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import memoria.HistorialSoluciones;

/*Componente que registra los cambios del Originator.
 *  Este componente nos permite viajar entre los 
 *  distintos estados del Originator. */

public class CareTaker {

	private HistorialSoluciones repo;
	private List<Memento> estadosGuardados = new ArrayList<Memento>();
	private int estadoActual;

	public CareTaker() throws FileNotFoundException {
		repo = new HistorialSoluciones();
	}

	public void addMemento(Memento m) {
		estadosGuardados.add(m);
		estadoActual = estadosGuardados.size() - 1;
	}

	public Memento getMemento(int i) {
		if (i > tamano() - 1 || i < 0)
			throw new IllegalArgumentException("Indice ingresado no valido");
		estadoActual = i;
		return estadosGuardados.get(i);
	}

	public int estadoActual() {
		return estadoActual;
	}

	public int tamano() {
		return estadosGuardados.size();
	}

	public void guardar() {
		repo.agregarAMemoria(this, "Grafos");
	}

	public CareTaker set() throws FileNotFoundException {
		CareTaker temp = this.repo.leerJSON(("Grafos"));
		return temp;
	}

	public void guardarGrafo(String nombre, Memento memento) {
		repo.generarJSON(nombre + ".JSON", memento);
	}

	public Memento getMemento(String archivo) {
		return repo.traerMemento(archivo);
	}

}
