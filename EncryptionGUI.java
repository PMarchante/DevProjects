//package EncyptionProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Button;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/*
*@author Pedro Marchante
*
*/
public class EncryptionGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
        private JTextArea textArea;
        private String outText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptionGUI frame = new EncryptionGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EncryptionGUI() {
		
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(EncryptionGUI.class.getResource("/Resources/lock.png")));
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
		setContentPane(contentPane);
		setTitle("Encryptor");
		
		
		JLabel lblInput = new JLabel("Input");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(new Color(255, 255, 255));
		textField.setColumns(10);
		
		JLabel lblOutput = new JLabel("Output");
                
                //String outText = new String();
                //JTextField outText = new JTextField();
                
		
		JLabel lblEncryptionType = new JLabel("Encryption Type");
		String[] comboBoxInsert = {"AES", "Blowfish", "3DES", "Reverse Cipher", "Caesar Cipher", "Transposition Cipher"};
		JComboBox comboBox = new JComboBox(comboBoxInsert);
		
		//actionlistener for upload file btn
		JButton btnUploadFile = new JButton("Upload file");
		btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * upload file code goes here
				 */
			}
		});
		btnUploadFile.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/text-file.png")));
		
		JButton btnEncrypt = new JButton("Encrypt");
                String inputEncrypted = new String();
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
                                    }				
				else {
                                    System.out.println("nope");
                                }//end else
			}//end event handler
		});
		btnEncrypt.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/lock.png")));
		
		
		//action listener for decrypt btn
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				else {
                                    System.out.println("nope");
                                }//end else
			}
		});
		
		btnDecrypt.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/padlock-unlocked.png")));
		
		JButton btnEmail = new JButton("Email");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * email btn code goes here
				 */
			}
		});
		btnEmail.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/close-envelope.png")));
		
                //scrollpane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		
		//action listener for encrypt to file btn
		JButton btnEncryptToFile = new JButton("Encrypt to file");
		btnEncryptToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * encrypt to file code goes here
				 */
			}
		});
		btnEncryptToFile.setIcon(new ImageIcon(EncryptionGUI.class.getResource("/Resources/lock.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(20)
									.addComponent(lblInput)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblEncryptionType))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblOutput)))
							.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnDecrypt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnEncrypt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox, Alignment.TRAILING, 0, 161, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(407)
							.addComponent(btnEncryptToFile, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
						.addComponent(btnUploadFile, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
					.addGap(432))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInput)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEncryptionType)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnUploadFile)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEmail)
					.addGap(115)
					.addComponent(btnEncryptToFile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEncrypt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblOutput)
						.addComponent(btnDecrypt))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(468))
		);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.WHITE);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
                textArea.setEditable(false);               
                
	}
    //method prints output to textArea    
    public void printTextArea(String text) {
    textArea.setText(text);
    }                                     
}
