package Intermediario;

import java.util.ArrayList;
import java.util.List;

/*
 * Componente que registra los cambios del Originator.
 *  Este componente nos permite viajar entre los 
 *  distintos estados del Originator.
 */
public class CareTaker {
	private List<Memento> estadosGuardados = new ArrayList<Memento>();
	private int estadoActual;
    public void addMemento(Memento m) { 
		   estadosGuardados.add(m); 
		   estadoActual=estadosGuardados.size()-1;
    }
	
    public Memento getMemento(int i) { 
    		if(i>tamano()-1 || i<0)
    			throw new IllegalArgumentException("Indice ingresado no valido");
    		estadoActual=i;
		   return estadosGuardados.get(i); 
	}
    public int estadoActual() {
    	return estadoActual;
    }

	public int tamano() {
		return estadosGuardados.size();
	}
}
