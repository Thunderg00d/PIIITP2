package txtData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorTxt {
	public ArrayList<Double> leerTxt(String archivo) throws IOException {
		File fr = new File(getPath() + "\\Instancias\\" + archivo + ".txt");
		BufferedReader br = new BufferedReader(new FileReader(fr));
		String numActual = "";
		String st;
		ArrayList<Double> lista = new ArrayList<Double>();
		while ((st = br.readLine()) != null) {
			for (int j = 0; j < st.length(); j++) {
				if (st.charAt(j) == '-' || st.charAt(j) == ' ') {
					if (numActual.length() > 0) {
						lista.add(Double.valueOf(numActual) * -1);
						numActual = "";
					}
				} else {
					numActual += st.charAt(j);
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
