import javax.swing.JOptionPane;

public class caesar_Cipher {

	static String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.!?,:; @#$%^&*()-=_+<>'\"[]{}\\|"; // 82
																													// characters
	static String translated = "";
	// test values
	static String testString = "This is my secret message. How awesom is that!|";
	int testKey = 20;

	public caesar_Cipher() {
	}

	public static String cipher(String text, String shiftKey, String mode) {
		int keyVal = 0;
		translated = "";
		int key = 0;
		
		String[] choices = new String[81];
		for (int i = 0; i < 81; i++) {
			choices[i] = Integer.toString(i);
		}

		shiftKey = (String) JOptionPane.showInputDialog(null, "Choose Key", "Choose your key",
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		
		try {
			key = Integer.parseInt(shiftKey.trim());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,
					"Key for a Caesar Cipher must be an intiger. \n\nPlease enter a valid key.");
			return "";
		}

		if (mode == "encrypt") {

			for (int i = 0; i < text.length(); i++) {
				int charLocation = LETTERS.indexOf(text.charAt(i));
				keyVal = (charLocation + key) % 82;
				char replaceVal = LETTERS.charAt(keyVal);
				translated += replaceVal;
			}
		} else if (mode == "decrypt") {

			for (int i = 0; i < text.length(); i++) {
				int charLocation = LETTERS.indexOf(text.charAt(i));
				keyVal = (charLocation - key) % 82;
				if (keyVal < 0) {
					keyVal = LETTERS.length() + keyVal;
				}
				char replaceVal = LETTERS.charAt(keyVal);

				translated += replaceVal;
			}

		}

		return translated;
	}
// TESTING
//	public static void main(String[] args) {
//
//		System.out.println(testString);
//		System.out.println(cipher(testString, "10", "encrypt"));
//		String testEncrypt = cipher(testString, "10", "encrypt");
//		System.out.println(testEncrypt);
//		System.out.println(cipher(testEncrypt, "10", "decrypt"));
//		System.out.println(cipher(testEncrypt, "10S", "decrypt"));
//		// System.out.println(decrypt(encrypt(message, 3), 3));
//		// sc.close();
//	}

}
