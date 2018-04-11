package cs2340.edu.gatech.lamp;

import org.junit.Test;

import java.lang.reflect.Field;

import cs2340.edu.gatech.lamp.model.Reservation;

import cs2340.edu.gatech.lamp.model.HomelessUser;
import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.model.User;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void reservation_decrement() throws Exception {
        //create reservation with 1 space reserved
        Reservation reservation = new Reservation("Test ID");

        assertFalse(reservation.decrement());

        //modify reservation through reflection
        Field spacesReserved = Reservation.class.getDeclaredField("spacesReserved");
        spacesReserved.setAccessible(true);
        spacesReserved.set(reservation, 2);

        //Test non empty post decrement
        assertTrue(reservation.decrement());
        assertEquals(1, reservation.getSpacesReserved());

    }

    @Test
    public void shelter_isFull() throws Exception {
        String[] testInput = {"498", "sucks", "500", "", "5", "5", "place dr", "", "", "1", "400"};
        Shelter testShelter = new Shelter(testInput);
        assertEquals(false, testShelter.isFull());
        testInput[2] = "300";
        Shelter testShelter2 = new Shelter(testInput);
        assertEquals(true, testShelter2.isFull());

    }
}