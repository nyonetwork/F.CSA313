package edu.sc.csce747.MeetingPlanner;

import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MeetingTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.
    private Person person1;
    private Person person2;
    private Room room1;
    private ArrayList<Person> attendees;

    @Before
    public void setUp(){
        person1 = new Person("person1");
        person2 = new Person("person2");
        room1 = new Room("Room101");
        attendees = new ArrayList<>();
        attendees.add(person1);
        attendees.add(person2);
    }

    @Test
    public void testDefaultConstructor(){
        Meeting meeting = new Meeting();
        assertNull("Month should be null", meeting.getRoom());
        assertNull("Attendees should be null", meeting.getAttendees());
    }
    @Test
    public void testWholeDayConstructor(){
        Meeting meeting = new Meeting(4,20, "Company Retreat");
        assertEquals("Company Retreat", meeting.getDescription());
        assertEquals(0, meeting.getStartTime());
        assertEquals(23, meeting.getEndTime());
    }
    @Test
    public void testDetailedConstructorWithoutExtras(){
        Meeting meeting = new Meeting(5,10,9,11);
        assertEquals(5, meeting.getMonth());
        assertEquals(10, meeting.getDay());
        assertEquals(9, meeting.getStartTime());
        assertEquals(11, meeting.getEndTime());
    }
    @Test 
    public void testFullConstructorWithAttendeesAndRoom(){
        Meeting meeting = new Meeting(6,18,14,16,attendees,room1,"Project Review");
        assertEquals("Project Review", meeting.getDescription());
        assertEquals("Room101", meeting.getRoom().getID());
        assertEquals(2, meeting.getAttendees().size());
        assertEquals("person1", meeting.getAttendees().get(0).getName());
    }
    @Test
    public void testAddAttendee() {
        Meeting meeting = new Meeting(7, 22, 10, 11, new ArrayList<>(), room1, "One-on-One");
        meeting.addAttendee(person1);
        assertEquals(1, meeting.getAttendees().size());
        assertEquals("person1", meeting.getAttendees().get(0).getName());
    }

    @Test
    public void testRemoveAttendee() {
        Meeting meeting = new Meeting(8, 5, 13, 14, attendees, room1, "Team Sync");
        meeting.removeAttendee(person2);
        assertEquals(1, meeting.getAttendees().size());
        assertEquals("person1", meeting.getAttendees().get(0).getName());
    }

    @Test
    public void testToStringFormat() {
        Meeting meeting = new Meeting(9, 30, 15, 17, attendees, room1, "Design Review");
        String output = meeting.toString();
        assertTrue(output.contains("9/30, 15 - 17,Room101: Design Review"));
        assertTrue(output.contains("Attending: person1,person2"));
    }

    @Test
    public void testSettersAndGetters() {
        Meeting meeting = new Meeting();
        meeting.setMonth(10);
        meeting.setDay(12);
        meeting.setStartTime(8);
        meeting.setEndTime(9);
        meeting.setRoom(room1);
        meeting.setDescription("Morning Briefing");

        assertEquals(10, meeting.getMonth());
        assertEquals(12, meeting.getDay());
        assertEquals(8, meeting.getStartTime());
        assertEquals(9, meeting.getEndTime());
        assertEquals("Room101", meeting.getRoom().getID());
        assertEquals("Morning Briefing", meeting.getDescription());
    }
}
