
public class revers_Cipher {

	static String revers_Cipher(String toEncrypt) {

		String textOut = "";
		for (int i = toEncrypt.length() - 1; i >= 0; i--) {
			textOut += toEncrypt.charAt(i);
		}

		return textOut;

	}

	public static void main(String[] args) {
		String temp = "";
		String toEncrypt = "This is my secret message. How awesom is that!";
		System.out.println(revers_Cipher(toEncrypt));
	}

}
