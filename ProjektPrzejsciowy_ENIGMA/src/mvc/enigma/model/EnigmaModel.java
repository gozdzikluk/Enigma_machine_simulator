package mvc.enigma.model;

import mvc.enigma.cypher.Cypher;

public class EnigmaModel {
	private Chooser choice = new Chooser();
	private Cypher cy = new Cypher();
	private String file;
	private String cypherText, outputText;

	/**
	 * Metoda wywoalujaca metody szyfrujace dla takstu wprowadzonego z klawiatury w
	 * pole testowe.
	 *
	 * @param rotorA
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            pierwszego rotora.
	 * @param rotorB
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            drugiego rotora.
	 * @param rotorC
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            trzeciego rotora.
	 * @param text
	 *            przyjmuje String do odszyfrowania.
	 * @return zwraca String z zaszyfrowanym tekstem.
	 */
	public String encrypt(int rotorA, int rotorB, int rotorC, String text) {
		cy = new Cypher();
		cy.setRotors(rotorA, rotorB, rotorC);
		return cy.cypher(text, "encrypt");
	}

	/**
	 * Metoda do wywolania metody wyboru pliku tekstowego z klasy Chooser. Po
	 * wyborze pliku zwraca sciezke do tego pliku.
	 * 
	 * @return Zwraca sciezke wybranego pliku tekstowego.
	 */
	public java.nio.file.Path chooseInputFile() {
		this.file = choice.selectFile();
		return choice.getInputhPath();
	}

	/**
	 * Metoda do wywolania metody wyboru pliku tekstowego z klasy Chooser. Po
	 * wyborze pliku zwraca sciezke do tego pliku.
	 * 
	 * @return Zwraca sciezke do wybranego pliku wyjsciowego
	 */
	public java.nio.file.Path chooseOutputFile() {
		return choice.chooseFileToSave();
	}

	/**
	 * Metoda do szyfrowania pliku tekstowego. Szyfruje wczesniej wczytany plik
	 * tekstowy i zapisuje dane wyjsciowe we wczesniej wybranym pliku wyjsciowym.
	 * 
	 * @param rotorA
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            pierwszego rotora.
	 * @param rotorB
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            drugiego rotora.
	 * @param rotorC
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            trzeciego rotora.
	 */
	public void encryptFile(int rotorA, int rotorB, int rotorC) {
		cy = new Cypher();
		cy.setRotors(rotorA, rotorB, rotorC);
		if (file != null) {
			this.cypherText = cy.cypher(file, "encrypt");
			choice.saveFile(cypherText);
		}
	}

	/**
	 * Metoda do deszyfrowania tekstu wpisanego w Text Field.
	 *
	 * @param rotorA
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            pierwszego rotora.
	 * @param rotorB
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            drugiego rotora.
	 * @param rotorC
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            trzeciego rotora.
	 * @param text
	 *            przyjmuje tekst, ktory ma byc odszyfrowany.
	 * @return zwraca String z odszyfrowanym tekstem..
	 */
	public String decrypt(int rotorA, int rotorB, int rotorC, String text) {
		cy = new Cypher();
		cy.setRotors(rotorA, rotorB, rotorC);
		return cy.cypher(text, "decrypt");
	}

	/**
	 * Metoda do deszyfrowania wczesniej wybranego pliku tekstowego. Zapisuje dane
	 * wyjsciowe we wczesniej wybranym pliku wyjsciowym.
	 *
	 * @param rotorA
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            pierwszego rotora.
	 * @param rotorB
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            drugiego rotora.
	 * @param rotorC
	 *            przyjmuje liczbe caakowita, ktora bedzie poczatkowa pozycja
	 *            trzeciego rotora.
	 */
	public void decrypt(int rotorA, int rotorB, int rotorC) {
		cy = new Cypher();
		cy.setRotors(rotorA, rotorB, rotorC);
		if (file != null) {
			this.outputText = cy.cypher(file, "decrypt");
			choice.saveFile(outputText);
		}
	}

	/**
	 * Metoda sluzaca do uzyskania informacji o wielkosci rotora..
	 *
	 * @return zwraca int zawierajacy informacje o wielkosci rotora.
	 */
	public int rotorSize() {
		return cy.getRotorSize();
	}

}
