package mvc.enigma.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mvc.enigma.Main;

public class MainController {
	private Main mainApp;
	
	/**
	 * Metoda do wywolania widoku menu glownego programu.
	 * @throws IOException
	 */
	@FXML
	public void setMain() throws IOException {
		mainApp.loadMainMenu();
	}
	
	/**
	 * Metoda do wyswietlenia okna dialogowego z informacjami o programie.
	 */
	@FXML
	public void about() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Enigma machine - Lukasz Gozdziewski");
		alert.setHeaderText(null);
		alert.setContentText("Enigma machine simulator 1.0\n\n"
				+ "A program for encrypting and decrypting text and text files with a cipher consistent with the enigma encryption machine. "
				+ "\n\n"
				+ "Author: Lukasz Gozdziewski");
		alert.showAndWait();
	}
	
	/**
	 * Metoda zamykajaca okno aplikacji.
	 */
	@FXML
	public void closeApp() {
		Platform.exit();
	}
}
