package mvc.enigma.compare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Compare {
	Stage stage = new Stage();
	private FileChooser choose = new FileChooser();
	private FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("TEXT FILES", "*.txt", "*.png");
	private File firstFile, secondFile;
	java.nio.file.Path firstFilePath;
	private java.nio.file.Path secondFilePath;
	private String diff = null;

	/**
	 * Metoda do wyboru pierwszego pliku do porownania.
	 * 
	 * @return Path zwraca sciezke dostepu do wybranego pliku.
	 */
	public java.nio.file.Path setFirstFile() {
		choose.getExtensionFilters().add(filter);
		firstFile = choose.showOpenDialog(stage);
		if (firstFile != null) {
			this.firstFilePath = Paths.get(firstFile.getAbsolutePath());
			return firstFilePath;
		}
		return null;
	}

	/**
	 * Metoda do wyboru drugiego pliku do porownania.
	 * 
	 * @return Path zwraca sciezke dostepu do wybranego pliku.
	 */
	public java.nio.file.Path setSecondFile() {
		choose.getExtensionFilters().add(filter);
		secondFile = choose.showOpenDialog(stage);
		if (secondFile != null) {
			this.secondFilePath = Paths.get(secondFile.getAbsolutePath());
			return secondFilePath;
		}
		return null;
	}

	/**
	 * Metoda do porownania dwoch wczesniej wczytanych plikow tekstowych. Wczytuje linie tesktu z pliku
	 * jedna po drugiej i porownuje je ze soba. W przypadku gdy pliki sa takie same
	 * zwraca wartosc logiczna boolean true. W przeciwnym przypadku zwraca false.
	 * 
	 * @return boolean zwraca wartosc logiczna true jesli pliki sa takie same lub
	 *         false gdy pliki sie roznia.
	 */
	public boolean compareFiles() throws IOException {
		BufferedReader reader1 = null;
		try {
			reader1 = new BufferedReader(new FileReader(firstFilePath.toString()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader2 = null;
		try {
			reader2 = new BufferedReader(new FileReader(secondFilePath.toString()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line1 = reader1.readLine();
		String line2 = reader2.readLine();
		boolean areEqual = true;
		int lineNum = 1;

		while (line1 != null || line2 != null) {
			if (line1 == null || line2 == null) {
				areEqual = false;
				break;
			} else if (!line1.equalsIgnoreCase(line2)) {
				areEqual = false;
				break;
			}

			line1 = reader1.readLine();
			line2 = reader2.readLine();
			lineNum++;
		}

		if (areEqual) {
			System.out.println("Two files have same content.");
		} else {
			System.out.println("Two files have different content. They differ at line " + lineNum);
			diff = "They differ at line " + lineNum+
					"\nFile1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum;
		}

		reader1.close();
		reader2.close();
		return areEqual;
	}
	
	public String getDifferences(){
		return diff;
	}
}
