package EncyptionProject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.Toolkit;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
*@author Pedro Marchante

*/

public class EncryptionGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
    private JTextArea textArea;
    private String outText;
    private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 Runnable gui = () -> {
	            EncryptionGUI frame = new EncryptionGUI();
	            frame.setVisible(true);
	            
	        };//end runnable
	        gui.run();
	}//end main
	
	/**
	 * Create the frame.
	 */
	public EncryptionGUI() {
		
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(EncryptionGUI.class.getResource("/Resources/lock.png")));
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setForeground(Color.BLACK);
		setContentPane(contentPane);
		setTitle("Encryptor");
		
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 15));
                
                //String outText = new String();
                //JTextField outText = new JTextField();
                
		
		JLabel lblEncryptionType = new JLabel("Encryption Type");
		lblEncryptionType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		String[] comboBoxInsert = {"AES", "Blowfish", "3DES", "Reverse Cipher", "Caesar Cipher", "Transposition Cipher"};
		JComboBox comboBox = new JComboBox(comboBoxInsert);
		
		
		JButton btnUploadFile = new JButton("Upload file");
		btnUploadFile.setPreferredSize(new Dimension(69, 23));
		btnUploadFile.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/text-file.png")));
		
		//Actionlistener for upload file btn
		btnUploadFile.addActionListener(e -> {
			
			//choose a file with jFile chooser
			JFileChooser fileChoose = new JFileChooser(".");
			//opens the file
			fileChoose.showOpenDialog(null);
			
			//need to add more code to actually read the file
			
		});//end uploadBtn action listener
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnEncrypt.addActionListener(e -> {
			
			 String dropSelection = new String (comboBox.getSelectedItem().toString());
				if (dropSelection.equals("AES")){
                                 //create key for AES, needs to be updated with key not hardcoded
                                 String aesKey = "abcdef1234567890abcdef1234567890";
                                 byte[] keyBytes = DatatypeConverter.parseHexBinary(aesKey);
                                 SecretKey key = new SecretKeySpec(keyBytes, "AES");
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                     encryption encrypter = new encryption (key);
                                     String inputEncrypted = encrypter.encrypt(inputText);
                                     //shows encrypted text in output
                                     printTextArea(inputEncrypted);
                                     //clears input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch                                                                        
                             }//end if
                             else if (dropSelection.equals("Blowfish")){
                                 //create key for Blowfish, needs to be updated with key not hardcoded
                                 String fishKey = "abcdef1234567890abcdef1234567890";
                                 byte[] keyBytes = DatatypeConverter.parseHexBinary(fishKey);
                                 SecretKey key = new SecretKeySpec(keyBytes, "Blowfish");
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                     Blowfish fishEncrypt = new Blowfish(key);
                                     String inputEncrypted = fishEncrypt.encrypt(inputText);
                                     //shows encrypted text in output box
                                     printTextArea(inputEncrypted);
                                     //clear input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch
                             }//end else if
                             else if (dropSelection.equals("3DES")){
                                 //create key for Blowfish, needs to be updated with key not hardcoded
                                 String threeDESKey = "This is a 3DES key Lets see it encrypt!!!";
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                     threeDES threeDES = new threeDES();
                                     String inputEncrypted = threeDES.threeDESControl(inputText, threeDESKey, "encrypt");
                                     //shows encrypted text in output box
                                     printTextArea(inputEncrypted);
                                     //clear input field
                                     textField.setText(null);
                                 }//end try
                                 //what kind of error are we trying to catch here??
                                 catch(Exception i) {
                                 	}//end catch
                                 }
				else 
				System.out.println("nope");
		});
		btnEncrypt.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/lock.png")));
		
		
		//action listener for decrypt btn
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setPreferredSize(new Dimension(69, 23));
		btnDecrypt.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnDecrypt.addActionListener(e -> {
			 String dropSelection = new String (comboBox.getSelectedItem().toString());
				if (dropSelection.equals("AES")){
                                 //create key for AES, needs to be updated with key not hardcoded
                                 String aesKey = "abcdef1234567890abcdef1234567890";
                                 byte[] keyBytes = DatatypeConverter.parseHexBinary(aesKey);
                                 SecretKey key = new SecretKeySpec(keyBytes, "AES");
                                 //get input text for decryption
                                 String inputText = new String (textField.getText());
                                 //decrypt input
                                 try {
                                     encryption encrypter = new encryption (key);
                                     String inputDecrypted = encrypter.decrypt(inputText);
                                     //shows encrypted text in output
                                     printTextArea(inputDecrypted);
                                     //clears input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch                                                                        
                             }//end if
                             else if (dropSelection.equals("Blowfish")){
                                 //create key for Blowfish, needs to be updated with key not hardcoded
                                 String fishKey = "abcdef1234567890abcdef1234567890";
                                 byte[] keyBytes = DatatypeConverter.parseHexBinary(fishKey);
                                 SecretKey key = new SecretKeySpec(keyBytes, "Blowfish");
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                     Blowfish fishEncrypt = new Blowfish(key);
                                     String inputDecrypted = fishEncrypt.decrypt(inputText);
                                     //shows encrypted text in output box
                                     printTextArea(inputDecrypted);
                                     //clear input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch
                             }//end else if
                            else if (dropSelection.equals("3DES")){
                                 //create key for Blowfish, needs to be updated with key not hardcoded
                                 String threeDESKey = "This is a 3DES key Lets see it encrypt!!!";
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                     threeDES threeDES = new threeDES();
                                     String inputEncrypted = threeDES.threeDESControl(inputText, threeDESKey, "decrypt");
                                     //shows encrypted text in output box
                                     printTextArea(inputEncrypted);
                                     //clear input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch
                             }//end else if
				else 
				System.out.println("nope");
			
		});//end action listener
		
		btnDecrypt.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/padlock-unlocked.png")));
		
		JButton btnEmail = new JButton("Email");
		btnEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEmail.setPreferredSize(new Dimension(69, 23));
		btnEmail.setMinimumSize(new Dimension(69, 23));
		btnEmail.setMaximumSize(new Dimension(69, 23));
		btnEmail.addActionListener(e -> {
		
			/*
			 * put action listener code here!!
			 */
			
		});
		btnEmail.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/close-envelope.png")));
		
        //scrollpane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		
		//action listener for encrypt to file btn
		JButton btnEncryptToFile = new JButton("Encrypt to file");
		btnEncryptToFile.setPreferredSize(new Dimension(69, 23));
		btnEncryptToFile.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnEncryptToFile.addActionListener(e -> {
			/*
			 * put action listener code here
			 */
			
					});
		btnEncryptToFile.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/lock.png")));
		
		JScrollPane InputScrollPane = new JScrollPane();
		InputScrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		InputScrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		InputScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		InputScrollPane.setPreferredSize(new Dimension(25, 25));
		
		JButton btnLoadKeyFrom = new JButton("Load Key from file");
		btnLoadKeyFrom.addActionListener(e -> {
			
			//opens a jpane for user to pick the file that contains the key
			JFileChooser keyloader = new JFileChooser(".");
			keyloader.showOpenDialog(null);
			/*
			 * add code for load key btn
			 */
			
		});//end action listener for load key btn
		
		btnLoadKeyFrom.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/text-file.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblOutput, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
									.addGap(761))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(InputScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblInput, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
											.addComponent(lblEncryptionType)
											.addGap(18)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
									.addGap(19)))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 790, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnDecrypt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnEncrypt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnEncryptToFile, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnEmail, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnUploadFile, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
									.addComponent(btnLoadKeyFrom, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(23))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEncryptionType)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(18))
						.addComponent(lblInput, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(InputScrollPane, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(65)
					.addComponent(btnUploadFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEncryptToFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEncrypt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDecrypt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLoadKeyFrom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOutput)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		textField = new JTextField();
		InputScrollPane.setViewportView(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(new Color(255, 255, 255));
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.WHITE);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
        textArea.setEditable(false);               
	}//end EncryptionGUI constructor        
	
    //method prints output to textArea    
    public void printTextArea(String text) {
    textArea.setText(text);
    }//end printTextArea                                     
}//end EncryptionGUI class