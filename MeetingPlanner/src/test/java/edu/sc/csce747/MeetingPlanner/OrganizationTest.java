package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class OrganizationTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.
    private Organization org;
    @Before
    public void setUp(){
        org = new Organization();
    }

    @Test
    public void testGetEmployeesCount() {
        ArrayList<Person> employees = org.getEmployees();
        assertEquals("Should have 5 default employees", 5, employees.size());
    }

    @Test
    public void testGetRoomsCount() {
        ArrayList<Room> rooms = org.getRooms();
        assertEquals("Should have 5 default rooms", 5, rooms.size());
    }

    @Test
    public void testGetEmployeeByName() throws Exception {
        Person person = org.getEmployee("John Rose");
        assertNotNull("Employee should be found", person);
        assertEquals("John Rose", person.getName());
    }

    @Test
    public void testGetRoomByID() throws Exception {
        Room room = org.getRoom("2A03");
        assertNotNull("Room should be found", room);
        assertEquals("2A03", room.getID());
    }

    @Test
    public void testGetEmployeeNotFound() {
        try {
            org.getEmployee("Nonexistent Person");
            fail("Should throw exception for unknown employee");
        } catch (Exception e) {
            assertEquals("Requested employee does not exist", e.getMessage());
        }
    }

    @Test
    public void testGetRoomNotFound() {
        try {
            org.getRoom("9Z99");
            fail("Should throw exception for unknown room");
        } catch (Exception e) {
            assertEquals("Requested room does not exist", e.getMessage());
        }
    }

}
