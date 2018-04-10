package cs2340.edu.gatech.lamp.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.utils.ShelterManager;

/**
 * Created by JermiahRussell on 2/13/2018.
 */

public class Model {
    private static final Model _instance = new Model();

    public static Model getInstance() {
        return _instance;
    }

    private Model() {}

    private User currentUser;
    private ShelterOwner shelterOwnerDefault;
    private List<Shelter> allShelters;
    private List<Shelter> filteredShelters;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setShelterOwnerDefault(ShelterOwner owner) {
        shelterOwnerDefault = owner;
    }

    public List<Shelter> getAllShelters() {
        return allShelters;
    }

    public void setFilteredShelters(List<Shelter> shelters) {
        filteredShelters = shelters;
    }
    public List<Shelter> getFilteredShelters() {
        return filteredShelters;
    }

    public Shelter getShelterByKey(String key) {
        for (Shelter s : allShelters) {
            if (s.getKey().equals(key)) {
                return s;
            }
        }
        return null;
    }

    public static void save(Context context) {
        _instance.writeSheltersToFile(context);

    }

    public static void load(Context context) {
        _instance.readSheltersFromFile(context);
    }

    private void writeSheltersToFile(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("shelters.txt", Context.MODE_PRIVATE));
            String data = "";
            for (Shelter s : allShelters) {
                for (String str : s.getInfo()) {
                    data = data + str + ",";
                }
                data = data + "\n";
            }
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (Exception e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void readSheltersFromFile(Context context) {
        try {
            InputStream inputStream = context.openFileInput("shelters.txt");

            allShelters = new ArrayList<>();

            if ( inputStream != null ) {
                Scanner scan = new Scanner(inputStream);

                while (scan.hasNext()) {
                    String[] info = scan.nextLine().split(",");
                    allShelters.add(new Shelter(info));
                    ShelterManager.createShelter(shelterOwnerDefault, info);
                }

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            initShelters(context);
            System.out.println("NO shelters.txt FILE FOUND");
            save(context);
            System.out.println("saved");
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    public void initShelters(Context context) {
        allShelters = new ArrayList<>();
        Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.homeless_shelter_database));
        scan.nextLine().split(","); //gets rid of the first line with names of columns
        while (scan.hasNext()) {
            String[] info = scan.nextLine().split(",");
            allShelters.add(new Shelter(info));
            ShelterManager.createShelter(shelterOwnerDefault, info);
        }
    }
}
