package newman;

public class FreeGroup {
	// Stored as follows: F<a,b,c> ---> validAlphabet = "abc".
	private String validAlphabet;

	public FreeGroup(String validAlphabet) {
		this.validAlphabet = validAlphabet;
	}

	// Determines if a given word is a valid input.  
	public boolean isValidWord(String string) {
		if (!StringFunctions.isWord(string)) return false;

		boolean isValidWord = true;

		// Change this to use the max function...
		char max = validAlphabet.charAt( validAlphabet.length() - 1 );

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) > max) isValidWord = false;
		}
		return isValidWord;
	}
}
