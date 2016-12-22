import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
class MenuSelection extends JFrame {	
	private Main home;	
	private JButton btnlogout;
	private JButton btnedit;
	private JButton btnschedule;
	private JButton btnaddress;
	private Account admin;
	private AccountModification editIdandPassword;
    
	public MenuSelection() {
		setTitle("MySystem");
		setSize(380, 180);
        setResizable(false);
        setLocation(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //panel
        JPanel accountPanel = new JPanel();
        makeAccountPanel(accountPanel);
        
        //add
        add(accountPanel);
        
        //visible
        setVisible(true);
    }
	
	public void makeAccountPanel(JPanel accountPanel) {
		accountPanel.setLayout(null);		
		btnlogout = new JButton("LOGOUT");
        btnlogout.setBounds(60, 40, 100, 25);
        accountPanel.add(btnlogout);
        btnlogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
            	isLogout();                      
            }
        });
       
        btnedit = new JButton("EDIT");
        btnedit.setBounds(210, 40, 100, 25);
        accountPanel.add(btnedit);
        btnedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
            	changeidandpassword();
            }
        });
        
        btnschedule = new JButton("SCHEDULE");
        btnschedule.setBounds(60, 80, 100, 25);
        accountPanel.add(btnschedule);
        btnschedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                home.showScheduleList();
            }
        });
        
        btnaddress = new JButton("ADDRESS");
        btnaddress.setBounds(210, 80, 100, 25);
        accountPanel.add(btnaddress);
        btnaddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	home.showAddressList();              
            }
        });		
	}
	
	public void isLogout() {
		home.showlogin();
	}
	
	public void changeidandpassword() {
		editIdandPassword = new AccountModification(this,"change");
		this.editIdandPassword.setAdminObject(admin);					
	}	
	 
    public void setMain(Main home) {
        this.home = home;
    }
	
    public void setAdminObject(Account admin) {
    	this.admin = admin;
    }	
}