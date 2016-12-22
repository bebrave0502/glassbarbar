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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
		
		setSize(390, 500);
		setLocation(500, 200);
		setResizable(false);
		
		makeSchedulePanel(listpanel);
		add(listpanel);
		
		setVisible(true);
	}

	public void makeSchedulePanel(JPanel listpanel) {
		listModel = new DefaultListModel();
		scheduleList = new JList(listModel);
		scheduleList.setPreferredSize(new Dimension(300, 400));
		listScroll = new JScrollPane(scheduleList);
		listScroll.setPreferredSize(new Dimension(260, 370));
		selectDate = new JPanel();
		month = new JComboBox();
		
		for (int i = 1; i < 13; i++) {
			month.addItem(String.valueOf(i));
		}

		day = new JComboBox();
		for (int i = 1; i < 32; i++) {
			day.addItem(String.valueOf(i));

		}

		content = new JTextField(20);

		selectDate.add(month);
		selectDate.add(day);

		newSchedule = new JButton("NEW");
		newSchedule.setBounds(10, 80, 50, 25);

		deleteSchedule = new JButton("DELETE");
		deleteSchedule.setBounds(10, 80, 50, 25);

		listpanel.add(listScroll);
		listpanel.add(selectDate);
		listpanel.add(content);
		listpanel.add(newSchedule);
		listpanel.add(deleteSchedule);
		
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
		
		if(checkData()){

		schedule = scheduleObjects.elementAt(n);
		scheduleObjects.remove(n);
		listModel.removeElementAt(n);
		}

	}
	
	Boolean checkData() {
		int n = scheduleList.getSelectedIndex();
		
		if(n<0) {
			JOptionPane.showMessageDialog(null, "삭제할 데이터가 없습니다.");
			return false;
		}
		else
			return true;
			
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
		} catch (IOException ie) {
		} catch (ClassNotFoundException ce) {
		}
	}

	void writeScheduleDB() {
		try {

			File f1 = new File("schedule");

			f1.createNewFile();

			FileOutputStream fos = new FileOutputStream(f1);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(scheduleObjects);
			oos.close();

		} catch (IOException ie) {
		}
	}
	
	void reset() {
		content.setText("");
		month.setSelectedItem("1");
		day.setSelectedItem("1");
	}
}