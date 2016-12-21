//
public class Main {
	
	AccountManagement accountmanagement;
	AccountFrame accountframe;
	Account admin;
	
	AddressFrame addressframe;
	
	public static void main(String args[])
	{
		Main home = new Main();
	
		home.accountmanagement = new AccountManagement();
		home.accountmanagement.setMain(home);
				
	}
	
	public void showAccountFrame() {
		//accountmanagement.dispose();
		admin = accountmanagement.getAdminObject();
		accountmanagement.setVisible(false);
		this.accountframe = new AccountFrame();
		this.accountframe.setMain(this);
		this.accountframe.setAdminObject(admin);
		
	}
	
    public void showlogin(){
        accountframe.dispose();

        this.accountmanagement = new AccountManagement();
        this.accountmanagement.setMain(this);
        
    }
    
    public void showAddressFrame() {
    	addressframe = new AddressFrame(this.accountframe,"ADDRESS");
    }
	
	
}
