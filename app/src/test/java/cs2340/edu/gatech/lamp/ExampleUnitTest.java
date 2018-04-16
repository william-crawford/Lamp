package cs2340.edu.gatech.lamp;

import android.content.Context;

import org.junit.Test;

import java.lang.reflect.Field;

import cs2340.edu.gatech.lamp.controller.WelcomeActivity;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.Reservation;

import cs2340.edu.gatech.lamp.model.HomelessUser;
import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.model.User;

import static org.junit.Assert.*;

import android.test.mock.MockContext;
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

    @Test
    public void shelter_equals() {
        String[] testInfo1 = {"666", "doggo", "999", "", "42", "13", "random rd", "", "", "0", "420"};
        String[] testInfo2 = {"666", "more doggo", "999", "", "42", "13", "random rd", "", "", "0", "420"};
        Shelter shelter1 = new Shelter(testInfo1);
        Shelter shelter2 = new Shelter(testInfo2);
        assertTrue(shelter1.equals(shelter2));
        String[] testInfo3 = {"840", "more doggo", "999", "", "42", "13", "random rd", "", "", "0", "420"};
        Shelter shelter3 = new Shelter(testInfo3);
        assertFalse(shelter1.equals(shelter3));
    }

}