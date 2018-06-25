package mvc.enigma.rotor;

public class Rotor {
	private char[] rotor;
	private int alphabetLength;
	private int turn = 0;
	private char[] defaultRotor;
	private char[] alphabet;

	/**
	 * Konstruktor bezargumentowy dla obiektu rotor.
	 */
	public Rotor() {
		setAlphabet();
		this.turn = 0;
		this.rotor = getAlphabet();
		this.alphabetLength = rotor.length;
		this.defaultRotor = this.rotor;
	}

	/**
	 * Metoda do zwrocenia indeksu szukanego znaku.
	 *
	 * @param character
	 *            przyjmuje zmienna typu char.
	 * @return zwraca pozycje szukanego znaku.
	 */
	public int indexOf(char character) {
		for (int iterator = 0; iterator <= this.alphabetLength; iterator++) {
			if (this.rotor[iterator] == character) {
				return iterator;
			}
		}
		return -1;
	}

	/**
	 * Metoda do uzyskania ilosci wykonanych obrot�w rotora.
	 * 
	 * @return zwraca ilosc wykonanych obrotow przez rotor
	 */
	public int getTurn() {
		return this.turn;
	}

	/**
	 * Metoda do wykonania zadanej liczby obrot�w rotora.
	 * 
	 * @param int
	 *            liczba obrotow rotora
	 */
	public void setTurn(int turns) {
		for (int iterator = 0; iterator < turns; iterator++) {
			turn();
		}
	}

	/**
	 * Metoda do obracania rotora o jedna pozycje.
	 */
	public void turn() {
		char character = this.rotor[0];

		for (int i = 1; i < this.alphabetLength; i++) {
			this.rotor[i - 1] = this.rotor[i];
		}
		this.rotor[this.alphabetLength - 1] = character;
		this.turn++;
	}

	/**
	 * Metoda zwraca znak z tablicy
	 *
	 * @param index
	 *            typu int. Pozycja zadanego znaku.
	 * @return zwraca znak na poszukiwanej pozycji.
	 */
	public char charAt(int index) {
		if (index > this.alphabetLength) {
			System.out.println("This is greater than alphabetLength");
			return 'a';
		}
		return this.rotor[index];
	}

	/**
	 * Metoda do uzyskania dlugosci alfabetu.
	 * 
	 * @return zwraca dlugosc tablicy typu int
	 */
	public int getSize() {
		return this.alphabetLength;
	}

	/**
	 * Metda do resetowania rotora. Ustawia rotor w wyjsciowej pozycji.
	 */
	public void resetRotor() {
		this.rotor = this.defaultRotor;
	}

	/**
	 * Metoda generujaca alfabet.
	 */
	public void setAlphabet() {
		String sAlphabet = "";
		for (int i = 0; i < 123; i++) {
			//if (((i >= 65) && (i <= 90)) || ((i >= 97) && (i <= 122))) {
			if((i>=32) && (i<=126)){
				sAlphabet += (char) i;
			}
		}
		alphabet = sAlphabet.toCharArray();
		//System.out.println(alphabet.length);
	}

	/**
	 * Metoda zwracajaca alfabet typu char[].
	 * 
	 * @return zwraca alfabet char[].
	 */
	public char[] getAlphabet() {
		return alphabet;
	}

}// class
