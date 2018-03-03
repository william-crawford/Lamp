package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Shelter;

/**
 * Created by Potato on 2/20/2018.
 */

public class ListActivity extends AppCompatActivity {

    Context context = this;
    ListView listView;
    ListAdapter listAdapter;
    ArrayList<String> listItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        shelterList = new ArrayList<>();
        String[] details = new String[0];
        createShelterList(details, shelterList);

        //read in csv
//        try {
//            Log.d("CSV", "In Scanner");
//            Scanner csvScan = new Scanner(new File("C:\\Users\\JermiahRussell\\Georgia Institute of Technology\\OneDrive - Georgia Institute of Technology\\CS Classes\\CS2340\\lamp\\app\\src\\main\\java\\cs2340\\edu\\gatech\\lamp\\controller\\HomelessShelterDatabase.csv"));
//            csvScan.useDelimiter(",");
//            Log.d("CSV", "Scanned Again");
//            Log.d("CSV", csvScan.next() + "|" + csvScan.next());
//        } catch (Exception e) {
//            Log.d("CSV", e.getMessage());
//        }
        listView = findViewById(android.R.id.list);
        for (Object s: shelterList.toArray()) {
            listItems.add(s.toString());
        }
//        listItems.add("Dank");
//        listItems.add("memes");
//        listItems.add("DABLAB");
//        listItems.add("\uD83D\uDE02");
        listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listItems) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                return view;
            }
        };
        //listView.setAdapter(listAdapter);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.test,listItems));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
//                ListView lv = (ListView) parent;
//                TextView tv = (TextView) lv.getChildAt(position);
//                String s = tv.getText().toString();
                //Do some action
                Intent intent = new Intent(ListActivity.this, TestActivity.class);
                intent.putExtra("testing", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            } });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void createShelterList(String[] details, ArrayList<Shelter> list) {
        Log.d("CSV", "Reading1");
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.homeless_shelter_database));
        Log.d("CSV", "Reading2");
        int i = 0;
        details = scan.nextLine().split(",");
        while (scan.hasNext()) {
            i++;
            String[] info = scan.nextLine().split(",");
            list.add(new Shelter(info));
        }
    }

}