package hangman.service;

import java.util.ArrayList;
import java.util.Scanner;

public class HangmanServiceImpl implements HangmanService {

	@Override
	public void playHangman(String[] words, int attemps) {
		int score=0;
		int randomWordNumber = (int) (Math.random() * words.length);
		char[] enteredLetters = new char[words[randomWordNumber].length()];
		ArrayList<String> enteredWrongLetters = new ArrayList<String>();
		int triesCount = 0;
		int triesWrongCount = 0;

		boolean wordIsGuessed = false;
		boolean play = true;

		while(play) {

			do {
				switch (enterLetter(words[randomWordNumber], enteredLetters, enteredWrongLetters, score)) {
					case 0:
						triesWrongCount++;
						triesCount++;
						break;
					case 1:
						triesCount++;
						break;
					case 2:
						break;
					case 3:
						wordIsGuessed = true;
						break;
				}
			} while ((!wordIsGuessed)&&(triesWrongCount<attemps));

			if(wordIsGuessed) {
				score+=10;
				System.out.println("\nThe word is " + words[randomWordNumber] +" you try " + (triesCount)+ " time(s) with score: "+score);
			}else {
				triesWrongCount=0;
				gameOverConsole(words[randomWordNumber]);
			}


			System.out.println("If do you want to continue playing, press y!");
			Scanner input = new Scanner(System.in);
			char userInput = input.nextLine().charAt(0);

			if(String.valueOf(userInput).equals("y")||String.valueOf(userInput).equals("Y")) {
				randomWordNumber = (int) (Math.random() * words.length);
				enteredLetters = new char[words[randomWordNumber].length()];
				enteredWrongLetters = new ArrayList<String>();
				play = true;
				wordIsGuessed = false;
				triesCount=0;
				triesWrongCount=0;
			}else{
				System.out.println("Goodbye!");
				play=false;
			}

		}
	}

	private int enterLetter(String word, char[] enteredLetters, ArrayList<String> enteredWrongLetters, int score) {
		System.out.print("Enter a letter in word: ");

		if (! printWord(word, enteredLetters)) {
			return 3;
		}
		System.out.print(" > ");

		Scanner input = new Scanner(System.in);
		int emptyPosition = findEmptyPosition(enteredLetters);
		char userInput = input.nextLine().charAt(0);

		if (inEnteredLetters(userInput, enteredLetters)) {
			System.out.println(userInput + " is already in the word");
			return 2;
		}
		else if (word.contains(String.valueOf(userInput))) {
			enteredLetters[emptyPosition] = userInput;
			return 1;
		}
		else {

			if(!enteredWrongLetters.contains(String.valueOf(userInput))) {
				enteredWrongLetters.add(String.valueOf(userInput));
			}
			System.out.println(userInput + " is not in the word, u have entered already the above wrong letters: "+enteredWrongLetters);
			return 0;
		}
	}

	private boolean printWord(String word, char[] enteredLetters) {
		boolean asteriskPrinted = false;
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			if (inEnteredLetters(letter, enteredLetters))
				System.out.print(letter);
			else {
				System.out.print('-');
				asteriskPrinted = true;
			}
		}
		return asteriskPrinted;
	}

	private boolean inEnteredLetters(char letter, char[] enteredLetters) {
		return new String(enteredLetters).contains(String.valueOf(letter));
	}

	private int findEmptyPosition(char[] enteredLetters) {
		int i = 0;
		while (enteredLetters[i] != '\u0000') i++;
		return i;
	}

	private void gameOverConsole(String word) {
		System.out.println("GAME OVER U have reached your attemps!");
		System.out.println("   ____________");
		System.out.println("   |          _|_");
		System.out.println("   |         /   \\");
		System.out.println("   |        |     |");
		System.out.println("   |         \\_ _/");
		System.out.println("   |          _|_");
		System.out.println("   |         / | \\");
		System.out.println("   |          / \\ ");
		System.out.println("___|___      /   \\");
		System.out.println("GAME OVER! The word was " + word);
	}
}
