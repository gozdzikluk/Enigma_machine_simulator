package mvc.enigma.view;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import mvc.enigma.compare.Compare;
import mvc.enigma.model.EnigmaModel;

public class CompareFilesController implements Initializable {
	@FXML
	private Button previewIn;
	@FXML
	private Button previewOut;
	@FXML
	private Button btnLoadFileIn;
	@FXML
	private Button btnLoadfileOut;
	@FXML
	private TextArea inputPath;
	@FXML
	private TextArea outputPath;
	@FXML
	private Button compare;
	@FXML
	private ImageView statusOk;
	@FXML
	private ImageView statusNOK;

	private EnigmaModel model = new EnigmaModel();
	private Compare comparator = new Compare();

	/**
	 * Metoda inicjujaca kontrolera widoku porownywania plikow.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	/**
	 * Metoda do wyboru pierwszego pliku do porownania. Jest uruchamiana po
	 * wcisnieciu przycisku zaladowania pierwszego pliku. Po poprawnym wyborze pliku
	 * tekstowego aktywowany jest przycisk Preview.
	 */
	@FXML
	public void chooseInputFile() {
		statusOk.visibleProperty().set(false);
		statusNOK.visibleProperty().set(false);
		inputPath.setText(comparator.setFirstFile().toString());
		if (inputPath != null) {
			previewIn.disableProperty().set(false);
		}
	}

	/**
	 * Metoda do wyboru drugiego pliku do porownania. Jest uruchamiana po wcisnieciu
	 * przycisku zaladowania drugiego pliku. Po poprawnym wyborze pliku tekstowego
	 * aktywowany jest przycisk Preview.
	 */
	@FXML
	public void chooseOutputFile() {
		statusNOK.visibleProperty().set(false);
		statusOk.visibleProperty().set(false);
		outputPath.setText(comparator.setSecondFile().toString());
		if (outputPath != null) {
			previewOut.disableProperty().set(false);
			compare.disableProperty().set(false);
		}
	}

	/**
	 * Metoda uruchamiajaca wczytany plik wejsciowy w notatniku systemowym.
	 */
	@FXML
	public void previewInputFile() {
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
			}}
	}

	/**
	 * Metoda uruchamiajaca wczytany plik wejsciowy w notatniku systemowym.
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
			}}
	}

	/**
	 * Metoda wywolujaca porownanie dwoch wczytanych plikow tekstowych. Rezultat
	 * porownania pojawia sie w nowym oknie dialogowym.
	 */
	@FXML
	public void compareTwoFiles() {
		try {
			if (comparator.compareFiles()) {
				statusOk.visibleProperty().set(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Result of comparison");
				alert.setHeaderText(null);
				alert.setContentText("Files are the same!");
				alert.showAndWait();
				
				
			} else {
				statusNOK.visibleProperty().set(true);
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Result of comparison");
				alert.setHeaderText(null);
				alert.setContentText("Files are not the same!\n"+comparator.getDifferences());
				alert.showAndWait();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
