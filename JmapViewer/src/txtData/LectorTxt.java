package txtData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	public ArrayList<String> read(String archivo) throws IOException{
		
		Scanner scanner;
		File file = new File(getPath()+"\\"+"instancias"+"\\"+archivo+".txt");
		try {
			scanner = new Scanner(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				FileWriter flwriter = new FileWriter(getPath()+"\\"+"instancias"+"\\"+archivo+".txt", true);
				BufferedWriter bfwriter = new BufferedWriter(flwriter);
					bfwriter.write(line);
					content.add(line);
					bfwriter.close();
				}
			scanner.close();
			
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
