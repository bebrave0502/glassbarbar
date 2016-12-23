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
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ScheduleList extends JDialog {
	public Vector<Schedule> scheduleObjects;
	private JPanel listpanel, selectDate;
	private DefaultListModel listModel;
	private JList scheduleList;
	private JScrollPane listScroll;
	private JButton newSchedule, deleteSchedule;
	private JTextField content;
	public JComboBox month, day;

	public ScheduleList(JFrame frame, String title) {	
		super(frame, title);
		
		scheduleObjects = new Vector<Schedule>();
		listpanel = new JPanel();
		
		setSize(390, 400);
		setLocation(500, 200);
		setResizable(false);
		
		makeSchedulePanel(listpanel);
		add(listpanel);
		
		setVisible(true);
	}

	public void makeSchedulePanel(JPanel listpanel) {
		listpanel.setLayout(new BoxLayout(listpanel, BoxLayout.Y_AXIS));		
	
		listModel = new DefaultListModel();
		scheduleList = new JList(listModel);
		scheduleList.setPreferredSize(new Dimension(390, 400));
		listScroll = new JScrollPane(scheduleList);
		listScroll.setPreferredSize(new Dimension(380, 370));
		
		selectDate = new JPanel();
		month = new JComboBox();		
		for (int i = 1; i < 13; i++) {
			month.addItem(String.valueOf(i));
		}
		
		day = new JComboBox();
		for (int i = 1; i < 32; i++) {
			day.addItem(String.valueOf(i));
		}		
		selectDate.add(month);
		selectDate.add(day);
		
		content = new JTextField(28);
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.add(content);
		
		newSchedule = new JButton("NEW");
		deleteSchedule = new JButton("DELETE");				
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(newSchedule);
		buttonPanel.add(deleteSchedule);
		
		listpanel.add(listScroll);
		listpanel.add(selectDate);
		listpanel.add(textFieldPanel);
		listpanel.add(buttonPanel);
		
		readScheduleDB();
		
		newSchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addSchedule();
				writeScheduleDB();
			}
		});
		
		deleteSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSchedule();
				writeScheduleDB();
			}
		});
	}

	void addSchedule() {
		Schedule schedule;
		schedule = new Schedule(month.getSelectedItem().toString(), day.getSelectedItem().toString(),
				content.getText());

		scheduleObjects.addElement(schedule);
		listModel.addElement(schedule);

		reset();
	}

	void deleteSchedule() {
		int n = scheduleList.getSelectedIndex();
		Schedule schedule;

		schedule = scheduleObjects.elementAt(n);
		scheduleObjects.remove(n);
		listModel.removeElementAt(n);
	}

	void readScheduleDB() {
		try {
			Schedule schedule;
			File f1 = new File("schedule");

			if (f1.exists()) {
				FileInputStream fis = new FileInputStream(f1);
				ObjectInputStream ois = new ObjectInputStream(fis);

				scheduleObjects = (Vector) ois.readObject();

				for (int i = 0; i < scheduleObjects.size(); i++) {
					schedule = scheduleObjects.get(i);
					listModel.addElement(schedule);
				}
				ois.close();
			}
		} 
		catch (IOException ie) {} 
		catch (ClassNotFoundException ce) {}
	}

	void writeScheduleDB() {
		try {
			File f1 = new File("schedule");

			f1.createNewFile();

			FileOutputStream fos = new FileOutputStream(f1);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(scheduleObjects);
			oos.close();
		} catch (IOException ie) {}
	}
	
	void reset() {
		content.setText("");
		month.setSelectedItem("1");
		day.setSelectedItem("1");
	}
}