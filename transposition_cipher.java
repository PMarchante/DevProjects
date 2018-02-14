import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

public class transposition_cipher {

	public transposition_cipher() {
		
	}

	static String transpositionControl(String text, String key, String mode) throws Exception {
			//displays dialog for user to enter Key
		String[] choices = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		
		key = (String) JOptionPane.showInputDialog(null,"Choose Key",
				"Choose your key", JOptionPane.QUESTION_MESSAGE, null,choices, choices[0]); 
		
		
		if (mode == "encrypt") {
			return encrypt(text, key);

		} else if (mode == "decrypt") {
			return decrypt(text, key);
		} else
			return "";
	}
	
	static String transpositionControl(String text, int key, String mode) throws Exception {
		if (mode == "encrypt") {
			return encrypt(text, key);

		} else if (mode == "decrypt") {
			
			return decrypt(text, key);
		} else
			return "";
	}
	
	static String encrypt(String toEncrypt, int key) {
		
		 char[] res = new char[toEncrypt.length()];
	     int k = 0;

	     for (int j = 0; j < key; j++) {
	         for (int i = j; i < toEncrypt.length(); i += key) {
	             res[k++] = toEncrypt.charAt(i);
	        }
	    }
	     System.out.println(new String(res));
	     return new String(res);
		
	}
	
	static String encrypt(String toEncrypt, String key) {
		int keyInt;
		try {keyInt = Integer.parseInt(key);} 
		catch (Exception e) {
			keyInt =key.length();
			if (keyInt < 2)
				JOptionPane.showMessageDialog(null, "Transposition key must be larger than 2.");
			return "Transposition key must be larger than 2.";
			}
		
		
		
		 char[] res = new char[toEncrypt.length()];
	     int k = 0;

	     for (int j = 0; j < keyInt; j++) {
	         for (int i = j; i < toEncrypt.length(); i += keyInt) {
	             res[k++] = toEncrypt.charAt(i);
	        }
	    }
	     System.out.println(new String(res));
	     return new String(res);
		
	}

	public static String decrypt(String decrypt, int key) {
		
		
		 char[] res = new char[decrypt.length()];
	     int k = 0;

	     for (int j = 0; j < key; j++) {
	         for (int i = j; i < decrypt.length(); i += key) {
	             res[i] = decrypt.charAt(k++);
	        }
	    }

	    return String.copyValueOf(res);

	}
	
	public static String decrypt(String decrypt, String key) {
		
		int keyInt;
		try {keyInt = Integer.parseInt(key);} 
		catch (Exception e) {
			keyInt =key.length();
			if (keyInt < 2)
				JOptionPane.showMessageDialog(null, "Transposition key must be larger than 2.");
			return "Transposition key must be larger than 2.";
			}
		
		
		
		 char[] res = new char[decrypt.length()];
	     int k = 0;

	     for (int j = 0; j < keyInt; j++) {
	         for (int i = j; i < decrypt.length(); i += keyInt) {
	             res[i] = decrypt.charAt(k++);
	        }
	    }

	    return String.copyValueOf(res);

	}

	public static void main(String[] args) {
		String temp = "";
		String toEncrypt = "Looks like we are making good progress.";
		int key = 8;
		try {
			temp = transpositionControl(toEncrypt, key, "encrypt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(temp);
		System.out.println(decrypt(temp, key));
		System.out.println("\n\nNow with a string key.\n\n");
		temp = encrypt(toEncrypt, "key");
		System.out.println(decrypt(temp, "key"));

	}

}
