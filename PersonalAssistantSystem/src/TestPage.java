import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestPage {

	@Test
	public void testAddSchdule() {
		Main home = new Main();
		MenuSelection menuSelection = new MenuSelection();		
		ScheduleList scheduleList = new ScheduleList(home.menuSelection, "");
		int lastSize = scheduleList.scheduleObjects.size();
		scheduleList.addSchedule();
		assertEquals(lastSize+1, scheduleList.scheduleObjects.size());
	}
	
	@Test
	public void testScheduleList() {
		Main home = new Main();
		MenuSelection menuSelection = new MenuSelection();
		ScheduleList scheduleList = new ScheduleList(home.menuSelection, "");
		assertTrue(scheduleList.isVisible());
	}
	
	@Test
	public void testReset() {
		Main home = new Main();
		MenuSelection menuSelection = new MenuSelection();
		ScheduleList scheduleList = new ScheduleList(home.menuSelection, "");
		scheduleList.reset();
		assertEquals(scheduleList.day.getSelectedItem(), "1");
		assertEquals(scheduleList.month.getSelectedItem(), "1");
	}
	
	@Test
	public void testEdit() {
		Account account = new Account("admin", "1234");
		account.edit("km", "1111");
		String test = account.id;
		assertEquals("km", test);
	}
	
	@Test
	public void testToString() {
		Schedule myschedule = new Schedule("2", "9", "MY BIRTHDAY");
		String test = myschedule.toString();
		assertEquals("2/9: MY BIRTHDAY\n", test);
	}
	
	@Test
	public void testGetPhoneNumber() {
		Address address = new Address("jinil", "010-1234-5678");
		String test = address.getPhoneNumber();
		assertEquals("010-1234-5678", test);
	}
}