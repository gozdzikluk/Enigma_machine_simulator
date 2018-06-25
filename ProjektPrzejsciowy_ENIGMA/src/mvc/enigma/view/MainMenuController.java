package mvc.enigma.view;

import java.io.IOException;

import javafx.fxml.FXML;
import mvc.enigma.Main;

public class MainMenuController {
	private Main main;

	/**
	 * Metoda wywolujaca wyswietlenie okna do pracy z tekstem wpisywanym z
	 * klawiatury.
	 * 
	 * @throws IOException
	 */
	@FXML
	public void setText() throws IOException {
		main.loadTextView();
	}

	/**
	 * Metoda wywolujaca wyswietlenie okna do pracy z plikami tekstowymi.
	 * 
	 * @throws IOException
	 */
	@FXML
	public void setFile() throws IOException {
		main.loadFileView();
	}

	/**
	 * Metoda wywolujaca wyswietlenie okna do porownania dwoch plikow tekstowych.
	 * 
	 * @throws IOException
	 */
	@FXML
	public void setComparator() throws IOException {
		main.loadCompareFiles();
	}
}
