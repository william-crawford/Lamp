package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.Shelter;

/**
 * Created by Potato on 2/20/2018.
 */

public class ListActivity extends AppCompatActivity {

    Context context = this;
    ListView listView;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(android.R.id.list);

        listAdapter = new CustomAdapter((ArrayList<Shelter>)Model.getInstance().getAllShelters(), context);

//        listAdapter = new ArrayAdapter<Shelter>(context, R.layout.listelement,
//                Model.getInstance().getAllShelters()) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                Shelter shelter = getItem(position);
//                View view = super.getView(position, convertView, parent);
//                TextView name = view.findViewById(R.id.name);
//                TextView location = view.findViewById(R.id.location);
//                TextView space = view.findViewById(R.id.hasSpace);
//                name.setTextColor(Color.WHITE);
//                location.setTextColor(Color.WHITE);
//                space.setTextColor(Color.WHITE);
//                name.setText(shelter.getName());
//                String address = shelter.getLocation().getStreet() + ", " +
//                        shelter.getLocation().getCity() + ", " + shelter.getLocation().getState()
//                        + " " + shelter.getLocation().getZip();
//                location.setText(address);
//                if(shelter.isHasSpace()){
//                    space.setText("Available");
//                } else {
//                    space.setText("Full");
//                }
//                ImageView call = view.findViewById(R.id.call_butt);
//                call.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //call shelter number
//                    }
//                });
//                return view;
//            }
//        };
        //listView.setAdapter(listAdapter);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                //Do some action
                Shelter shelter = (Shelter) listView.getItemAtPosition(position);
                Intent intent = new Intent(ListActivity.this, TestActivity.class);
                //get Intent.putExtra("info", shelter.getInfo());
                intent.putExtra("sName", shelter.getName());
//                String adr = shelter.getLocation().getStreet() + ", " + shelter.getLocation().getCity()
//                        + ", " + shelter.getLocation().getState() + " " + shelter.getLocation().getZip();
                intent.putExtra("sAddress", shelter.getLocation().getAddress());
                intent.putExtra("number", shelter.getPhoneNumber());
                intent.putExtra("url", shelter.getImageURL());
                intent.putExtra("ide", shelter.getKey());
                intent.putExtra("hasSpace", shelter.isHasSpace());
                intent.putExtra("capacity", shelter.getCapacity());
                intent.putExtra("notes", shelter.getNotes());
                intent.putExtra("restrictions", shelter.getRestrictions());
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

}