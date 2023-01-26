package newman;

public class SpellingAlgorithm {

	// Returns false if the result is not trivial.
	public static boolean isTrivial(OneRelatorTorsionGroup group, String word) {
		int n = group.getPower();

		// Note that both of the following words are reduced and cyclically reduced.  
		String fullRelation = group.getFullRelation();
		String fullRelationInverse = StringFunctions.invert(fullRelation);

		word = StringFunctions.reduceWord(word);

		String oldStr, newStr;
		int l = fullRelation.length();
		oldStr = word;
		newStr = word;
		// Check if we have a word in a free group.  
		if (l == 0 && word.length() != 0) return false;

		//int reductionSubstringLength = (int)( (n-1) * (l / n) + 1 );
		int reductionSubstringLength = (int)( (n-1) * (l / n) );
		int rsl = reductionSubstringLength;

		// Keep reducing using the relation until no reduction takes place.
		do {
			oldStr = newStr;
			if (newStr.equals("")) return true;
			// Check if the word is too short for any reductions.  
			if (newStr.length() < reductionSubstringLength) return false;

			// Look at all possible lengths for the substitution. 
			for (int j = 0; j < l - rsl; j++) {
				reductionSubstringLength += 1;

				// Look at all cyclic rotations of the relation R^n and R^{-n}.	
				for (int i = 0; i <= l; i++) {

					fullRelation = StringFunctions.rotateCyclically(fullRelation);
					fullRelationInverse = StringFunctions.rotateCyclically(fullRelationInverse);

					// Check if the first ((n - 1) / n) * l + 1 characters of the cyclic roations
					// match with some subword of the word w.   
					String subwordFullRelation = fullRelation.substring(0, reductionSubstringLength);
					String subwordFullRelationInverse = fullRelationInverse.substring(0, reductionSubstringLength);
					
					String replaceFullRelation = "";
					String replaceFullRelationInverse = "";

					if (reductionSubstringLength != l) {
						replaceFullRelation = StringFunctions.invert( fullRelation.substring( reductionSubstringLength, l ) );
						replaceFullRelationInverse = StringFunctions.invert( fullRelationInverse.substring( reductionSubstringLength, l) );
					}
					newStr = oldStr.replace(subwordFullRelation, replaceFullRelation);
					newStr = newStr.replace(subwordFullRelationInverse, replaceFullRelationInverse);
				}
			}
		} while (!oldStr.equals(newStr));
		return (newStr.equals(""));	
	}
}
