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
import javax.swing.JOptionPane;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
*@author Pedro Marchante
*/

public class EncryptionGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea textField;
    private JTextArea textArea;
    private String key = new String();
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
			JFileChooser fileChoose = new JFileChooser(".\\Files\\");
			//opens the file
			fileChoose.showOpenDialog(null);
			//get file name
			String fileName = fileChoose.getSelectedFile().getName();
                        //get file
                        File file = fileChoose.getSelectedFile();
			//load file using loadFromFile method
                        String fileText = loadFromFile(file, fileName);
                        textField.setText(fileText);
		});//end uploadBtn action listener
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnEncrypt.addActionListener(e -> {
			//get dropdown selection
			String dropSelection = new String (comboBox.getSelectedItem().toString());                         
				if (dropSelection.equals("AES")){
                                    if (!key.equals("")){                                        
                                        //get secret key
                                        SecretKey skey = getKey("AES", key);
                                        //get input text for encryption
                                        String inputText = new String (textField.getText());
                                        //create encryption
                                        try {
                                            encryption encrypter = new encryption (skey);
                                            String inputEncrypted = encrypter.encrypt(inputText);
                                            //shows encrypted text in output
                                            printTextArea(inputEncrypted);
                                            //clears input field
                                            textField.setText(null);
                                        }
                                        catch (Exception ex) {
                                            //handle exception
                                        }//end catch
                                    }//end inner if
                                    //if key is null
                                    else {
                                        JFrame frame = new JFrame ();
                                        JOptionPane.showMessageDialog(frame, "Please select a key");                                        
                                    }    
                             }//end outer if
                             else if (dropSelection.equals("Blowfish")){
                                 if (!key.equals("")){
                                    //get key
                                    SecretKey skey = getKey("Blowfish", key);
                                    //get input text for encryption
                                    String inputText = new String (textField.getText());
                                    //create encryption
                                    try {
                                        Blowfish fishEncrypt = new Blowfish(skey);
                                        String inputEncrypted = fishEncrypt.encrypt(inputText);
                                        //shows encrypted text in output box
                                        printTextArea(inputEncrypted);
                                        //clear input field
                                        textField.setText(null);
                                    }
                                    catch (Exception ex) {
                                        //handle exception
                                    }//end catch
                                    }//end if
                                 else {
                                        JFrame frame = new JFrame ();
                                        JOptionPane.showMessageDialog(frame, "Please select a key");                                        
                                    }
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
                             else if (dropSelection.equals("Reverse Cipher")){
                                 
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                	 revers_Cipher revers = new revers_Cipher();
                                     String inputEncrypted = revers.revers_Cipher(inputText);
                                     //shows encrypted text in output box
                                     printTextArea(inputEncrypted);
                                     //clear input field
                                     textField.setText(null);
                                 }//end try
                                 //what kind of error are we trying to catch here??
                                 catch(Exception i) {
                                 	}//end catch
                                 }
                             else if (dropSelection.equals("Caesar Cipher")){
                                 
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 String key = "5";
                                 //create encryption
                                 try {
                                	 caesar_Cipher revers = new caesar_Cipher();
                                     String inputEncrypted = revers.cipher(inputText, key, "encrypt" );
                                     //shows encrypted text in output box
                                     printTextArea(inputEncrypted);
                                     //clear input field
                                     textField.setText(null);
                                 }//end try
                                 //what kind of error are we trying to catch here??
                                 catch(Exception i) {
                                 	}//end catch
                                 }
                             else if (dropSelection.equals("Transposition Cipher")){
                                 
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 String key = "5";
                                 //create encryption
                                 try {
                                	 transposition_cipher transp = new transposition_cipher();
                                    String inputEncrypted = transp.transpositionControl(inputText, key, "encrypt");
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
                                    if (!key.equals("")){
                                 //get key
                                 SecretKey skey = getKey("AES", key);
                                 //get input text for decryption
                                 String inputText = new String (textField.getText());
                                 //decrypt input
                                 try {
                                     encryption encrypter = new encryption (skey);
                                     String inputDecrypted = encrypter.decrypt(inputText);
                                     //shows encrypted text in output
                                     printTextArea(inputDecrypted);
                                     //clears input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch 
                                    }//end inner if
                                    else {
                                        JFrame frame = new JFrame ();
                                        JOptionPane.showMessageDialog(frame, "Please select a key");                                        
                                    }
                             }//end if
                             else if (dropSelection.equals("Blowfish")){
                                 if (!key.equals("")){
                                 //get key
                                    SecretKey skey = getKey("Blowfish", key);
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                     Blowfish fishEncrypt = new Blowfish(skey);
                                     String inputDecrypted = fishEncrypt.decrypt(inputText);
                                     //shows encrypted text in output box
                                     printTextArea(inputDecrypted);
                                     //clear input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch
                                 }
                                 else {
                                        JFrame frame = new JFrame ();
                                        JOptionPane.showMessageDialog(frame, "Please select a key");                                        
                                    }
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
                             else if (dropSelection.equals("Reverse Cipher")){
                                
                                //get input text for encryption
                                String inputText = new String (textField.getText());
                                //create encryption
                                try {
                               	 revers_Cipher revers = new revers_Cipher();
                                    String inputEncrypted = revers.revers_Cipher(inputText);
                                    //shows encrypted text in output box
                                    printTextArea(inputEncrypted);
                                    //clear input field
                                    textField.setText(null);
                                }//end try
                                //what kind of error are we trying to catch here??
                                catch(Exception i) {
                                	}//end catch
                                }
                            else if (dropSelection.equals("Caesar Cipher")){
                                
                                //get input text for encryption
                                String inputText = new String (textField.getText());
                                String key = "5";
                                //create encryption
                                try {
                               	 caesar_Cipher revers = new caesar_Cipher();
                                    String inputEncrypted = revers.cipher(inputText, key, "decrypt" );
                                    //shows encrypted text in output box
                                    printTextArea(inputEncrypted);
                                    //clear input field
                                    textField.setText(null);
                                }//end try
                                //what kind of error are we trying to catch here??
                                catch(Exception i) {
                                	}//end catch
                                }else if (dropSelection.equals("Transposition Cipher")){
                                    
                                    //get input text for encryption
                                    String inputText = new String (textField.getText());
                                    String key = "5";
                                    //create encryption
                                    try {
                                   	 transposition_cipher transp = new transposition_cipher();
                                        String inputEncrypted = transp.transpositionControl(inputText, key, "decrypt");
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
			
		});//end action listener
		
		btnDecrypt.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/padlock-unlocked.png")));
		
		JButton btnEmail = new JButton("Email");
		btnEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEmail.setPreferredSize(new Dimension(69, 23));
		btnEmail.setMinimumSize(new Dimension(69, 23));
		btnEmail.setMaximumSize(new Dimension(69, 23));
		btnEmail.addActionListener(e -> {
		
			try {
				String secretMessage = new String();
				// store text from output text area
				secretMessage = textArea.getText();
				if (secretMessage.length() != 0) {

				  // Pop up window asking for destination email
				  final JFrame frame = new JFrame();
				  final String message = "Please enter the destination email address";
				  String emailRecipient = null;
				  emailRecipient = JOptionPane.showInputDialog(frame, message);

				  if (emailRecipient != null && emailRecipient.length() != 0) {
				    // Start Token validation
				    TokenValidator oauth = new TokenValidator();
				    // Renew token if expired
				    oauth.renewToken();
				    // Send email
				    SecureEmail.emailMessage(secretMessage, emailRecipient,
					oauth.getAccessToken());
				  }
				}
			      } catch(Exception emailEx) {
				System.out.println(emailEx.getMessage());
			      }
		}); // End of Email action listener

		btnEmail.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/close-envelope.png")));
		
        //scrollpane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		
		//action listener for encrypt to file btn
		JButton btnEncryptToFile = new JButton("Encrypt to file");
		btnEncryptToFile.setPreferredSize(new Dimension(69, 23));
		btnEncryptToFile.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnEncryptToFile.addActionListener(e -> {
			//get dropdown box selection
                        String dropSelection = new String (comboBox.getSelectedItem().toString());
                        //popup for user to input file name
                        JFrame frame = new JFrame();
                        String message = "Please enter a file name";
                        String nameOfFile = JOptionPane.showInputDialog(frame, message);
                        if (nameOfFile == null) {
                          // User clicked cancel
                        }
                        //get input, encrypt, write to .txt file
                        else {
                            if (dropSelection.equals("AES")){
                                if (!key.equals("")){
                                 //get key
                                 SecretKey skey = getKey("AES", key);
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                     encryption encrypter = new encryption (skey);
                                     String inputEncrypted = encrypter.encrypt(inputText);
                                     //use saveToFile method to write text to file
                                     saveToFile(inputEncrypted, nameOfFile);
                                     //clears input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch
                                }//end inside if
                                else {
                                        JFrame noKeyFrame = new JFrame ();
                                        JOptionPane.showMessageDialog(noKeyFrame, "Please select a key");                                        
                                    }
                            }//end if
                            else if (dropSelection.equals("Blowfish")){
                                if (!key.equals("")){
                                 //get key
                                 SecretKey skey = getKey("Blowfish", key);
                                 //get input text for encryption
                                 String inputText = new String (textField.getText());
                                 //create encryption
                                 try {
                                     encryption encrypter = new encryption (skey);
                                     String inputEncrypted = encrypter.encrypt(inputText);
                                     //use saveToFile method to write text to file
                                     saveToFile(inputEncrypted, nameOfFile);
                                     //clears input field
                                     textField.setText(null);
                                 }
                                 catch (Exception ex) {
                                     //handle exception
                                 }//end catch
                                }//end inside if
                                else {
                                        JFrame noKeyFrame = new JFrame ();
                                        JOptionPane.showMessageDialog(noKeyFrame, "Please select a key");                                        
                                    }
                            }//end if
                            
                        }//end else
			
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
			JFileChooser keyLoader = new JFileChooser(".\\Keys\\");
			keyLoader.showOpenDialog(null);			
			//get file name
			String fileName = keyLoader.getSelectedFile().getName();
                        //get file
                        File file = keyLoader.getSelectedFile();
			//load file using loadFromFile method
                        key = loadFromFile(file, fileName);
                         
			 
			
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
		
		textField = new JTextArea();
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
    
    //method encrypts to file
    public void saveToFile (String contents, String fileName) {
        //String contentToFile = new String(contents);
        BufferedWriter writer = null;
        File file = new File(".\\Files\\", fileName+".txt");
        
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(contents);
            //writer.newLine();
        }
        // print error message if there is one
        catch (IOException io) {
            System.out.println("File IO Exception" + io.getMessage());
        }
        //close the file
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }
            //print error message if there is one
            catch (IOException io) {
                System.out.println("Issue closing the File." + io.getMessage());
            }
        }
    }
    
    //method loads from file
    public String loadFromFile (File file, String fileName){
        String fileText = new String();
        //File file = new File(".\\Files\\", fileName+".txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine(); 
            StringBuilder sb = new StringBuilder(); 
            while(line != null){ 
                sb.append(line); 
                line = reader.readLine(); 
            }//end while
            fileText = sb.toString();
        }//end try
        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
        return fileText;
    }
    
    public SecretKey getKey (String type, String key){
        //create key for AES, needs to be updated with key not hardcoded
        //String aesKey = "abcdef1234567890abcdef1234567890";
        byte[] keyBytes = DatatypeConverter.parseHexBinary(key);
        SecretKey skey = new SecretKeySpec(keyBytes, type);
        return skey;
    }
    
}//end EncryptionGUI class
