package edu.sc.csce747.MeetingPlanner;


import java.util.ArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CalendarTest {
	// Add test methods here. 
	// You are not required to write tests for all classes.
	private Calendar calendar;
	@Before
	public void setUp() {
    	calendar = new Calendar();
	}
	@Test
	public void testAddMeeting_holiday() {
		// Create Midsommar holiday
		// Add to calendar object.
		try {
			Meeting midsommar = new Meeting(6, 26, "Midsommar");
			calendar.addMeeting(midsommar);
			// Verify that it was added.
			Boolean added = calendar.isBusy(6, 26, 0, 23);
			assertTrue("Midsommar should be marked as busy on the calendar",added);
		} catch(TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	@Test
	public testAddMeeting_conflict(){
		try {
			Meeting meeting1 = new Meeting(5,10,10,12, "Team Sync");
			Meeting meeting2 = new Meeting(5,10,11,13, "Client call");
			calendar.addMeeting(meeting1);
			calendar.addMeeting(meeting2);
			fail("Should throw TimeConflictException due to overlap");

		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Overlap with another item"));
		}
	}
	@Test
	public void testClearSchedule(){
		try {
			Meeting meeting = new Meeting(7,15,9,10,null,new Room("UB104"), "Morning Standup");
			calendar.addMeeting(meeting);
			calendar.clearSchedule(7,15);
			boolean busy = calendar.isBusy(7,15,9,10);
			assertFalse("Day should be cleared", busy);
		} catch (TimeConflictException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}
	@Test
	public void testPrintAgenda_day(){
		try {
			Meeting meeting = new Meeting(8,20,14,15,new ArrayList<Person>(), new Room("UB104"), "Demo Presentation");
			calendar.addMeeting(meeting);
			String agenda = calendar.printAgenda(8,20);
			assertTrue("Agenda should contain meeting description", agenda.contains("Demo Presentation"));

		} catch (TimeConflictException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}
	@Test
	public void testRemoveMeeting(){
		try {
			Meeting meeting = new Meeting(9,5,10,11,new ArrayList<Person>(), new Room("UB104"),"Interview");
			calendar.addMeeting(meeting);
			calendar.removeMeeting(9,5,0);
			boolean busy = calendar.isBusy(9,5,10,11);
			assertFalse("Meeting should be removed", busy);
		} catch (TimeConflictException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}
	@Test 
	public void testInvalidDate(){
		try {
			Meeting meeting = new Meeting(2,29, 10,11,new ArrayList<Person>(), new Room("UB104"),"Invalid Date Meeting");
			calendar.addMeeting(meeting);
			fail("Should throw TimeConflictException for invalid date");
		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Day does not exist"));
		}
	}
	
}
