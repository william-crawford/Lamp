package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.content.DialogInterface;
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
import java.util.Locale;
import java.util.Map;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Location;
import cs2340.edu.gatech.lamp.model.Shelter;

public class MapsActivity extends FragmentActivity implements MapsDetailFragment.OnFragmentInteractionListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Shelter> shelters;
    private MapsDetailFragment detailFragment;
    private LatLng currentLocation;
    private Map<LatLng, Shelter> shelterMap = new HashMap<>();


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
                Shelter shelter = shelterMap.get(marker.getPosition());
                detailFragment.setSelected(shelter, currentLocation);
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
        } catch (NullPointerException e) {
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText("Error retrieving location");
            toast.show();
        }

        if (location != null) {
            currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                    .target(currentLocation)
                    .zoom(15)
                    .build()
            ));

            detailFragment.setSelected(shelters.get(0), currentLocation);
        } else {
            LatLng culc = new LatLng(33.7749, -84.3964);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(culc));
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
            mMap.addMarker(marker);
            shelterMap.put(shelter.getLocation().getLatLng(), shelter);
        }
    }

    private List<Shelter> fetchShelterData() {
        List<Shelter> data = new ArrayList<>();

        data.add(new Shelter(
                "The CULC",
                new Location(33.7749, -84.3964, "266 4th St. NW"),
                false
        ));

        data.add(new Shelter(
                "Architecture Roof",
                new Location(33.7761, -84.3960, "245 4th St. NW"),
                true
        ));

        return data;
    }

    @Override
    public void onDirectionsButtonPressed(Shelter shelter) {

    }

    @Override
    public void onDetailsButtonPressed(Shelter shelter) {

    }

    @Override
    public void onOtherSheltersButtonPressed() {

    }
}
