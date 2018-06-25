package mvc.enigma;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mvc.enigma.Main;

/**
 * Enigma Machne simulator v1.0
 * 
 * Program do symulacji maszyny szyfrujacej Enigma. Umozliwia uzytkownikowi
 * wybor szyfrowanych danych: plikow tekstowych lub wpisywania tesktu z
 * klawiatury. Nastepnie szyfruje lub deszyfruje wprowadzone dane. Kolejne
 * podstawienia dokonywane sa przez trzy obrotowe bebny szyfrujace. Ich wstepne
 * ustawienie generowane jest w sposob losowy, jednak uzytkownik ma mozliwosc
 * dokonania ustawienia recznie. Zaszyfrowane dane w zaleznosci od rodzaju
 * danych wejsciowych zapisywane sa w oknie programu lub pliku tekstowym.
 * Aplikacja posida funkcjonalnosc porownania dwoch plikow tekstowych w celu
 * weryfikacji poprawnosci wykonywanych operacji szyfrujacych i deszyfrujacych.
 * 
 * @author Lukasz Gozdziewski
 * @version %I%, %G%
 * @since 1.0
 */
public class Main extends Application {

	private Scene scene;
	private static BorderPane main;
	private Stage primaryStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		loadMainView();
		loadMainMenu();
	}

	/**
	 * Metoda ladujaca okno glowne programu.
	 * 
	 * @throws IOException
	 */
	public void loadMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		main = loader.load();

		Scene scene = new Scene(main);
		this.scene = scene;
		primaryStage.setTitle("Enigma Machine - Lukasz Gozdziewski");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Metoda ladujaca menu glowne programu i ustawiajaca zawatrosc w oknie glownym
	 * aplikacji.
	 * 
	 * @throws IOException
	 */
	public static void loadMainMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
		AnchorPane mainMenu = loader.load();
		main.setCenter(mainMenu);
	}

	/**
	 * Metoda ladujaca widok do operacji na tekscie wpisywanym z klawiatury.
	 * 
	 * @throws IOException
	 */
	public static void loadTextView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/EnigmaTextView.fxml"));
		AnchorPane mainMenu = loader.load();
		main.setCenter(mainMenu);
	}

	/**
	 * Metoda ladujaca widok do operacji na plikach tekstowych.
	 * 
	 * @throws IOException
	 */
	public static void loadFileView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/EnigmaFileView.fxml"));
		AnchorPane mainMenu = loader.load();
		main.setCenter(mainMenu);
	}

	/**
	 * Metoda ladujaca widok do porownania dwoch plikow tekstowych.
	 * 
	 * @throws IOException
	 */
	public static void loadCompareFiles() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/CompareFilesView.fxml"));
		AnchorPane mainMenu = loader.load();
		main.setCenter(mainMenu);
	}

}
