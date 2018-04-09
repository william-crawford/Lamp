package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.utils.HelperUI;

/**
 * Created by Potato on 2/20/2018.
 */

public class ListActivity extends AppCompatActivity {

    Context context = this;
    ListView listView;
    ListAdapter listAdapter;
    EditText search;
    Spinner age;
    Spinner gender;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        search = findViewById(R.id.search);
        age = findViewById(R.id.spinner1);
        gender = findViewById(R.id.spinner2);
        listView = findViewById(android.R.id.list);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateFiltering((Shelter.GenderPolicy) gender.getSelectedItem(), (Shelter.AgePolicy) age.getSelectedItem(), search.getText().toString());
            }
        });

        ArrayAdapter<String> ageAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Shelter.AgePolicy.values());
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(ageAdapter);

        age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                updateFiltering((Shelter.GenderPolicy) gender.getSelectedItem(), (Shelter.AgePolicy) age.getSelectedItem(), search.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing!!!! be free!!!!
            }

        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                updateFiltering((Shelter.GenderPolicy) gender.getSelectedItem(), (Shelter.AgePolicy) age.getSelectedItem(), search.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing!!!! be free!!!!
            }

        });

        ArrayAdapter<String> genderAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Shelter.GenderPolicy.values());
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        listAdapter = new CustomAdapter(Model.getInstance().getAllShelters(), context);

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
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                //Do some action
                Shelter shelter = (Shelter) listView.getItemAtPosition(position);
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("shelterID", shelter.getKey());
                /*
                intent.putExtra("info", shelter.getInfo());
                intent.putExtra("sName", shelter.getName());
                String adr = shelter.getLocation().getStreet() + ", " + shelter.getLocation().getCity()
                          + ", " + shelter.getLocation().getState() + " " + shelter.getLocation().getZip();
                intent.putExtra("sAddress", shelter.getLocation().getAddress());
                intent.putExtra("number", shelter.getPhoneNumber());
                intent.putExtra("url", shelter.getImageURL());
                intent.putExtra("ide", shelter.getKey());
                intent.putExtra("hasSpace", shelter.isHasSpace());
                intent.putExtra("capacity", shelter.getCapacity());
                intent.putExtra("notes", shelter.getNotes());
                intent.putExtra("restrictions", shelter.getRestrictions());
                */
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


    private void updateFiltering(Shelter.GenderPolicy gp, Shelter.AgePolicy ap, String search) {
        List<Shelter> filteredShelters = new ArrayList<>();
        for (Shelter s : Model.getInstance().getAllShelters()) {
            if (s.checkGPFilter(gp)
                    && s.checkAPFilter(ap)
                    && s.checkNameFilter(search)) {
                filteredShelters.add(s);
                Model.getInstance().setFilteredShelters(filteredShelters);
            }
        }
        CustomAdapter newAdapter = new CustomAdapter(filteredShelters, this);
        listView.setAdapter(newAdapter);
    }

    @Override
    public void onBackPressed() {
        HelperUI.signOut(this);
    }

}