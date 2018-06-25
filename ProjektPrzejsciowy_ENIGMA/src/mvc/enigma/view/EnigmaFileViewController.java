package mvc.enigma.view;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import mvc.enigma.model.EnigmaModel;

public class EnigmaFileViewController implements Initializable {
	@FXML
	private Button btnEncryptFile;
	@FXML
	private Button btnDecryptFile;
	@FXML
	private Button btnLoadFileIn;
	@FXML
	private Button btnLoadfileOut;
	@FXML
	private Button previewIn;
	@FXML
	private Button prewievOut;
	@FXML
	private ComboBox<Integer> rotorA = new ComboBox<>();
	@FXML
	private ComboBox<Integer> rotorB = new ComboBox<>();
	@FXML
	private ComboBox<Integer> rotorC = new ComboBox<>();
	@FXML
	private TextArea inputPath;
	@FXML
	private TextArea outputPath;
	@FXML
	private ImageView statusOK;

	private EnigmaModel model = new EnigmaModel();

	/**
	 * Metoda inicjujaca kontrolera widoku operacji na plikach. Ustawia wartosci
	 * list rozwijanych rotorow.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i <= model.rotorSize(); i++) {
			list.add(i);
		}
		setRotor(rotorA, list);
		setRotor(rotorB, list);
		setRotor(rotorC, list);
	}

	/**
	 * Metoda ustawiajaca losowa poczatkowa pozycje rotora z dostepnego zakresu
	 * slownika.
	 * 
	 * @param rotor
	 *            Rotor dla ktorego zostanie ustawiona losowa wartosc
	 *            calkowitoliczbowa.
	 * @param list
	 *            Lista dostepnych wartosci do ustawienia dla obiektu rotor.
	 */
	private void setRotor(ComboBox<Integer> rotor, ArrayList<Integer> list) {
		rotor.getItems().clear();
		rotor.getItems().addAll(list);
		rotor.getSelectionModel().select(new Random().nextInt(list.size()));
	}

	/**
	 * Metoda wywolujaca okno wyboru pliku wejsciowego na ktorym zostana wykonane
	 * operacje szyfrowania/deszyfrowania.
	 */
	@FXML
	public void chooseInputFile() {
		statusOK.visibleProperty().set(false);
		inputPath.setText(model.chooseInputFile().toString());
		if (inputPath != null) {
			previewIn.disableProperty().set(false);
		}
	}

	/**
	 * Metoda wywolujaca okno wyboru wyjsciowego pliku tekstowego. W tym pliku
	 * zostana zapisane wyniki operacj szyfrowania/deszyfrowania.
	 */
	@FXML
	public void chooseOutputFile() {
		statusOK.visibleProperty().set(false);
		outputPath.setText(model.chooseOutputFile().toString());
		if (outputPath != null && inputPath != null) {
			btnEncryptFile.disableProperty().set(false);
			btnDecryptFile.disableProperty().set(false);
			prewievOut.disableProperty().set(false);
		}
	}

	/**
	 * Metoda wywolujaca szyfrowanie wczytanego pliku wejsciowego. Dane wyjsciowe
	 * zostaja zapisane w wybranym plkiu wyjsciowym. Metoda uruchamiana sie po
	 * wcisnieciu przycisku Encrypt.
	 */
	@FXML
	public void encryptFile() {
		model.encryptFile(rotorA.getValue(), rotorB.getValue(), rotorC.getValue());
		statusOK.visibleProperty().set(true);
	}

	/**
	 * Metoda wywolujaca odszyfrowanie wczytanego pliku wejsciowego. Dane wyjsciowe
	 * zostaja zapisane w wybranym plkiu wyjsciowym. Metoda uruchamiana sie po
	 * wcisnieciu przycisku Decrypt.
	 */
	@FXML
	public void decryptFile() {
		model.decrypt(rotorA.getValue(), rotorB.getValue(), rotorC.getValue());
		statusOK.visibleProperty().set(true);
	}

	/**
	 * Metoda uruchamiajaca wczytany plik wejsciowy w notatniku systemowym.
	 */
	@FXML
	public void previewInputFile() {
		Runtime runtime = Runtime.getRuntime();
//		try {
//			Process process = runtime.exec("C:\\Windows\\notepad.exe " + inputPath.getText());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String os = System.getProperty("os.name").toLowerCase();
		if(os.startsWith("windows"))
			try {
				runtime.exec("C:\\Windows\\notepad.exe " + inputPath.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if(os.contains("linux")) {
			ProcessBuilder pb = new ProcessBuilder("gedit", inputPath.getText());
			pb.redirectOutput(Redirect.INHERIT);
			Process p = null;;
			try {
				p = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metoda uruchamiajaca wczytany plik wyjsciowy w notatniku systemowym.
	 */
	@FXML
	public void previewOutputFile() {
		Runtime runtime = Runtime.getRuntime();
		String os = System.getProperty("os.name").toLowerCase();
		if(os.startsWith("windows"))
			try {
				runtime.exec("C:\\Windows\\notepad.exe " + outputPath.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if(os.contains("linux")) {
			ProcessBuilder pb = new ProcessBuilder("gedit", outputPath.getText());
			pb.redirectOutput(Redirect.INHERIT);
			Process p = null;;
			try {
				p = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}}
