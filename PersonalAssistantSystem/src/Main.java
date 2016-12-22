public class Main {	
	AccountManagement accountmanagement;
	MenuSelection menuSelection;
	Account admin;
	AddressList addresslist;
	ScheduleList schedulelist; 
	
	public static void main(String args[]) {
		Main home = new Main();	
		home.accountmanagement = new AccountManagement();
		home.accountmanagement.setMain(home);				
	}
	
	public void showMenuSelection() {
		admin = accountmanagement.getAdminObject();
		accountmanagement.setVisible(false);
		this.menuSelection = new MenuSelection();
		this.menuSelection.setMain(this);
		this.menuSelection.setAdminObject(admin);		
	}
	
    public void showlogin() {
        menuSelection.dispose();
        this.accountmanagement = new AccountManagement();
        this.accountmanagement.setMain(this);        
    }
    
    public void showAddressList() {
    	addresslist = new AddressList(this.menuSelection, "ADDRESS");
    }
    
    public void showScheduleList() {
    	schedulelist = new ScheduleList(this.menuSelection, "SCHEDULE");
    }
}