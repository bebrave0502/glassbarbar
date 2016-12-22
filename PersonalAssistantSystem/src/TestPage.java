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

}
