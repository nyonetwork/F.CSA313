package edu.sc.csce747.MeetingPlanner;

import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.
    private Person person;
    @Before
    public void setUp() {
        person = new Person("person1");
    }

    @Test
    public void testGetName() {
        assertEquals("person1", person.getName());
    }

    @Test
    public void testAddMeetingAndIsBusy() {
        try {
            Meeting meeting = new Meeting(10, 25, 14, 16, null, new Room("UB101"), "AI Planning");
            person.addMeeting(meeting);
            boolean busy = person.isBusy(10, 25, 14, 15);
            assertTrue("Person should be busy during meeting time", busy);
        } catch (TimeConflictException e) {
            fail("Unexpected conflict: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeetingConflict() {
        try {
            Meeting m1 = new Meeting(10, 25, 10, 12, null, new Room("UB102"), "Morning Sync");
            Meeting m2 = new Meeting(10, 25, 11, 13, null, new Room("UB103"), "Overlap Session");
            person.addMeeting(m1);
            person.addMeeting(m2);
            fail("Should throw TimeConflictException");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for attendee person1"));
        }
    }

    @Test
    public void testPrintAgendaMonth() {
        try {
            Meeting meeting = new Meeting(10, 25, 9, 10, new ArrayList<>(), new Room("UB104"), "Daily Brief");
            person.addMeeting(meeting);
            String agenda = person.printAgenda(10);
            assertTrue(agenda.contains("Daily Brief"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPrintAgendaDay() {
        try {
            Meeting meeting = new Meeting(10, 25, 17, 18,  new ArrayList<>(), new Room("UB105"), "Wrap-up");
            person.addMeeting(meeting);
            String agenda = person.printAgenda(10, 25);
            assertTrue(agenda.contains("Wrap-up"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetAndRemoveMeeting() {
        try {
            Meeting meeting = new Meeting(10, 25, 8, 9, null, new Room("UB106"), "Quick Check-in");
            person.addMeeting(meeting);
            Meeting retrieved = person.getMeeting(10, 25, 0);
            assertEquals("Quick Check-in", retrieved.getDescription());

            person.removeMeeting(10, 25, 0);
            boolean busy = person.isBusy(10, 25, 8, 9);
            assertFalse("Meeting should be removed", busy);
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

}
