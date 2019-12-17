/**
 * CS312 Assignment 6.
 *
 * On my honor, <Seong Beom Ko>, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 *  email address: ksb3602@gmail.com
 *  UTEID: sk32262
 *  Unique 5 digit course ID: 86320
 *  Grader name: Amir
 *  Number of slip days used on this assignment: 0
 */
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		intro();
		Scanner sc = new Scanner(System.in);
		System.out.println("Topic: ");
		System.out.println("1. COMIDAS, BEBIDAS, Y ESPECIAS");
		System.out.println("2. DEPORTES");
		System.out.println("3. PERSONAS RELACIONADAS CON LOS DEPORTES");
		System.out.println("4. LOS MESES DEL ANO");
		System.out.println("5. ALGUNAS MATERIAS/ ASIGNATURAS ACADEMICAS");
		System.out.print("Enter your choice: ");
		int choice = sc.nextInt();
		PhraseBank phrases = buildPhraseBank(choice);
		// CS312 Students -- Do not create any additional Scanners.
		Scanner keyboard = new Scanner(System.in);
		// CS12 students: add your code here
		beginGame(keyboard, phrases);
		keyboard.close();
	}
	// CS12 students: add your methods here.

	// this method takes a random phrase from the "PhraseBank
	public static String shuffle(PhraseBank phrases) {

		System.out.println("\nI am thinking of a " + phrases.getTopic() + " ...");
		String phrase = phrases.getNextPhrase();

		return phrase;
	}

	// this method alters the phrase to a secret phrase expressed as the ,"*", stars
	public static String creatingSecretPhrase(String phrase, String alphabets) {

		String secretPhrase = "";
		String star = "*";

		for (int count = 1; count < phrase.length(); count++) {

			if (!(secretPhrase.contains(star))) {
				secretPhrase = secretPhrase.replace(secretPhrase, star);
			}
			if (alphabets.indexOf(phrase.charAt(count)) != -1 && !(phrase.charAt(count) == '_')) {
				secretPhrase += star.charAt(0);
			} else {
				secretPhrase += phrase.charAt(count);
			}
		}
		return secretPhrase;
	}

	// this method stores the original phrase and the altered secret phrase
	// and in the while loop, it receives the updated phrase and alphabets from
	// the two different method when user types in a input.
	// also it counts the wrong guess whenever the input is not included in the
	// original phrase.
	public static void beginGame(Scanner keyboard, PhraseBank phrases) {

		String phrase = shuffle(phrases);
		int num_try = 5;
		int wrongGuess = 0;
		String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String secretPhrase = creatingSecretPhrase(phrase, alphabets);
		String updatedAlphabets = alphabets;
		for (int count = 0; count < updatedAlphabets.length() - 1; count += 2) {

			updatedAlphabets = updatedAlphabets.substring(0, count + 1) + "_"
					+ updatedAlphabets.substring(count + 1, updatedAlphabets.length());
		}
		System.out.printf("\nThe current phrase is ");
		System.out.println(secretPhrase + "\n");

		while (wrongGuess < num_try && !(phrase.equals(secretPhrase))) {

			guessing(updatedAlphabets);
			String input = keyboard.nextLine().toUpperCase();
			String firstCharacter = input.substring(0, 1);
			if (!(firstCharacter.contains("_")) && updatedAlphabets.contains(firstCharacter)) {

				System.out.printf("\nYou guessed %s.\n", firstCharacter);
			}
			wrongGuess = goodGuessOrWrongGuess(phrase, firstCharacter, wrongGuess, updatedAlphabets, input);
			secretPhrase = phraseUpdate(num_try, wrongGuess, secretPhrase, firstCharacter, phrase, updatedAlphabets);
			updatedAlphabets = alphabetUpdate(firstCharacter, updatedAlphabets);
		}
		gameResult(wrongGuess, phrase);
		moreGames(keyboard, phrases);
	}

	// this method prints out the remained alphabets as the game keeps go on
	public static void guessing(String updatedAlphabets) {

		System.out.println("The letters you have not guessed yet are: ");
		System.out.println(updatedAlphabets);
		System.out.print("\nEnter your next guess: ");
	}

	// this method prints out the statement whether it was wrong, good or valid guess
	// also, it counts the wrong guess and return the value to the method "beginGame"
	public static int goodGuessOrWrongGuess(String phrase, String firstCharacter, int wrongGuess,
			String updatedAlphabets, String input) {

		if (phrase.contains(firstCharacter) && updatedAlphabets.contains(firstCharacter)
				&& !(firstCharacter.contains("_"))) {
			System.out.println("\nThat is present in the secret phrase.");
			System.out.printf("\nNumber of wrong guesses so far: %d\n", wrongGuess);
		} else if (updatedAlphabets.contains(firstCharacter) && !(firstCharacter.contains("_"))) {
			System.out.println("\nThat is not present in the secret phrase.");
			wrongGuess++;
			System.out.printf("\nNumber of wrong guesses so far: %d\n", wrongGuess);
		} else {
			System.out.println("\n" + input + " is not a valid guess.");
		}
		return wrongGuess;
	}

	// this method update the alphabet list as the user types in an input
	public static String alphabetUpdate(String firstCharacter, String updatedAlphabets) {

		int alphabetIndex = -1;
		for (int count = 0; count < updatedAlphabets.length(); count++) {
			if (updatedAlphabets.charAt(count) == firstCharacter.charAt(0) && !(firstCharacter.contains("_"))) {

				alphabetIndex = updatedAlphabets.indexOf(updatedAlphabets.charAt(count));
				count = updatedAlphabets.length();
			}
		}
		if (alphabetIndex != -1) {
			if (alphabetIndex != 0 && alphabetIndex != updatedAlphabets.length() - 1) {
				updatedAlphabets = updatedAlphabets.substring(0, alphabetIndex)
						+ updatedAlphabets.substring(alphabetIndex + 2);
			} else if (alphabetIndex == 0) {
				updatedAlphabets = updatedAlphabets.substring(2);
			} else if (alphabetIndex == updatedAlphabets.length() - 1) {
				updatedAlphabets = updatedAlphabets.substring(0, updatedAlphabets.length() - 2);
			}
		}
		return updatedAlphabets;
	}

	// this method update the secret phrase when the user types in an input
	public static String phraseUpdate(int num_try, int wrongGuess, String secretPhrase, String firstCharacter,
			String phrase, String updatedAlphabets) {

		int phraseIndex = -1;
		String newPhrase = secretPhrase;

		for (int num = 0; num < phrase.length(); num++) {

			char ch = phrase.charAt(num);

			if (firstCharacter.indexOf(ch) != -1) {

				phraseIndex = num;
			}
			if (phraseIndex != -1) {
				if (phraseIndex != 0) {
					newPhrase = newPhrase.substring(0, phraseIndex) + firstCharacter
							+ newPhrase.substring(phraseIndex + 1);
				} else {
					newPhrase = firstCharacter + newPhrase.substring(1);
				}
			}
			phraseIndex = -1;
		}
		if (phrase.equals(newPhrase)) {
			System.out.print("The phrase is ");
			System.out.println(newPhrase + ".");
		} else if (wrongGuess != num_try) {
			if (updatedAlphabets.contains(firstCharacter) && !(firstCharacter.contains("_"))) {
				System.out.print("The current phrase is ");
				System.out.println(newPhrase + "\n");
			}
		}
		return newPhrase;
	}

	// this method prints out the game result
	public static void gameResult(int wrongGuess, String phrase) {

		if (wrongGuess != 5) {
			System.out.println("You win!!");
		} else {
			System.out.println("You lose. The secret phrase was " + phrase);
		}
	}

	// this method decides whether the user wants more games or not
	public static void moreGames(Scanner keyboard, PhraseBank phrases) {

		String moreGames = "Y";
		System.out.println("Do you want to play again?");
		System.out.print("Enter \'Y\' or \'y\' to play again: ");
		String answer = keyboard.nextLine().toUpperCase();

		if (moreGames.equals(answer)) {

			beginGame(keyboard, phrases);
		}
	}

	// Build the PhraseBank.
	// If args is empty or null, build the default phrase bank.
	// If args is not null and has a length greater than 0
	// then the first elements is assumed to be the name of the
	// file to build the PhraseBank from.
	public static PhraseBank buildPhraseBank(String[] args) {
		PhraseBank result;
		if (args == null || args.length == 0 || args[0] == null || choice > 5 || choice < 1)
			result = new PhraseBank();
		else
			result = new PhraseBank(choice);
		return result;
	}

	// show the intro to the program
	public static void intro() {
		System.out.println("This program plays the game of hangman.");
		System.out.println();
		System.out.println("The computer will pick a random phrase.");
		System.out.println("Enter letters for your guess.");
		System.out.println("After 5 wrong guesses you lose.");
	}
}
