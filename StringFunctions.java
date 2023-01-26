package newman;

public class StringFunctions {
	

	public static boolean isWord(String string) {
		boolean isWord = true;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (!isLetter(c)) isWord = false;  
		}
		return isWord;
	}

	public static boolean isLetter(char c) {
		return (c >= 'a' && c <= 'z') ||
		       (c >= 'A' && c <= 'Z');
	}

	public static char findMaxLetter(String string) {
		string = string.replaceAll("\\s","");
		string = string.toLowerCase();
		
		char max = 'a';

		char[] stringChar = string.toCharArray();
		
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < stringChar.length; j++) {
				if (stringChar[j] == 'a' + i) max += i;
			}
		}
		return max;
	}

	public static String reduceWord(String word){

		if (word.equals("")) return "";
		String oldStr, newStr;
		oldStr = word;
		newStr = word;

		do {
			oldStr = newStr;
			for (char alph = 'a'; alph <= 'z'; alph++){
				char up = Character.toUpperCase(alph);
				newStr = newStr.replace("" + alph + up,"");
				newStr = newStr.replace("" + up + alph,"");
			}
		} while (!oldStr.equals(newStr));
		return newStr;
	}

	public static String cyclicReduceWord(String word) {
		
		if (word.equals("")) return "";
		String oldStr, newStr;
		oldStr = word;
		newStr = word;
		do {
			int l = newStr.length();
			oldStr = newStr;
			if ( oppositeCase(oldStr.charAt(0), oldStr.charAt(oldStr.length() - 1)) ) {
				newStr = removeFirstCharacter(oldStr);
				newStr = removeLastCharacter(oldStr);
			}

		} while (!oldStr.equals(newStr) );

		return newStr;
	}

	public static String rotateCyclically(String string) {
		char firstCharacter = string.charAt(0);
		string = removeFirstCharacter(string);
		return string + Character.toString(firstCharacter);
	}

	public static String invert(String word) {
		String outStr;
		StringBuilder sb = new StringBuilder(word);
		sb.reverse();
		for (int i = 0; i < sb.length(); i++) {
			if (Character.isUpperCase(sb.charAt(i))) {
				sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));	
			}
			else {
				sb.setCharAt(i,Character.toUpperCase(sb.charAt(i)));	
			}
		}
		outStr = sb.toString();
		return outStr;
	}


	private static String removeFirstCharacter(String string) {
		return string.substring(1);
	}
	
	private static String removeLastCharacter(String string) {
		return string.substring(1, string.length() - 1);
	}

	private static boolean oppositeCase(char char1, char char2) {
		if (char1 == char2) return false;
		if (Character.toUpperCase(char1) == char2 || Character.toLowerCase(char1) == char2) {
			return true;
		}
		return false;
	}


}
