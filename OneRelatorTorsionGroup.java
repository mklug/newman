package newman;

public class OneRelatorTorsionGroup {

	private final String fullRelation;
	private final boolean successfulInitiation;
	private final FreeGroup freeGroup;
	private final int power;

	public OneRelatorTorsionGroup(String relation, int n) {

		if (isValidInput(relation) && (n > 1)) {
			successfulInitiation = true;
		} else {
			successfulInitiation = false;
		}

		relation = StringFunctions.reduceWord(relation);
		relation = StringFunctions.cyclicReduceWord(relation);

		String fullRelation = "";

		for (int i = 0; i < n; i++) {
			fullRelation = fullRelation.concat(relation);
		}

		this.fullRelation = fullRelation;
		this.power = n;
		
		char maxLetter = StringFunctions.findMaxLetter(relation);
		String validAlphabet = "";
		for (int i = 0; i < 26; i++) {
			if ('a' + i <= maxLetter) {
				String s = Character.toString('a' + i);
				validAlphabet += s;
			}
		}

		this.freeGroup = new FreeGroup(validAlphabet);
	}

	private boolean isValidInput(String relation) {
		return StringFunctions.isWord(relation);		
	}

	public boolean wasSucessfullyInitialized() {
		return successfulInitiation;
	}

	public FreeGroup getFreeGroup() {
		return freeGroup;
	}

	public int getPower() {
		return power;
	}

	public String getFullRelation() {
		return fullRelation;
	}
}




