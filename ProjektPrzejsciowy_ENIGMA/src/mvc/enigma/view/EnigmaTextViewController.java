package mvc.enigma.view;

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

public class EnigmaTextViewController implements Initializable {
	@FXML
	private Button encryptText;
	@FXML
	private Button decryptText;
	@FXML
	private ComboBox<Integer> rotorA = new ComboBox<>();
	@FXML
	private ComboBox<Integer> rotorB = new ComboBox<>();
	@FXML
	private ComboBox<Integer> rotorC = new ComboBox<>();
	@FXML
	private TextArea input;
	@FXML
	private TextArea output;
	@FXML
	private ImageView statusOK;

	private EnigmaModel model = new EnigmaModel();

	/**
	 * Metoda inicjujaca kontrolera widoku operacji na tekscie. Ustawia wartosci
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
	 * Metoda wywolujaca szyfrowanie tekstu wprowadzonego w pole text field. Zostaje
	 * wywolana po wcisnieciu przycisku Encrypt. Powoduje wyswietlenie w oknie
	 * danych wyjsciowych w postaci zaszyfrowanej.
	 */
	@FXML
	private void encrypt() {
		output.setText(model.encrypt(rotorA.getValue(), rotorB.getValue(), rotorC.getValue(), input.getText()));
		statusOK.visibleProperty().set(true);
	}

	/**
	 * Metoda wywolujaca deszyfrowanie tekstu wprowadzonego w pole text field.
	 * Zostaje wywolana po wcisnieciu przycisku Decrypt. Powoduje wyswietlenie w
	 * oknie danych wyjsciowych w postaci odszyfrowanej.
	 */
	@FXML
	private void decrypt() {
		output.setText(model.decrypt(rotorA.getValue(), rotorB.getValue(), rotorC.getValue(), input.getText()));
		statusOK.visibleProperty().set(true);
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
}
