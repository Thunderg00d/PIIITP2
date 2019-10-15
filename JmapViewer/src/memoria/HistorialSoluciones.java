package memoria;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.openstreetmap.gui.jmapviewer.Coordinate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import Intermediario.CareTaker;
import Intermediario.Memento;

public class HistorialSoluciones implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private List<Double> doubles;

	public HistorialSoluciones() {
		doubles = new ArrayList<Double>();
	}

	public void generarJSON(String archivo, Object o) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(o);

		try {
			FileWriter writer = new FileWriter(archivo);
			writer.write(json);
			writer.close();

		} catch (Exception e) {
			return;
		}
	}


	public void agregarAMemoria(Object o, String arch) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(o);
			FileWriter writer = new FileWriter(arch + ".JSON");

			writer.write(jsonString);
			writer.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public CareTaker leerJSON(String archivo) {
		Gson gson = new Gson();
		CareTaker ret = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo + ".JSON"));
			ret = gson.fromJson(br, CareTaker.class);
		} catch (Exception e) {
		}
		return ret;
	}

	public Memento traerMemento(String archivo) {
		Gson gson = new Gson();
		Memento ret = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo + ".JSON"));
			ret = gson.fromJson(br, Memento.class);
		} catch (Exception e) {
		}
		return ret;
	}

	public ArrayList<Double> getNumsDMemo(String arch) {
		ArrayList<Double> numerosARet = new ArrayList<Double>();
		try {
			JsonParser parser = new JsonParser();
			FileReader fr = new FileReader(arch + ".JSON");
			JsonElement datos = parser.parse(fr);
			elementosSegunTipo(datos);

			for (int i = 0; i < doubles.size(); i++) {
				numerosARet.add(doubles.get(i));
			}
			return numerosARet;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("error archivo");
		}
	}

	public void eliminarMemoria(String arch) {
		try {
			FileOutputStream fos = new FileOutputStream(arch);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(null);
			out.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void agregarAMemoriaCoord(ArrayList<Coordinate> o, String arch) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(o);
		FileWriter writer = new FileWriter(arch);
		writer.write(jsonString);
		writer.close();
	}

	public void elementosSegunTipo(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			JsonObject obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();
				elementosSegunTipo(entrada.getValue());
			}
		} else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				elementosSegunTipo(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			JsonPrimitive valor = elemento.getAsJsonPrimitive();
			if (valor.isNumber()) {
				doubles.add(valor.getAsDouble());
				return;
			}
		}
	}

	public List<Double> getNumeros() {
		return doubles;
	}


}
