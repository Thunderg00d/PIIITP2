package txtData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LectorTxt {
	private  ArrayList<String> content;
	
	public LectorTxt() {
		content = new ArrayList<String>();
	}
	
	public ArrayList<String> LeerArchivo(String archivo) throws IOException{
		try {
			content.clear();
			File fr = new File(getPath() + "\\instancias\\" + archivo + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(fr));
			String st;
			while ((st = br.readLine()) != null) {
				content.add(st);
			}
			System.out.println(content.size()+"CONTENT");
			br.close();
			}
			
			catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Archivo no encontrado : "+archivo);
			}
		
		
		
		return content;
	}
	private static String getPath() {
		File miDir = new File (".");
	     try {
	      return (miDir.getCanonicalPath());
	       }
	     catch(Exception e) {
	       e.printStackTrace();
	       }
		return null;
	     }

}
