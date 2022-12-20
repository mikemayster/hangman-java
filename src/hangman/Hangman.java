package hangman;

import hangman.service.HangmanService;
import hangman.service.HangmanServiceImpl;

public class Hangman {

	private final static HangmanService service = new HangmanServiceImpl();
	private static String[] words = {"writer", "that", "program", "plant", "developer", "elon", "musk", "tron", "computer", "perform"};
	private static int attemps=3;

	public static void main(String[] args) {
		service.playHangman( words, attemps);
	}

}
