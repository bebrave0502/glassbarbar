import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class AccountModification extends JDialog {	
	private JButton btnOk;
    private JButton btnInit;
    private JPasswordField ChangePassword;
    private JTextField ChangeText;
    private Account admin; 
    
	public AccountModification(JFrame frame, String title) {
		super(frame, title);
		setSize(280,150);
		setLocation(550, 300);
		setResizable(false);
		JPanel changePanel = new JPanel();
		makechangePanel(changePanel);
		add(changePanel);
		setVisible(true);
	}
	
	public void makechangePanel(JPanel changePanel) {		
		changePanel.setLayout(null);     
        JLabel userLabel = new JLabel("ChangeId");
        userLabel.setBounds(10, 10, 80, 25);
        changePanel.add(userLabel);
       
        JLabel passLabel = new JLabel("ChangePw");
        passLabel.setBounds(10, 40, 80, 25);
        changePanel.add(passLabel);
       
        ChangeText = new JTextField(20);
        ChangeText.setBounds(100, 10, 160, 25);
        changePanel.add(ChangeText);
       
        ChangePassword = new JPasswordField(20);
        ChangePassword.setBounds(100, 40, 160, 25);
        changePanel.add(ChangePassword);
        
        btnInit = new JButton("Reset");
        btnInit.setBounds(10, 80, 100, 25);
        changePanel.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeText.setText("");
                ChangePassword.setText("");
            }
        });
       
        btnOk = new JButton("OK");
        btnOk.setBounds(160, 80, 100, 25);
        changePanel.add(btnOk);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String newId = ChangeText.getText();
            	String newPassword = new String(ChangePassword.getPassword());
            	admin.edit(newId, newPassword);
            	try{	     	
            		File f1 = new File("LoginDB");         			
            		FileOutputStream fos = new FileOutputStream(f1);
            		ObjectOutputStream oos = new ObjectOutputStream(fos);
            		oos.writeObject(admin);
            		oos.close();      
            	}
            	catch(IOException ie) {}
            	
            	JOptionPane.showMessageDialog(null, "Success");
            	setVisible(false);
            }
        });	
	}
	
	public void setAdminObject(Account admin) {
		this.admin = admin;
	}
}