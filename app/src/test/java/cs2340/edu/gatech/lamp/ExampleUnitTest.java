package cs2340.edu.gatech.lamp;

import android.content.Context;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.zip.CheckedOutputStream;

import cs2340.edu.gatech.lamp.controller.DefaultActivity;
import cs2340.edu.gatech.lamp.controller.WelcomeActivity;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.Reservation;

import cs2340.edu.gatech.lamp.model.HomelessUser;
import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.model.User;

import static org.junit.Assert.*;

import android.content.ContextWrapper;
import android.test.mock.MockContext;

import com.google.firebase.analytics.FirebaseAnalytics;

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

    @Test
    public void getShelterByKey_test() {
       String[] shelterInput1 = {"testID1", "testShelter", "999", "", "420", "69", "Able Av", "", "", "0", "875"};
       String[] shelterInput2 = {"testID2", "testShelter", "999", "", "420", "69", "Able Av", "", "", "0", "875"};
       String[] shelterInput3 = {"testID3", "testShelter", "999", "", "420", "69", "Able Av", "", "", "0", "875"};
       Shelter testShelter1 = new Shelter(shelterInput1);
       Shelter testShelter2 = new Shelter(shelterInput2);
       Shelter testShelter3 = new Shelter(shelterInput3);
       Model.getInstance().initShelters();
       Model.getInstance().getAllShelters().add(testShelter1);
       Model.getInstance().getAllShelters().add(testShelter2);
       Model.getInstance().getAllShelters().add(testShelter3);
       assertEquals(testShelter1, Model.getInstance().getShelterByKey("testID1"));
       assertEquals(testShelter2, Model.getInstance().getShelterByKey("testID2"));
       assertEquals(testShelter3, Model.getInstance().getShelterByKey("testID3"));
       assertEquals(null, Model.getInstance().getShelterByKey("notTestID1"));
    }
    //potential methods to test
    //getShelterByKey_test()
    //getReservation
    //increaseReservation
}