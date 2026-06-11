package com.example.stylenestboutique.ui.profile;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.stylenestboutique.data.UserManager;
import com.example.stylenestboutique.databinding.FragmentMapBinding;
import com.example.stylenestboutique.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapBinding binding;
    private GoogleMap mMap;
    private LatLng selectedLatLng;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(com.example.stylenestboutique.R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        binding.confirmLocationButton.setOnClickListener(v -> {
            if (selectedLatLng != null) {
                saveLocationAndExit();
            } else {
                Toast.makeText(getContext(), "Please move the map to select a location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        // Default location (e.g., center of a city)
        LatLng defaultLoc = new LatLng(-1.286389, 36.817223); // Nairobi as example
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLoc, 15f));

        mMap.setOnCameraIdleListener(() -> {
            selectedLatLng = mMap.getCameraPosition().target;
        });
    }

    private void saveLocationAndExit() {
        UserManager userManager = UserManager.getInstance(requireContext());
        User user = userManager.getUser();
        user.setLatitude(selectedLatLng.latitude);
        user.setLongitude(selectedLatLng.longitude);

        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(selectedLatLng.latitude, selectedLatLng.longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                user.setAddress(addresses.get(0).getAddressLine(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        userManager.saveUser(user);
        Toast.makeText(getContext(), "Location saved!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
