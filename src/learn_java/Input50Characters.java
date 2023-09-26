package learn_java;

import java.util.Random;

public class Input50Characters {
	public static String generateCharactersEqualTo50() {
		String rndCharacters = "";
		Random rnd = new Random();
		while (rndCharacters.length() < 51) {
			rndCharacters += (char)('a' + rnd.nextInt(122-97 + 1));
		}
		return rndCharacters;
		
	}
	
	public static void main(String[] args) {
		System.out.println(generateCharactersEqualTo50());
	}
}
