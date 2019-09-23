package txtData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LectorTxt {
	public HashMap<Double, Double> leerTxt(String archivo) throws IOException {
		File fr = new File(getPath() + "\\Instancias\\" + archivo + ".txt");
		BufferedReader br = new BufferedReader(new FileReader(fr));
		String numActual = "";
		Double latitud = 0.0;
		Double longitud = 0.0;
		String st;
		HashMap<Double, Double> lista = new HashMap<Double, Double>();
		while ((st = br.readLine()) != null) {
			for (int j = 0; j < st.length(); j++) {
				if (st.charAt(j) == '-' || st.charAt(j) == ' ') {
					if (numActual.length() > 0) {
						latitud = Double.valueOf(numActual) * -1;
						numActual = "";
					}
				} else {
					numActual += st.charAt(j);
					if (st.length() == j + 1) {
						longitud = Double.valueOf(numActual) * -1;
						lista.put(latitud, longitud);
					}
				}
			}
		}
		br.close();
		return lista;
	}

	public static String getPath() {
		File miDir = new File(".");
		try {
			return (miDir.getCanonicalPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
