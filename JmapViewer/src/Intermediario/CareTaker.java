package Intermediario;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import memoria.HistorialOperaciones;

public class CareTaker implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Double> estAGuardar;
	private HistorialOperaciones repo;
	
	
	public CareTaker() {
		estAGuardar= new LinkedList<Double>();
		repo=new HistorialOperaciones();
	}
	
	private void guardarEstados() throws IOException {
		
		repo.agregarAMemoria(this, "Calcu.json");
	}
	
	public void setMemoria(List<Coordinate> list) throws IOException {
		for(Coordinate c : list) {
			estAGuardar.add(c.getLat());
			estAGuardar.add(c.getLon());
		}
		
		guardarEstados();
		estAGuardar.clear();
	}
	
	public ArrayList<Coordinate> getNumerosDMemoria(int estado) {
		ArrayList<Double> temp = new ArrayList<Double>();
		ArrayList<Coordinate> tempC = new ArrayList<Coordinate>();
		temp.addAll(repo.getNumsDMemo("Calcu.json"));
		for (int i=0; i<temp.size();i+=2) {
			tempC.add(new Coordinate(temp.get(i),temp.get(i+1)));
		}
													
		return tempC;
	}
	
	public ArrayList<String> getEstadoOperandos() {
		return repo.traerOpsDMemo();
	}
	
	
	public void setMemoria(ArrayList<Double> arrayList) throws IOException {
		estAGuardar.addAll(arrayList);
		
		guardarEstados();
	}
	
	public void setMemoria(String estadoNums) throws IOException {
		//estAGuardar.add(estadoNums);
		guardarEstados();	
	}
	
}