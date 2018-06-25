package mvc.enigma.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Chooser {
	Stage stage = new Stage();
	private FileChooser chooser = new FileChooser();
	private FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("TEXT FILES", "*.txt");
	private File file;
	private Path inputPath;

	/**
	 * Metoda sluzaca do wyboru pliku tekstowego do szyfrowania przez uzytkownika.
	 * Po poprawnym wczytaniu pliku tekstowego nastepuje zapisanie jego sciezki w
	 * polu inputPath a nastepnie zawartosc pliku zapisywana jest w polu text typu
	 * String.
	 *
	 * @return Zwraca zawartosc pliku tekstowego w postaci String.
	 * @throws Exception
	 *             jesli uzytkownik nie wybierz pliu lub plik bedzie uszkodzony.
	 */
	public String selectFile() {
		chooser.getExtensionFilters().add(filter);
		File file = chooser.showOpenDialog(stage);

		if (file != null) {
			this.inputPath = Paths.get(file.getAbsolutePath());
			String currentLine;
			String text = "";
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));// Creates the buffered reader with the
																				// file.
				while ((currentLine = br.readLine()) != null) {
					text += currentLine + "\n";

				}
				StringBuilder sb = new StringBuilder(text);
				sb.deleteCharAt(sb.length() - 1);
				text = sb.toString();
				System.out.println(text);
			} catch (Exception e) {
				String warning = "You did not select a file or the file you selected is corrupt.";
				message(warning, AlertType.WARNING);
			}
			return text; // returns the file as a string.
		}
		return null;
	}

	/**
	 * Meta do wyboru pliku w ktorym zostana zapiusane dane wyjsciowe. Metoda
	 * umozliwia uzytkownikowi wybor pliku wyjsciowego.
	 *
	 * @return Path Zwraca sciezke wybranego pliku tekstowego.
	 */
	public Path chooseFileToSave() {
		Path path = null;
		;
		this.file = chooser.showSaveDialog(stage);
		if (file != null) {
			if (file.exists()) {
				file.delete();
			}
			path = Paths.get(file.getAbsolutePath());
		}

		return path;
	}

	/**
	 * Meta do zapisania danych wyjsciowych w wybranym pliku tekstowym.
	 *
	 * @param fileToSave
	 *            dane wyjsciowe do zapisania w pliku.
	 */
	public void saveFile(String fileToSave) {

		if (file != null) {
			if (file.exists()) {
				file.delete();
			}
			Path path = Paths.get(file.getAbsolutePath());
			byte data[] = fileToSave.getBytes(); // turns the string into bytes.
			try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE, APPEND))) {
				out.write(data, 0, data.length); // writes the file.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Metoda do wyswietlania graficznych komunikatow dla uzytkownika.
	 *
	 * @param message
	 *            String z wiadomoscia do wyswietlenia.
	 * @param alertType
	 *            AlertType okienka do wyswietlenia.
	 */
	public void message(String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Metoda do otrzymania sciezki pliku wejsciowego
	 * 
	 * @return Path sciezka wybranego pliku wejsciowego
	 */
	public Path getInputhPath() {
		return inputPath;
	}
}
