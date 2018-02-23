package cs2340.edu.gatech.lamp.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.model.Shelter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapsDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapsDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsDetailFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView nameText;
    private TextView distanceText;

    public MapsDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapsDetailFragment.
     */
    public static MapsDetailFragment newInstance() {
        MapsDetailFragment fragment = new MapsDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maps_detail, container, false);
        nameText = v.findViewById(R.id.txt_map_location_name);
        distanceText = v.findViewById(R.id.txt_map_distance_to);
        return v;
    }


    public interface OnFragmentInteractionListener {
        void onMapItemSelected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setSelected(Shelter shelter, LatLng current) {
        nameText.setText(shelter.getName());
        distanceText.setText(String.format(Locale.US, "%.1f mi.", shelter.getLocation().distanceTo(current)));
    }
}
