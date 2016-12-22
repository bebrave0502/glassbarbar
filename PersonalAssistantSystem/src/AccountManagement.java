import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class AccountManagement extends JFrame {	
	private Main home;
	private JTextField userText;
	private JPasswordField passwordText;	
	private boolean loginfin = false;
	private Account admin; 
	
    public AccountManagement() {
    	setTitle("Login");
        setSize(280, 150);
        setResizable(false);
        setLocation(550, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel loginPanel = new JPanel();
        makeLoginPanel(loginPanel);
        
        add(loginPanel);
        
        setVisible(true);
    }
    
    public void makeLoginPanel(JPanel loginPanel) {
    	loginPanel.setLayout(null);
    	
    	JLabel userLabel = new JLabel("USER");
    	userLabel.setBounds(10, 10, 80, 25);
    	loginPanel.add(userLabel);
    	
    	JLabel passwordLabel = new JLabel("PASSWORD");
    	passwordLabel.setBounds(10, 40, 80, 25);
    	loginPanel.add(passwordLabel);
    	
    	userText = new JTextField(20);
    	userText.setBounds(100, 10, 160, 25);
    	loginPanel.add(userText);
    	
    	passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        loginPanel.add(passwordText);
        
        JButton resetButton = new JButton("RESET");
        resetButton.setBounds(10, 80, 100, 25);
        loginPanel.add(resetButton);
        resetButton.addActionListener(new ActionListener() {       	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		userText.setText("");
        		passwordText.setText("");
        	}       	
        });
        
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(160, 80, 100, 25);
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		checkLogin();
        	}
        });    	
    }
    
    public void checkLogin() {
    	try{
    		File f1 = new File("LoginDB");
    		if(!f1.exists()) {
    			f1.createNewFile();
    			Account admin = new Account("admin", "1234");
    			FileOutputStream fos = new FileOutputStream(f1);
    			ObjectOutputStream oos = new ObjectOutputStream(fos);
    			oos.writeObject(admin);
    			oos.close();    			
    		}
    		FileInputStream fis = new FileInputStream(f1);    		
    		ObjectInputStream ois = new ObjectInputStream(fis);   		
    		admin = (Account) ois.readObject();    	
    		ois.close();    		
    		String adminId = admin.getId();
    		String adminPassword = admin.getPassword();    		
    		String userId = userText.getText();
    		String userPassword = new String(passwordText.getPassword());   		
    		
    		if(userId.equals(adminId) && userPassword.equals(adminPassword)) {
    			JOptionPane.showMessageDialog(null, "Success");
    			loginfin = true;
    			if(isLogin()) {
    				home.showMenuSelection();
    			}	
    		}else{
    			JOptionPane.showMessageDialog(null, "Failed");
    		}
    	}
    	catch(IOException ie) {}
		catch(ClassNotFoundException ce) {}
    }
    
    public boolean isLogin() {
    	return loginfin;
    }
    
    public void setMain(Main home) {
        this.home = home;
    }
    
    public Account getAdminObject() {
    	return admin;
    }    
}