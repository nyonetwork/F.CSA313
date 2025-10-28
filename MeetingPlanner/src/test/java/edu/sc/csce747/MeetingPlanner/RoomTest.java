package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.
     private Room room;

    @Before
    public void setUp() {
        room = new Room("UB202");
    }

    @Test
    public void testGetID() {
        assertEquals("UB202", room.getID());
    }

    @Test
    public void testAddMeetingAndIsBusy() {
        try {
            Meeting meeting = new Meeting(11, 5, 10, 12, null, room, "Planning Session");
            room.addMeeting(meeting);
            boolean busy = room.isBusy(11, 5, 10, 11);
            assertTrue("Room should be busy during meeting time", busy);
        } catch (TimeConflictException e) {
            fail("Unexpected conflict: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeetingConflict() {
        try {
            Meeting m1 = new Meeting(11, 5, 9, 11, null, room, "Morning Briefing");
            Meeting m2 = new Meeting(11, 5, 10, 12, null, room, "Overlap Meeting");
            room.addMeeting(m1);
            room.addMeeting(m2);
            fail("Should throw TimeConflictException due to overlap");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for room UB202"));
        }
    }

    @Test
    public void testPrintAgendaMonth() {
        try {
            Meeting meeting = new Meeting(11, 6, 14, 15,  new ArrayList<>(), room, "Design Review");
            room.addMeeting(meeting);
            String agenda = room.printAgenda(11);
            assertTrue(agenda.contains("Design Review"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPrintAgendaDay() {
        try {
            Meeting meeting = new Meeting(11, 7, 16, 17, null, room, "Client Call");
            room.addMeeting(meeting);
            String agenda = room.printAgenda(11, 7);
            assertTrue(agenda.contains("Client Call"));
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetAndRemoveMeeting() {
        try {
            Meeting meeting = new Meeting(11, 8, 13, 14, null, room, "Quick Sync");
            room.addMeeting(meeting);
            Meeting retrieved = room.getMeeting(11, 8, 0);
            assertEquals("Quick Sync", retrieved.getDescription());

            room.removeMeeting(11, 8, 0);
            boolean busy = room.isBusy(11, 8, 13, 14);
            assertFalse("Meeting should be removed", busy);
        } catch (TimeConflictException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

}
