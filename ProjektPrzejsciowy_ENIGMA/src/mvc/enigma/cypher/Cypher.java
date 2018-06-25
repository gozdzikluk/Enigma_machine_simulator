package mvc.enigma.cypher;

import mvc.enigma.rotor.Rotor;

public class Cypher {
	private Rotor smallRotor = new Rotor();
	private Rotor mediumRotor = new Rotor();
	private Rotor largeRotor = new Rotor();

	/**
	 * Method uruchamijaca szyfrowanie tekstu znak po znaku.
	 *
	 * @param text
	 *            String do zaszyfrowania.
	 * @param choice
	 *            String informujacy o rodzaju operacji szyfrowania lub
	 *            deszyfrowania.
	 * @return String zwraca zaszyfrowany tekst.
	 */
	public String cypher(String text, String choice) {
		String cypherArray = "";

		for (int i = 0; i < text.length(); i++) {
			cypherArray += enigma(text.charAt(i), choice);
		}

		return cypherArray;
	}

	/**
	 * Metoda do szyfrowania lub deszyfrowania znaku podanego jako parametr
	 * wywolania metody. Podczas szyfrowania po kazdym znaku obraca pierwszy beben
	 * szyfrjacy o jedna pozycje. Nastepnie sprawdza jego pozycje, jesli wynosi ona
	 * tyle co dlugosc alfabetu obraca kolejny beben szyfrujacy.
	 *
	 * @param ch
	 *            char Znak do zaszyfrowania lub odszyfrowania
	 * @param choice
	 *            String informujacy o rodzaju operacji do wykonania, szyfrowanie
	 *            "encrypt lub deszyfrowanie "decrypt"
	 * @return char Zwraca zaszyfrowany lub odszyfrowany znak podany jako parametr
	 *         wywolania metody
	 */
	private char enigma(char ch, String choice) {
		char character = ch;

		switch (choice) {
		case "decrypt": // decrypt
			character = largeRotor.charAt(smallRotor.indexOf(ch));
			character = largeRotor.charAt(mediumRotor.indexOf(character));
			break;
		case "encrypt": // encrypt
			character = mediumRotor.charAt(largeRotor.indexOf(ch));
			character = smallRotor.charAt(largeRotor.indexOf(character));
			break;
		default:
			System.out.println("Invalid choice.");
		}

		smallRotor.turn();

		if (smallRotor.getTurn() % 91 == 0) {
			mediumRotor.turn();
			if (mediumRotor.getTurn() % 91 == 0) {
				largeRotor.turn();
			}
		}
//		System.out.println("smal: " + smallRotor.getTurn());
//		System.out.println("medium: " + mediumRotor.getTurn());
//		System.out.println("large: " + largeRotor.getTurn());
		return character;
	}

	/**
	 * Metoda do ustawiania pozycji rotorow.
	 *
	 * @param r1
	 *            Pobiera liczbe caakowita, ktora bedzie uzywana do ustawienia
	 *            pozycji poczatkowej dla pierwszego rotora.
	 * @param r2
	 *            Pobiera liczbe caakowita, ktora bedzie uzywana do ustawienia
	 *            pozycji poczatkowej dla drugiego rotora.
	 * @param r3
	 *            Pobiera liczbe caakowita, ktora bedzie uzywana do ustawienia
	 *            pozycji poczatkowej dla trzeciego rotora.
	 */
	public void setRotors(int r1, int r2, int r3) {
		smallRotor.setTurn(r1);
		mediumRotor.setTurn(r2);
		largeRotor.setTurn(r3);
	}

	/**
	 * Getter do uzyskania pozycji pierwszego rotora.
	 *
	 * @return zwraca pozycje pierwszego rotora jako String.
	 */
	public String getSmallRotorPosition() {
		return String.valueOf(smallRotor.getTurn());
	}

	/**
	 * Getter do uzyskania pozycji drugiego rotora.
	 *
	 * @return zwraca pozycje drugiego rotora jako String.
	 */
	public String getMediumRotorPosition() {
		return String.valueOf(smallRotor.getTurn());
	}

	/**
	 * Getter do uzyskania pozycji trzeciego rotora.
	 *
	 * @return zwraca pozycje trzeciego rotora jako String.
	 */
	public String getLargeRotorPosition() {
		return String.valueOf(largeRotor.getTurn());
	}

	/**
	 * Getter do uzyskania wymiaru rotora.
	 *
	 * @return zwraca rozmiar rotora jako String.
	 */
	public int getRotorSize() {
		return new Rotor().getSize();
	}
}
