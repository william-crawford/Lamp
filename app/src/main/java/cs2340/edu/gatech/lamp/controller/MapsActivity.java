package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Location;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.utils.HelperUI;

public class MapsActivity extends FragmentActivity implements MapsDetailFragment.OnFragmentInteractionListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Shelter> shelters;
    private MapsDetailFragment detailFragment;
    private LatLng currentLocation;
    private Map<LatLng, Shelter> shelterMap = new HashMap<>();
    private Marker selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        shelters = fetchShelterData();

        detailFragment = (MapsDetailFragment) getSupportFragmentManager().findFragmentById(R.id.map_detail);
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

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (!marker.equals(selected)) {
                    updateSelected(marker);
                }
                return false;
            }
        });

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        android.location.Location location = null;
        try {
            location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Attention")
                    .setMessage("For best functionality, allow Lamp to access your location."
                            + "This can be done from your system settings")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            onBackPressed();
        } catch (Exception e) {
            Toast.makeText(this, "error fetching location, check permissions", Toast.LENGTH_LONG).show();
            onBackPressed();
        }

        if (location != null) {
            currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                    .target(currentLocation)
                    .zoom(14)
                    .build()
            ));

            detailFragment.setSelected(shelters.get(0), currentLocation);
        } else {
            LatLng culc = new LatLng(33.7749, -84.3964);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(culc));
        }


        String[] info = getIntent().getStringArrayExtra("shelterInfo");
        Shelter passedIn = null;
        if (info != null) {
            passedIn = new Shelter(info);
        }

        for (Shelter shelter : shelters) {
            MarkerOptions marker = new MarkerOptions()
                    .position(shelter.getLocation().getLatLng())
                    .title(shelter.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            shelter.isHasSpace() ?
                                    BitmapDescriptorFactory.HUE_GREEN :
                                    BitmapDescriptorFactory.HUE_ORANGE
                    ));
            Marker m = mMap.addMarker(marker);
            shelterMap.put(shelter.getLocation().getLatLng(), shelter);
            if (passedIn != null && passedIn.equals(shelter)) {
                updateSelected(m);
            }
        }
    }

    private List<Shelter> fetchShelterData() {
        return Model.getInstance().getAllShelters();
    }

    @Override
    public void onDirectionsButtonPressed(Shelter shelter) {
        Toast.makeText(this, "Feature coming soon!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetailsButtonPressed(Shelter shelter) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("shelterInfo", shelter.getInfo());
        startActivity(intent);
    }

    @Override
    public void onOtherSheltersButtonPressed() {
        HelperUI.goToList(this);
    }

    private void updateSelected(Marker marker) {
        Shelter shelter = shelterMap.get(marker.getPosition());
        detailFragment.setSelected(shelter, currentLocation);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                .target(shelter.getLocation().getLatLng())
                .zoom(14)
                .build()
        ));
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        if (selected != null) {
            selected.setIcon(BitmapDescriptorFactory.defaultMarker(
                    shelterMap.get(selected.getPosition()).isHasSpace() ?
                            BitmapDescriptorFactory.HUE_GREEN :
                            BitmapDescriptorFactory.HUE_ORANGE
            ));
        }
        selected = marker;
    }
}
