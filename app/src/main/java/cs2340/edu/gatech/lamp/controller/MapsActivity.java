package cs2340.edu.gatech.lamp.controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Address;
import cs2340.edu.gatech.lamp.model.Shelter;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Shelter> shelters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        shelters = fetchShelterData();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng culc = new LatLng(33.7749, -84.3964);
        //mMap.addMarker(new MarkerOptions().position(culc).title("Come sleep in the CULC!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(culc));

        for (Shelter shelter : shelters) {
            mMap.addMarker(new MarkerOptions()
                    .position(shelter.getAddress().getLatLng())
                    .title(shelter.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            shelter.isHasSpace() ?
                                    BitmapDescriptorFactory.HUE_GREEN :
                                    BitmapDescriptorFactory.HUE_ORANGE
                    ))
            );
        }
    }

    private List<Shelter> fetchShelterData() {
        List<Shelter> data = new ArrayList<>();

        data.add(new Shelter(
                "The CULC",
                new Address(33.7749, -84.3964, "266 4th St. NW"),
                false
        ));

        data.add(new Shelter(
                "Architecture Roof",
                new Address(33.7763, -84.3957, "245 4th St. NW"),
                true
        ));

        return data;
    }
}
