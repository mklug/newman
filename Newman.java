package newman;

import java.util.Scanner;

public class Newman {

	public static void main(String[] args) {
		if (args.length > 3) {
			String inputError =   "Usage: \nnewman [relation R] [power n ] [valid word w]" + 
						" determines if w is trivial in one relator <gens up to largest letter in w | R^n>.\n" + 
						"newman [relation R] [pwer n] enters session allowing multiple words " + 
						"to be entered.\n" + 
						"No input for a session with varying relator.\n" +
						"(n must be greater than 1.)";
			System.out.println(inputError);
			System.exit(64);
		} else if (args.length == 3) {
			int n = convertPower(args[1]);
			if (n == 0) return;
			runAlgorithm(args[0], n, args[2]);
		} else if (args.length == 2){
			int n = convertPower(args[1]);
			if (n == 0) return;
			runFixedRelatorPrompt(args[0], n);
		} else {
			runVaryingRelatorPrompt();
		}
	}

	private static void runAlgorithm(String relation, int n,  String word) {

		OneRelatorTorsionGroup group = new OneRelatorTorsionGroup(relation, n);

		if (wasValidRelation(group)  
				&& wasValidWord(group, word)) {
			printAlgorithmResult(group, word);
		} 
	}

	private static void runFixedRelatorPrompt(String relation, int n) {
		Scanner scanner = new Scanner(System.in);

		OneRelatorTorsionGroup group = new OneRelatorTorsionGroup(relation, n);
		for(;;) {
			printWordPrompt();
			System.out.print("> ");
			if (!scanner.hasNextLine()) break;
			String word = scanner.nextLine();
			// Add triviality test here.
			printAlgorithmResult(group, word);
		}	
	}

	private static void runVaryingRelatorPrompt() {
		Scanner scanner = new Scanner(System.in);

		for(;;) {
			printRelatorPrompt();
			System.out.print("> ");
			if (!scanner.hasNextLine()) break;
			String relation = scanner.nextLine();

			printPowerPrompt();
			System.out.print("> ");
			if (!scanner.hasNextLine()) break;
			int n = convertPower(scanner.nextLine());
			if (n==0) break;

			OneRelatorTorsionGroup group = new OneRelatorTorsionGroup(relation, n);

			printWordPrompt();
			System.out.print("> ");
			if (!scanner.hasNextLine()) break;
			String word = scanner.nextLine();

			// Add triviality test here. 
			printAlgorithmResult(group, word);			
		}
	}

	private static void printRelatorPrompt() {
		System.out.println("Enter the relator for the group:");
	}

	private static void printWordPrompt() {
		System.out.println("Enter the word in the group:");
	}

	private static void printPowerPrompt() {
		System.out.println("Enter the power for the relation:");
	}

	private static void printAlgorithmResult(OneRelatorTorsionGroup group, String word) {
		if (SpellingAlgorithm.isTrivial(group, word) ) {
			System.out.println("The word is trivial.");
		} else {
			System.out.println("The word is not trivial.");	
		}
	}

	private static int convertPower(String power) {
		try {
			int n = Integer.valueOf(power);
			if (n < 2) {
				System.out.println("Power input not large enough. Must be greater than 1.");
				return 0;
			}
			return n;
		} catch (Exception e) {
			System.out.println("Power input not valid.");
			return 0;
		}
	}

	private static boolean wasValidRelation(OneRelatorTorsionGroup group) {
		if (!group.wasSucessfullyInitialized()) {
			System.out.println("Input relation or power was not valid.");
			return false;
		}
		return true;	
	}

	private static boolean wasValidWord(OneRelatorTorsionGroup group, String word) {
		FreeGroup F = group.getFreeGroup();
		if (!F.isValidWord(word)) {
			System.out.println("Input word was not valid.");
			return false;
		}
		return true;
	}
}
