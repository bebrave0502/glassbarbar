import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AddressList extends JDialog {
	private Vector<Address> addressObjects;
	private JLabel nameLabel, phoneLabel;
	private JPanel listpanel;
	private DefaultListModel listModel;
	private JList addressList;
	private JScrollPane listScroll;
	private JButton newAddress, deleteAddress;
	private JTextField name, phoneNumber;

	public AddressList(JFrame frame, String title) {		
		super(frame, title);
		
		addressObjects = new Vector<Address>();
		listpanel = new JPanel();
		
		setSize(390, 500);
		setLocation(500, 200);
		setResizable(false);
		
		makeAddressPanel(listpanel);
		add(listpanel);
		
		setVisible(true);
	}

	public void makeAddressPanel(JPanel listpanel) {
		listModel = new DefaultListModel();
		addressList = new JList(listModel);
		addressList.setPreferredSize(new Dimension(390, 400));
		listScroll = new JScrollPane(addressList);
		listScroll.setPreferredSize(new Dimension(380, 370));

		nameLabel = new JLabel("Name");
		phoneLabel = new JLabel("Phone");
		
		name = new JTextField(8);
		phoneNumber = new JTextField(12);

		
		newAddress = new JButton("NEW");
		newAddress.setBounds(10, 80, 50, 25);

		deleteAddress = new JButton("DELETE");
		deleteAddress.setBounds(10, 80, 50, 25);

		listpanel.add(listScroll);
		listpanel.add(nameLabel);
		listpanel.add(name);
		listpanel.add(phoneLabel);
		listpanel.add(phoneNumber);
		listpanel.add(newAddress);
		listpanel.add(deleteAddress);

		readAddressDB();

		newAddress.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addAddress();
				writeAddressDB();
			}
		});

		deleteAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAddress();
				writeAddressDB();
			}
		});

	}

	void addAddress() {
		Address address;
		address = new Address(name.getText(), phoneNumber.getText());

		addressObjects.addElement(address);
		listModel.addElement(address);

		reset();

	}

	void deleteAddress() {
		int n = addressList.getSelectedIndex();
		Address address;

		address = addressObjects.elementAt(n);
		addressObjects.remove(n);
		listModel.removeElementAt(n);

	}

	void readAddressDB() {
		try {

			Address address;
			File f1 = new File("address");

			if (f1.exists()) {
				FileInputStream fis = new FileInputStream(f1);
				ObjectInputStream ois = new ObjectInputStream(fis);

				addressObjects = (Vector) ois.readObject();

				for (int i = 0; i < addressObjects.size(); i++) {
					address = addressObjects.get(i);
					listModel.addElement(address);
				}

				ois.close();

			}
		} catch (IOException ie) {
		} catch (ClassNotFoundException ce) {
		}
	}

	void writeAddressDB() {
		try {

			File f1 = new File("address");

			f1.createNewFile();

			FileOutputStream fos = new FileOutputStream(f1);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(addressObjects);
			oos.close();

		} catch (IOException ie) {
		}
	}

	void reset() {
		name.setText("");
		phoneNumber.setText("");
	}
}