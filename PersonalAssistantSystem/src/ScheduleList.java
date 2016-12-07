import java.awt.Dimension;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ScheduleList extends JFrame {
	private Vector<Schedule> scheduleObjects;

	private JPanel listpanel, selectDate;
	private DefaultListModel listModel;
	private JList scheduleList;
	private JScrollPane listScroll;
	private JButton newSchedule, deleteSchedule;
	private JTextField content;
	private JComboBox month, day;

	public ScheduleList() {
		scheduleObjects = new Vector<Schedule>();
		listpanel = new JPanel();

		setSize(300, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

		deleteSchedule = new JButton("DELETEE");
		deleteSchedule.setBounds(10, 80, 50, 25);

		listpanel.add(listScroll);
		listpanel.add(selectDate);
		listpanel.add(content);
		listpanel.add(newSchedule);
		listpanel.add(deleteSchedule);

	}
	
	public static void main(String[] args) {

		new ScheduleList();

	}
	
}
