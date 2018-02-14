/*
 * Author: Jesse
 * 
 * Program will accept three strings text to be encrypted, key, and mode(encrypt/decrypt)
 * it will then encrypt or decrypt the text with the provided key. 
 * */


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import java.security.SecureRandom;
import java.util.Base64;

public class threeDES {

	public threeDES() throws Exception {

	}

	static String threeDESControl(String text, String key, String mode) throws Exception {

		//Add this
		//displays dialog for user to enter Key
		key = JOptionPane.showInputDialog("Key");	
		//Stop Add
		
		// variables
		// create a binary key from the provided key
		SecureRandom secureRandom = new SecureRandom(key.getBytes());
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
		keyGenerator.init(secureRandom);
		SecretKey secretkey = keyGenerator.generateKey();

		// create an instance of cipher
		Cipher cipher = Cipher.getInstance("DESede");
		Cipher deCipher = Cipher.getInstance("DESede");

		// initialize the cipher with the key
		cipher.init(Cipher.ENCRYPT_MODE, secretkey);
		// initialize the cipher with the key
		deCipher.init(Cipher.DECRYPT_MODE, secretkey);

		if (mode == "encrypt") {
			return encrypt(text, cipher);

		} else if (mode == "decrypt") {
			return decrypt(text, deCipher);
		} else
			return "";
	}

	static String encrypt(String toEncrypt, Cipher cipher) throws Exception {
		String encryptedString = null;
		// encrypts the plaintext bytes
		byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
		// converts encrypted bytes back to readable text
		encryptedString = Base64.getEncoder().encodeToString(encrypted);
		return encryptedString;
		// return encrypt(encrypt, key);

	}

	static String decrypt(String toDecrypt, Cipher deCipher) throws Exception {
		try {

			// converts encrypted bytes back to readable text
			byte[] toDecryptByte = Base64.getDecoder().decode(toDecrypt);
			byte[] decrypted = deCipher.doFinal(toDecryptByte);

			return new String(decrypted);

		} catch (final BadPaddingException ex) {
			System.out.println("Wrong Password...");
			JOptionPane.showMessageDialog(null, "Wrong 3DES Password provided.");
			return "Wrong Password entered!!!";
		}

	}

	// used for testing
//	public static void main(String[] args) throws Exception {
//		String toEncrypt = "This is my secret message. How awesome is that?";
//
//		System.out.println("Encrypting...");
//		String encrypted = threeDESControl(toEncrypt, "password", "encrypt");
//		System.out.println(encrypted);
//
//		// System.out.println(encrypt(toEncrypt, "password"));
//		System.out.println("Decrypting...");
//		String decrypted = threeDESControl(encrypted, "password", "decrypt");
//		System.out.println(decrypted);
//
//		System.out.println("Decrypted text: " + decrypted);
//		System.out.println("Decrypting Wrong Password...");
//		decrypted = threeDESControl(encrypted, "assword", "decrypt");
//
//	}
}
